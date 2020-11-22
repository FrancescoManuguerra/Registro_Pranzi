package Elis.College.RegistroPranzi.service;

import Elis.College.RegistroPranzi.controller.PrenotationController;
import Elis.College.RegistroPranzi.exception.model.exceptionimpl.EntityNotFoundException;
import Elis.College.RegistroPranzi.model.Register;
import Elis.College.RegistroPranzi.repository.RegisterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service("RegisterService")
public class RegisterService {

    @Autowired
    RegisterRepository registerRepository;

    private final Logger logger = LoggerFactory.getLogger(RegisterService.class);

    //TODO doesn't work
    //get prenotations by userId
    public List<Register> getAllByUser_id(Long userId, String logId){

        List<Register> registerList = registerRepository.findAllByUser_Id(userId);
        logger.info(logId+ " Got result from db");
        if (registerList.isEmpty()) throw new EntityNotFoundException(HttpStatus.NOT_FOUND, 404,"Registration","No prenotation for this userId: "+userId );

        return registerList ;
    }

    //get prenotations by date
    public Integer countPrenotationsByDate(Date date, String logId){

        Integer value = registerRepository.countAllByDate(date);
        logger.info(logId+ "Got result from db");
        return value;
    }

    //save prenotation
    public void savePrenotation(Register register,String logId){

        if(registerRepository.getAllByUser_idAndAndDate(register.getUser().getId(),register.getDate())==null) registerRepository.save(register);
        else {
            Register register1= registerRepository.getAllByUser_idAndAndDate(register.getUser().getId(),register.getDate());
            register1.setBreakfast(register.getBreakfast());
            register1.setDinner(register.getDinner());
            register1.setLunch(register.getLunch());

            registerRepository.save(register1);
        }
    }
}
