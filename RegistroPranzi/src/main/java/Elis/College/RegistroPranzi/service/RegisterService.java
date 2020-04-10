package Elis.College.RegistroPranzi.service;


import Elis.College.RegistroPranzi.model.Presence;
import Elis.College.RegistroPranzi.model.Register;
import Elis.College.RegistroPranzi.model.User;
import Elis.College.RegistroPranzi.repository.RegisterRepository;
import Elis.College.RegistroPranzi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service("RegisterService")
public class RegisterService {

    @Autowired
    RegisterRepository registerRepository;


    //TODO doesn't work
    //get prenotations by userId
    public List<Register> getAllByUser_id(Long userId){

        System.out.println("SERVICE : userId: "+userId);
        return registerRepository.findAllByUser_Id(userId);

    }

    //get prenotations by date
    public Presence countPrenotationsByDate(Date date){
        System.out.println("SERVICE: date: "+date);
        final Integer id=2;
        Presence presence= Presence.builder().totBreakfast(registerRepository.countAllByDateAndBreakfast_Id(date,id))
                .totLunch(registerRepository.countAllByDateAndLunch_Id(date,id))
                .totDinner(registerRepository.countAllByDateAndDinner_Id(date,id))
                .build();
        return presence ;
    }

    //save prenotation
    public void savePrenotation(Register register){
        System.out.println("Service now before register 1 ");
        System.out.println("REPOSITORY: GET DATE " + register.getDate());
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
