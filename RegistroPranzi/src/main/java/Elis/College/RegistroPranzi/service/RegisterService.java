package Elis.College.RegistroPranzi.service;

import Elis.College.RegistroPranzi.exception.model.exceptionimpl.EntityNotFoundException;
import Elis.College.RegistroPranzi.model.DailyPrenotation;
import Elis.College.RegistroPranzi.model.MonthlyPrenotation;
import Elis.College.RegistroPranzi.model.Register;
import Elis.College.RegistroPranzi.model.State;
import Elis.College.RegistroPranzi.repository.RegisterRepository;
import Elis.College.RegistroPranzi.utility.Utilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static Elis.College.RegistroPranzi.utility.Utilities.getFirstDateOfMonth;
import static Elis.College.RegistroPranzi.utility.Utilities.getLastDateOfMonth;

@Service("RegisterService")
public class RegisterService {

    @Autowired
    RegisterRepository registerRepository;

    private final Logger logger = LoggerFactory.getLogger(RegisterService.class);


    public List<Register> getAllPrenotations(Integer pageNo, Integer pageSize, String sortBy){

        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<Register> pagedResult = registerRepository.findAll(paging);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<>();
        }
    }


    //get prenotations by userId
    public List<Register> getAllByUser_id(Long userId, String logId) {


        List<Register> registerList = registerRepository.getPrenotationsById(userId);

        registerList.forEach(element -> element.setUser(null));

        logger.info(logId + " Got result from db");
        if (registerList.isEmpty())
            throw new EntityNotFoundException(HttpStatus.NOT_FOUND, 404, "Registration", "No prenotation for the userId : " + userId);

        return registerList;
    }


    //get prenotations by date
    public DailyPrenotation countPrenotationsByDate(Date date, String logId) {
        
        Integer lunchvalue = registerRepository.countAllByDateAndLunch(date, new State(1, "Prenotato"));
        Integer dinnervalue = registerRepository.countAllByDateAndDinner(date, new State(1, "Prenotato"));

        Integer lunchGuestValue = registerRepository.getNumberOfLunchGuest(date);
        Integer dinnerGuestValue = registerRepository.getNumberOfDinnerGuest(date);

        logger.info(logId + "Got result from db");
        return new DailyPrenotation(lunchvalue + lunchGuestValue, dinnervalue+ dinnerGuestValue);
    }

    //get prenotations by month
    public MonthlyPrenotation countPrenotationByMonth(Date date , String logId){


        java.util.Date startDate = getFirstDateOfMonth(date);
        java.util.Date endDate = getLastDateOfMonth(date);
        Integer lunchValue = registerRepository.monthlyLunchPrenotation(startDate, endDate);
        Integer dinnerValue = registerRepository.monthlyDinnerPrenotation(startDate, endDate);

        return  new MonthlyPrenotation(lunchValue,dinnerValue );
    }

    public List<Register> getMonthlyPrenotationById(Long id, String logId){

     java.util.Date startDate = Utilities.getCurrentDate();
     java.util.Date endDate = Utilities.getCurrentDatePlusOneMonth();

     List<Register> registerList = registerRepository.monthlyPrenotationsById(id,startDate,endDate);

     return registerList;

    }

    //save prenotation
    public Register savePrenotation(Register register, String logId) {

        if (registerRepository.getAllByUser_idAndAndDate(register.getUser().getId(), register.getDate()) == null)
            return registerRepository.save(register);
        else {
            Register register1 = registerRepository.getAllByUser_idAndAndDate(register.getUser().getId(), register.getDate());
            register1.setDinner(register.getDinner());
            register1.setLunch(register.getLunch());

            return registerRepository.save(register1);
        }
    }
}
