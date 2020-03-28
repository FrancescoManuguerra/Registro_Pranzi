package Elis.College.RegistroPranzi.controller;

import Elis.College.RegistroPranzi.exception.model.exceptionimpl.InputParameterException;
import Elis.College.RegistroPranzi.model.Register;
import Elis.College.RegistroPranzi.model.User;
import Elis.College.RegistroPranzi.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
public class PrenotationController {

    @Autowired
    RegisterService registerService;

    //TODO doesn't work
    //get prenotations by id
    @CrossOrigin
    @GetMapping(value = "/prenotations/{userId}")
    public ResponseEntity<List<Register>> getAllByUser_id(@PathVariable(name = "userId", required = true) Long userId) throws InputParameterException {
        System.out.println("Controller: "+ userId);

         return new ResponseEntity<>(registerService.getAllByUser_id(userId),HttpStatus.OK);

    }

    @CrossOrigin
    @GetMapping(value="/prenotations")
    public ResponseEntity<Integer> countPrenotationByDate(@RequestParam(value = "date" ,required = true) Date date){

        System.out.println("ConTROLLER: date: "+date );

        return new ResponseEntity<>(registerService.countPrenotationsByDate(date), HttpStatus.OK);
    }



    @CrossOrigin
    @PutMapping(value = "/prenotations")
    public ResponseEntity savePrenotation(@RequestBody Register register){
        System.out.println("Controller prenotations: register.date: "+ register.getDate());
        registerService.savePrenotation(register);
        return new ResponseEntity(HttpStatus.OK);
    }



}
