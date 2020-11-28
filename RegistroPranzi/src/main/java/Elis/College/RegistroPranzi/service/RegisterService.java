package Elis.College.RegistroPranzi.service;

import Elis.College.RegistroPranzi.controller.PrenotationController;
import Elis.College.RegistroPranzi.exception.model.exceptionimpl.EntityNotFoundException;
import Elis.College.RegistroPranzi.model.DailyPrenotation;
import Elis.College.RegistroPranzi.model.Register;
import Elis.College.RegistroPranzi.model.State;
import Elis.College.RegistroPranzi.repository.RegisterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

@Service("RegisterService")
public class RegisterService {

    @Autowired
    RegisterRepository registerRepository;

    private final Logger logger = LoggerFactory.getLogger(RegisterService.class);


    //get prenotations by userId
    public List<Register> getAllByUser_id(Long userId, String logId){

        List<Register> registerList = registerRepository.getPrenotationsById(userId);

        //TODO change date format

        /*for(Register register : registerList ){
            register.setUser(null);
        }*/

        registerList.forEach( element -> element.setUser(null) );

        logger.info(logId+ " Got result from db");
        if (registerList.isEmpty()) throw new EntityNotFoundException(HttpStatus.NOT_FOUND, 404,"Registration","No prenotation for this userId: "+userId );

        return registerList ;
    }

    //get prenotations by date
    public DailyPrenotation countPrenotationsByDate(Date date, String logId){

        //Integer value = registerRepository.countAllByDate(date);
        Integer lunchvalue= registerRepository.countAllByDateAndLunch(date, new State(1,"Prenotato"));
        Integer dinnervalue= registerRepository.countAllByDateAndDinner(date, new State(1,"Prenotato"));
        logger.info(logId+ "Got result from db");
        return new DailyPrenotation(lunchvalue, dinnervalue);
    }

    //save prenotation
    public void savePrenotation(Register register,String logId){

        if(registerRepository.getAllByUser_idAndAndDate(register.getUser().getId(),register.getDate())==null) registerRepository.save(register);
        else {
            Register register1= registerRepository.getAllByUser_idAndAndDate(register.getUser().getId(),register.getDate());
            register1.setDinner(register.getDinner());
            register1.setLunch(register.getLunch());

            registerRepository.save(register1);
        }
    }
}
