package Elis.College.RegistroPranzi.controller;

import Elis.College.RegistroPranzi.model.User;
import Elis.College.RegistroPranzi.repository.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserControllerTest {


    private String message;

    @Before
    void setUp(){}

    @Test
    void login(){}
    @Test
    void getUserById(){

     }

    void getUser() {
    }

    void getUsers() {
    }

    void insertUser() {
    }

    void deleteUser() {
    }
}