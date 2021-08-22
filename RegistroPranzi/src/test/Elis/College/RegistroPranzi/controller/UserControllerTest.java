package Elis.College.RegistroPranzi.controller;

import Elis.College.RegistroPranzi.repository.UserRepository;
import Elis.College.RegistroPranzi.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerTest {


    private final static Logger logger = LoggerFactory.getLogger(UserControllerTest.class);

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @MockBean
    UserService service;

    @InjectMocks
    UserController controller;

    @MockBean
    UserRepository userRepository;


    @Before
    public void setUp() throws Exception {

        //Inialization of mocks
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();


        //define the behavior of service.getTelemetry method
        //when(service.getTelemetry(any(), any(), any(), any()))
        //        .thenReturn(new ResponseEntity<>(measureDataResponse, HttpStatus.OK));

    }



    @Test
    public void getStatusTest() throws Exception {

        logger.info("GET STATUS");

        MvcResult result = mockMvc.perform(get("/api/status"))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals("API RegistroPranzi is running!", result.getResponse().getContentAsString());
    }


    /*
    @Test
    public void login(@RequestParam(name = "email", required = false) String email, @RequestParam(name = "password", required = false) String password) throws Exception {

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

    /*
    @Test
    public void getUser(@PathVariable(name = "userid") Long id) throws Exception {

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

    @Test
    public void getUsers() {

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

    @Test
    public void insertUser(@RequestBody User user) throws Exception {

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

    @Test
    public void deleteUser(@RequestParam(value = "id") Long id) throws Exception{

        String logID = generateLogID("DELETE an user by id");
        Timestamp startTime = startLog(logID);

        RequestValidator.checkParameter(id, "userid");

        userService.deleteUserById(id,logID);

        endLog(logID, startTime);
        return new ResponseEntity<>(new Result(0,"Operation completed successfully"), HttpStatus.OK);
    }

     */

}