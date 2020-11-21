package Elis.College.RegistroPranzi.controller;

import Elis.College.RegistroPranzi.exception.model.exceptionimpl.InputParameterException;
import Elis.College.RegistroPranzi.model.Presence;
import Elis.College.RegistroPranzi.model.Register;
import Elis.College.RegistroPranzi.model.User;
import Elis.College.RegistroPranzi.service.RegisterService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.text.DateFormat;
import java.util.List;

@RestController
public class PrenotationController {

    @Autowired
    RegisterService registerService;

    //get prenotations by id
    @ApiOperation(value = "//prenotations/{userId}", produces = MediaType.APPLICATION_JSON_VALUE, notes = "Get prenotations by user ID ")
    @CrossOrigin
    @GetMapping(value = "/prenotations/{userId}")
    public ResponseEntity<List<Register>> getAllByUser_id(@PathVariable(name = "userId") Long userId) throws InputParameterException {

         System.out.println("Controller: "+ userId);
         return new ResponseEntity<>(registerService.getAllByUser_id(userId),HttpStatus.OK);

    }

    //TO GET PRENOTATIONS FOR A GIVEN DATE
    @ApiOperation(value = "/prenotations", produces = MediaType.APPLICATION_JSON_VALUE, notes = "Get prenotations by date ")
    @CrossOrigin
    @GetMapping(value="/prenotations")
    public ResponseEntity<Presence> countPrenotationByDate(@RequestParam(value = "date") Date date){

        System.out.println("ConTROLLER: date: "+date );
        return new ResponseEntity<Presence>(registerService.countPrenotationsByDate(date), HttpStatus.OK);

    }

    //TO SAVE A PRENOTATION
    @ApiOperation(value = "/prenotations", produces = MediaType.APPLICATION_JSON_VALUE, notes = "Post prenotation" )
    @CrossOrigin
    @PutMapping(value = "/prenotations")
    public ResponseEntity savePrenotation(@RequestBody Register register){

        System.out.println("Controller prenotations: register.date: "+ register.getDate());
        registerService.savePrenotation(register);
        return new ResponseEntity(HttpStatus.OK);

    }
}
