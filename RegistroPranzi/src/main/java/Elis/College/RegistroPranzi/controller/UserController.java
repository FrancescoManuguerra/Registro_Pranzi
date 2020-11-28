package Elis.College.RegistroPranzi.controller;

import Elis.College.RegistroPranzi.exception.model.Result;
import Elis.College.RegistroPranzi.exception.model.exceptionimpl.ResponseHeaderFiller;
import Elis.College.RegistroPranzi.model.*;
import Elis.College.RegistroPranzi.service.UserService;
import Elis.College.RegistroPranzi.utility.RequestValidator;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.PostConstruct;
import java.sql.Timestamp;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    //GET USER FOR AUTHENTICATION
    @ApiOperation(value = "User authentication")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "SUCCESS", response = UserResponse.class),
            @ApiResponse(code = 400 , message = "BAD REQUEST", response = Result.class),
            @ApiResponse(code = 401, message = "UNAUTHORIZED!", response = Result.class),
            @ApiResponse(code = 403, message = "FORBIDDEN!", response = Result.class),
            @ApiResponse(code = 404, message =  "NOT FOUND", response = Result.class),
            @ApiResponse(code = 500 , message = "INTERNAL SERVER ERROR", response = Result.class)
    })
    @CrossOrigin
    @GetMapping(value = "/login")
    public ResponseEntity<UserResponse> login(@RequestParam(name = "email", required = false) String email, @RequestParam(name = "password", required = false) String password) throws Exception {

        String logID = generateLogID("Login");
        Timestamp startTime = startLog(logID);

        //INPUT VALIDATOR
        RequestValidator.checkLoginParameter(email,password);

        UserResponse response= UserResponse.builder()
                .result(new Result(0,"Operation completed successfully"))
                .user(userService.login(new LoginKey(email, DigestUtils.sha256Hex(password)),logID))
                .build();
        HttpHeaders headers = ResponseHeaderFiller.setHeaders(response);

        endLog(logID, startTime);
        return new ResponseEntity<>(response,headers, HttpStatus.OK);
    }


    //GET USER BY ID
    @ApiOperation(value = "Get user by user id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "SUCCESS", response = UserResponse.class),
            @ApiResponse(code = 400 , message = "BAD REQUEST", response = Result.class),
            @ApiResponse(code = 401, message = "UNAUTHORIZED!", response = Result.class),
            @ApiResponse(code = 403, message = "FORBIDDEN!", response = Result.class),
            @ApiResponse(code = 404, message =  "NOT FOUND", response = Result.class),
            @ApiResponse(code = 500 , message = "INTERNAL SERVER ERROR", response = Result.class)
    })
    @CrossOrigin
    @GetMapping(value = "users/{userid}")
    public ResponseEntity<UserResponse> getUser(@PathVariable(name = "userid") Long id) throws Exception {

        String logID = generateLogID("GET user by id");
        Timestamp startTime = startLog(logID);

        //INPUT VALIDATOR
        RequestValidator.checkParameter(id, "userid");
        RequestValidator.checkUserId(id);

        UserResponse response= UserResponse.builder()
                .result(new Result(0,"Operation completed successfully"))
                .user(userService.getUser(id,logID))
                .build();
        HttpHeaders headers = ResponseHeaderFiller.setHeaders(response);

        endLog(logID, startTime);
        return new ResponseEntity<>(response,headers, HttpStatus.OK);
    }

    //GET USERS
    @ApiOperation(value = "Get all users")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "SUCCESS", response = UserResponse.class),
            @ApiResponse(code = 400 , message = "BAD REQUEST", response = Result.class),
            @ApiResponse(code = 401, message = "UNAUTHORIZED!", response = Result.class),
            @ApiResponse(code = 403, message = "FORBIDDEN!", response = Result.class),
            @ApiResponse(code = 404, message =  "NOT FOUND", response = Result.class),
            @ApiResponse(code = 500 , message = "INTERNAL SERVER ERROR", response = Result.class)
    })
    @CrossOrigin
    @GetMapping(value = "/users")
    public ResponseEntity<UsersResponse> getUsers() {

        String logID = generateLogID("GET all users");
        Timestamp startTime = startLog(logID);

        UsersResponse response= UsersResponse.builder()
                .result(new Result(0, "Operation completed successfully"))
                .userList(userService.getUsers(logID))
                .build();

        HttpHeaders headers = ResponseHeaderFiller.setHeaders(response);

        endLog(logID, startTime);
        return new ResponseEntity<>(response,headers, HttpStatus.OK);
    }

    //REGISTRATION
    @ApiOperation(value = "Save an user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "SUCCESS", response = UserResponse.class),
            @ApiResponse(code = 400 , message = "BAD REQUEST", response = Result.class),
            @ApiResponse(code = 401, message = "UNAUTHORIZED!", response = Result.class),
            @ApiResponse(code = 403, message = "FORBIDDEN!", response = Result.class),
            @ApiResponse(code = 404, message =  "NOT FOUND", response = Result.class),
            @ApiResponse(code = 500 , message = "INTERNAL SERVER ERROR", response = Result.class)
    })
    @CrossOrigin
    @PostMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> insertUser(@RequestBody(required = true) User user) throws Exception {

        String logID = generateLogID("POST an user");
        Timestamp startTime = startLog(logID);

        RequestValidator.checkParameter(user, "User body");
        RequestValidator.checkBody(user);

        UserResponse response= UserResponse.builder()
                .result(new Result(0, "Operation completed successfully"))
                .user(userService.saveUser(User.builder()
                        .name(user.getName())
                        .surname(user.getSurname())
                        .number(user.getNumber())
                        .email(user.getEmail())
                        .password(DigestUtils.sha256Hex(user.getPassword()))
                        .lable_number(user.getLable_number())
                        .build(),logID))
                .build();
        HttpHeaders headers = ResponseHeaderFiller.setHeaders(response);

        endLog(logID, startTime);
        return new ResponseEntity<>(response,headers, HttpStatus.OK);
    }

    //DELETE USER
    @ApiOperation(value = "Delete an user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "SUCCESS", response = Result.class),
            @ApiResponse(code = 400 , message = "BAD REQUEST", response = Result.class),
            @ApiResponse(code = 401, message = "UNAUTHORIZED!", response = Result.class),
            @ApiResponse(code = 403, message = "FORBIDDEN!", response = Result.class),
            @ApiResponse(code = 404, message =  "NOT FOUND", response = Result.class),
            @ApiResponse(code = 500 , message = "INTERNAL SERVER ERROR", response = Result.class)
    })
    @CrossOrigin
    @DeleteMapping(value = "/users")
    public ResponseEntity<Result> deleteUser(@RequestParam(value = "id") Long id) throws Exception{

        String logID = generateLogID("DELETE an user by id");
        Timestamp startTime = startLog(logID);

        RequestValidator.checkParameter(id, "userid");

        userService.deleteUserById(id,logID);

        endLog(logID, startTime);
        return new ResponseEntity<>(new Result(0,"Operation completed successfully"), HttpStatus.OK);
    }

    @PostConstruct
    private void imAlive(){
        logger.info(" Prenotation Controller started");
    }

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
}