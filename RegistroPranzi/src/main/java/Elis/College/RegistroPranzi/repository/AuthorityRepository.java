package Elis.College.RegistroPranzi.repository;

import Elis.College.RegistroPranzi.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority,Long> {

    Authority getAllById(Long id);
}
