package Elis.College.RegistroPranzi.controller;

import Elis.College.RegistroPranzi.exception.model.Result;
import Elis.College.RegistroPranzi.exception.model.exceptionimpl.ResponseHeaderFiller;
import Elis.College.RegistroPranzi.model.NumberOfPrenotationResponse;
import Elis.College.RegistroPranzi.model.Register;
import Elis.College.RegistroPranzi.model.RegisterResponse;
import Elis.College.RegistroPranzi.service.RegisterService;
import Elis.College.RegistroPranzi.utility.RequestValidator;
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
import java.util.UUID;

@RestController
public class PrenotationController {

    @Autowired
    RegisterService registerService;

    private final Logger logger = LoggerFactory.getLogger(PrenotationController.class);

    //TODO doesn't work
    //get prenotations by id

    //GET prenotations by userId
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
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    //Get all prenotations
    @CrossOrigin
    @GetMapping(value = "/prenotations")
    public ResponseEntity<NumberOfPrenotationResponse> countPrenotationByDate(@RequestParam(value = "date", required = true) Date date) throws Exception {

        String logID = generateLogID("GET number of prenotation by date");
        Timestamp startTime = startLog(logID);

        RequestValidator.checkParameter(date, "date");

        NumberOfPrenotationResponse response = NumberOfPrenotationResponse.builder()
                .result(new Result(0, "Operation completed successfully"))
                .value(registerService.countPrenotationsByDate(date, logID))
                .build();

        HttpHeaders headers = ResponseHeaderFiller.setHeaders(response);

        endLog(logID, startTime);
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    //PUT prenotation on db
    @CrossOrigin
    @PutMapping(value = "/prenotations")
    public ResponseEntity savePrenotation(@RequestBody Register register) throws Exception {

        String logID = generateLogID("POST an prenotation");
        Timestamp startTime = startLog(logID);

        RequestValidator.checkParameter(register, "prenotation");
        RequestValidator.checkBody(register);

        registerService.savePrenotation(register, logID);

        Result result = new Result(0, "Operation completed successfully");
        HttpHeaders headers = ResponseHeaderFiller.setHeaders(result);

        endLog(logID, startTime);
        return new ResponseEntity(headers, HttpStatus.OK);
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
