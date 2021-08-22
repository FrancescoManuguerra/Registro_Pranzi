package Elis.College.RegistroPranzi.repository;

import Elis.College.RegistroPranzi.model.Register;
import Elis.College.RegistroPranzi.model.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface RegisterRepository extends JpaRepository<Register, Long> {

    //public final static String FIND_DAILY_PRENOTATIONS  = "SELECT sum(e) FROM ( SELECT count(e) as number FROM Register e WHERE e.dinner.id=1 and e.date=:date UNION SELECT guest_dinner_number as number FROM Register e WHERE e.dinner.id=1 AND e.date=:date)as total ";

    //get prenotations by userId
    @Query(value = "select e from Register e where e.date>=CURRENT_DATE and e.user.id=:userid")
    List<Register> getPrenotationsById(Long userid);

    List<Register> getAllByUser_id(Long userId);

    //get prenotations by date
    Integer countAllByDate(Date date);

    //get prenotations by month
    @Query(value = "select count(e) from Register e where e.lunch.id=1 and e.date>=:startDate and e.date<=:endDate")
    Integer monthlyLunchPrenotation(Date startDate, Date endDate);

    @Query(value = "select count(e) from Register e where e.dinner.id=1 and e.date>=:startDate and e.date<=:endDate")
    Integer monthlyDinnerPrenotation(Date startDate, Date endDate);

    @Query(value = "select e from Register e where e.user.id=:id and e.date>=:startDate and e.date<=:endDate ")
    List<Register> monthlyPrenotationsById(Long id, Date startDate, Date endDate);

    Integer countAllByDateAndDinner(Date data, State state);

    @Query(value = "Select sum(e.guestDinnerNumber) from Register e where e.date=:data and e.dinner.id=1 ")
    Integer getNumberOfDinnerGuest(Date data);

    @Query(value = "Select sum(e.guestLunchNumber) from Register e where e.date=:data and e.lunch.id=1 ")
    Integer getNumberOfLunchGuest(Date data);

    Integer countAllByDateAndLunch(Date data, State state);

    //set prenotation
    Register getAllByUser_idAndAndDate(Long userId, Date date);

    //delete all prenotations
    void deleteAllByUser_Id(Long userId);

    //QUERY
    //@Query(FIND_DAILY_PRENOTATIONS)
    //Integer getDailyPrenotations(Date date);

}
