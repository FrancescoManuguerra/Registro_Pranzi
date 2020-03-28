package Elis.College.RegistroPranzi.repository;


import Elis.College.RegistroPranzi.model.Authority;
import Elis.College.RegistroPranzi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {

    User getByEmailAndPassword(String email,String password);

    void deleteById(Long id);
    User getById(Long id);
}
