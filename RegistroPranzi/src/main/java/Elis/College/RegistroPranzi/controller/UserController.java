package Elis.College.RegistroPranzi.controller;

import Elis.College.RegistroPranzi.model.LoginKey;
import Elis.College.RegistroPranzi.model.User;
import Elis.College.RegistroPranzi.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    //GET ALL

    //GET USER FOR AUTHENTICATION
    @CrossOrigin
    @GetMapping(value="/login")
    public ResponseEntity<User> login(@RequestParam(name = "email",required = false)String email, @RequestParam(name = "password",required = false)String password){
        return new ResponseEntity<User>(userService.login(new LoginKey(email, DigestUtils.sha256Hex(password))), HttpStatus.OK);

    }
    //GET USER BY ID
    @CrossOrigin
    @GetMapping(value="users/{userid}")
    public ResponseEntity<User> getUser(@PathVariable(name = "userid") Long id){
            return new ResponseEntity<User>(userService.getUser(id),HttpStatus.OK);

    }

    //GET USERS
    @CrossOrigin
    @GetMapping(value = "/users")
    public ResponseEntity<List<User>> getUsers(){
        return new ResponseEntity<List<User>>(userService.getUsers(),HttpStatus.OK);
    }
    //SAVE USER
    @CrossOrigin
    @PostMapping(value = "/users",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> insertUser(@RequestBody(required = true) User user){

       return new ResponseEntity<>(userService.saveUser(User.builder().name(user.getName()).surname(user.getSurname()).number(user.getNumber()).email(user.getEmail()).password(DigestUtils.sha256Hex(user.getPassword())).lable_number(user.getLable_number()).build()),HttpStatus.OK);

    }
    //DELETE USER
    @CrossOrigin
    @DeleteMapping(value = "/users")
    public ResponseEntity deleteUser(@RequestParam(value = "id") Long id){
        userService.deleteUserById(id);
        return new ResponseEntity (HttpStatus.OK);
    }




}
