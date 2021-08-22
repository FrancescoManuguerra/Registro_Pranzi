package Elis.College.RegistroPranzi.controller;

import Elis.College.RegistroPranzi.exception.model.Result;
import Elis.College.RegistroPranzi.exception.model.exceptionimpl.ResponseHeaderFiller;
import Elis.College.RegistroPranzi.model.MonthlyPrenotation;
import Elis.College.RegistroPranzi.model.NumberOfPrenotationResponse;
import Elis.College.RegistroPranzi.model.Register;
import Elis.College.RegistroPranzi.model.RegisterResponse;
import Elis.College.RegistroPranzi.service.RegisterService;
import Elis.College.RegistroPranzi.utility.RequestValidator;
import Elis.College.RegistroPranzi.utility.Utilities;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class PrenotationController {

    @Autowired
    RegisterService registerService;

    private final Logger logger = LoggerFactory.getLogger(PrenotationController.class);

    @CrossOrigin
    @GetMapping("/prenotation/testpagination")
    public ResponseEntity<RegisterResponse> getPrenotationPagination(@RequestParam(defaultValue = "0") Integer pageNo,
                                                                     @RequestParam(defaultValue = "10") Integer pageSize,
                                                                     @RequestParam(defaultValue = "id") String sortBy) throws Exception {

        RegisterResponse response = RegisterResponse.builder()
                .result(new Result(0, "Operation completed successfully"))
                .registerList(registerService.getAllPrenotations(pageNo, pageSize, sortBy))
                .build();


        return new ResponseEntity(response, HttpStatus.OK);
    }


    //GET prenotations by userId
    //SWAGGER DEFINITION
    @ApiOperation(value = "Get prenotation by user id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "SUCCESS", response = RegisterResponse.class),
            @ApiResponse(code = 400, message = "BAD REQUEST", response = Result.class),
            @ApiResponse(code = 401, message = "UNAUTHORIZED!", response = Result.class),
            @ApiResponse(code = 403, message = "FORBIDDEN!", response = Result.class),
            @ApiResponse(code = 404, message = "NOT FOUND", response = Result.class),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR", response = Result.class)
    })
    @CrossOrigin
    @GetMapping(value = "/prenotations/{userId}")
    public ResponseEntity<RegisterResponse> getAllByUser_id(@PathVariable(name = "userId", required = true) Long userId) throws Exception {

        String logID = generateLogID("GET prenotation by userId");
        Timestamp startTime = startLog(logID);

        RequestValidator.checkParameter(userId, "userid");

        RegisterResponse response = RegisterResponse.builder()
                .result(new Result(0, "Operation completed successfully"))
                .registerList(registerService.getAllByUser_id(userId, logID))
                .build();

        HttpHeaders headers = ResponseHeaderFiller.setHeaders(response);

        endLog(logID, startTime);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //Get all prenotations
    @ApiOperation(value = "Get all prenotations ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "SUCCESS", response = NumberOfPrenotationResponse.class),
            @ApiResponse(code = 400, message = "BAD REQUEST", response = Result.class),
            @ApiResponse(code = 401, message = "UNAUTHORIZED!", response = Result.class),
            @ApiResponse(code = 403, message = "FORBIDDEN!", response = Result.class),
            @ApiResponse(code = 404, message = "NOT FOUND", response = Result.class),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR", response = Result.class)
    })
    @CrossOrigin
    @GetMapping(value = "/dailyprenotations")
    public ResponseEntity<NumberOfPrenotationResponse> countPrenotationByDate(@RequestParam(value = "date", required = true) Date date) throws Exception {

        String logID = generateLogID("GET number of prenotation by date");
        Timestamp startTime = startLog(logID);

        RequestValidator.checkParameter(date, "date");


        NumberOfPrenotationResponse response = NumberOfPrenotationResponse.builder()
                .result(new Result(0, "Operation completed successfully"))
                .launch_value(registerService.countPrenotationsByDate(date, logID).getLunchvalue())
                .dinner_value(registerService.countPrenotationsByDate(date, logID).getDinnervalue())
                .build();

        HttpHeaders headers = ResponseHeaderFiller.setHeaders(response);

        endLog(logID, startTime);
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping(value = "/monthlyprenotations")
    public ResponseEntity<NumberOfPrenotationResponse> countPrenotationByMonth(@RequestParam(value = "date") String date) throws Exception {

        String logID = generateLogID("GET number of prenotation by month");
        Timestamp startTime = startLog(logID);

        RequestValidator.checkParameter(date, "date");
        RequestValidator.isDateMonth(date);

        MonthlyPrenotation monthlyPrenotation = registerService.countPrenotationByMonth(Utilities.convertDateMonthToSqlDate(date), logID);

        NumberOfPrenotationResponse response = NumberOfPrenotationResponse.builder()
                .result(new Result(0, "Operation completed successfully"))
                .launch_value(monthlyPrenotation.getLunchvalue())
                .dinner_value(monthlyPrenotation.getDinnervalue())
                .build();

        HttpHeaders headers = ResponseHeaderFiller.setHeaders(response);

        endLog(logID, startTime);
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping(value = "/monthlyprenotations/{id}")
    public ResponseEntity<RegisterResponse> getMonthlyPrenotationById(@PathVariable(value = "id") Long id) throws Exception {

        String logID = generateLogID("GET monthly prenotation by id");
        Timestamp startTime = startLog(logID);

        RequestValidator.checkParameter(id, "date");
        RequestValidator.checkUserId(id);

        String message = "Operation completed successfully";

        List<Register> registerList = registerService.getMonthlyPrenotationById(id, logID);
        if (registerList == null || registerList.size() == 0) message = "No prenotations for id : " + id;

        RegisterResponse response = new RegisterResponse(new Result(0, message), registerList);

        HttpHeaders headers = ResponseHeaderFiller.setHeaders(response);

        endLog(logID, startTime);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    //PUT prenotation on db
    @ApiOperation(value = "Save an prenotation")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "SUCCESS", response = Result.class),
            @ApiResponse(code = 400, message = "BAD REQUEST", response = Result.class),
            @ApiResponse(code = 401, message = "UNAUTHORIZED!", response = Result.class),
            @ApiResponse(code = 403, message = "FORBIDDEN!", response = Result.class),
            @ApiResponse(code = 404, message = "NOT FOUND", response = Result.class),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR", response = Result.class)
    })
    @PutMapping(value = "/prenotations")
    public ResponseEntity savePrenotation(@RequestBody Register register) throws Exception {

        String logID = generateLogID("POST an prenotation");
        Timestamp startTime = startLog(logID);

        RequestValidator.checkParameter(register, "prenotation");
        RequestValidator.checkBody(register);

        Register savePrenotation = registerService.savePrenotation(register, logID);

        Result result = new Result(0, "Operation completed successfully");
        HttpHeaders headers = ResponseHeaderFiller.setHeaders(result);

        endLog(logID, startTime);
        System.out.println(savePrenotation);
        return ResponseEntity.ok().build();
    }

    //LOG
    private String generateLogID(String processName) {

        String uniqueID = UUID.randomUUID().toString();
        String logID = "[" + uniqueID + "][" + processName + "]";
        return logID;
    }

    private Timestamp startLog(String logID) {
        Timestamp startTime = new Timestamp(System.currentTimeMillis());
        logger.info(logID + " Starting process at: " + startTime);
        return startTime;
    }

    private void endLog(String logID, Timestamp startTime) {
        Timestamp endTime = new Timestamp(System.currentTimeMillis());
        long diff = endTime.getTime() - startTime.getTime();
        logger.info(logID + " Ending process at: " + endTime
                + ", duration " + diff + " ms ");
    }

    @PostConstruct
    private void imAlive() {
        logger.info(" Prenotation Controller started");
    }

}
