package Elis.College.RegistroPranzi.repository;


import Elis.College.RegistroPranzi.model.Register;
import Elis.College.RegistroPranzi.model.State;
import Elis.College.RegistroPranzi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface RegisterRepository extends JpaRepository<Register,Long> {

    //get prenotations by userId
    @Query(value=" select e from Register e where e.date<=CURRENT_DATE and e.user.id=:userid" )
    List<Register> getPrenotationsById(Long userid);

    List<Register> getAllByUser_id(Long userId);
    //get prenotations by date
    Integer countAllByDate(Date date);

    Integer countAllByDateAndDinner(Date data, State state);

    Integer countAllByDateAndLunch(Date data, State state);

    //set prenotation
    Register getAllByUser_idAndAndDate(Long userId, Date date);

    //delete all prenotations
    void deleteAllByUser_Id(Long userId);



}
