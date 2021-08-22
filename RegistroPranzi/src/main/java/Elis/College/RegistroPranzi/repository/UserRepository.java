package Elis.College.RegistroPranzi.repository;

import Elis.College.RegistroPranzi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User,Long> {

    User getByEmailAndPassword(String email,String password);
    
    @Transactional
    void deleteById(Long id);
    User getById(Long id);
}
