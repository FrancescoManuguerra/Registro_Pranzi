package Elis.College.RegistroPranzi.repository;


import Elis.College.RegistroPranzi.model.Register;
import Elis.College.RegistroPranzi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

public interface RegisterRepository extends JpaRepository<Register,Long> {

    //get prenotations by userId
    List<Register> findAllByUser_Id(Long userId);


    List<Register> getAllByUser_id(Long userId);
    //get prenotations by date
    Integer countAllByDate(Date date);

    //set prenotation
    Register getAllByUser_idAndAndDate(Long userId, Date date);

    //delete all prenotations
    void deleteAllByUser_Id(Long userId);



}
