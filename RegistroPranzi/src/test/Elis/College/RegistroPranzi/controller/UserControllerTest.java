package Elis.College.RegistroPranzi.controller;

import Elis.College.RegistroPranzi.model.User;
import Elis.College.RegistroPranzi.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {

    @Autowired
    UserRepository userRepository;

    @Test
    void login() {
        assertEquals(4,2+2);
    }

    @org.junit.jupiter.api.Test
    void getUser() {
    }

    @org.junit.jupiter.api.Test
    void getUsers() {
    }

    @org.junit.jupiter.api.Test
    void insertUser() {
    }

    @org.junit.jupiter.api.Test
    void deleteUser() {
    }
}