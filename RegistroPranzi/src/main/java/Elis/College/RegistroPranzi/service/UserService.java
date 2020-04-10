package Elis.College.RegistroPranzi.service;

import Elis.College.RegistroPranzi.exception.model.exceptionimpl.EntityNotFoundException;
import Elis.College.RegistroPranzi.exception.service.RestExceptionHandler;
import Elis.College.RegistroPranzi.model.LoginKey;
import Elis.College.RegistroPranzi.model.User;
import Elis.College.RegistroPranzi.repository.RegisterRepository;
import Elis.College.RegistroPranzi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RegisterRepository registerRepository;

    //GET ALL USERS
    @Cacheable(value = "articoli",sync = true)
    public List<User> getUsers(){
        return userRepository.findAll();
    }


    //CHECK AUTHENTICATION
    public User login(LoginKey loginKey) {

        User user= userRepository.getByEmailAndPassword(loginKey.getEmail(),loginKey.getPassword());
        if(user == null ) throw new EntityNotFoundException(HttpStatus.NOT_FOUND,404, "email or password","Wrong credential");
        return user;

    }

    //GET USER
    public User getUser(Long id){

        User user = userRepository.getById(id);
        if( user == null ) throw new EntityNotFoundException(HttpStatus.NOT_FOUND,404, "id","User not found");
        return user;

    }

    //SAVE USER
    public User saveUser(User user){

        userRepository.save(user);
        User checkuser= userRepository.getByEmailAndPassword(user.getEmail(),user.getPassword());
        //TODO manage exception
        if(checkuser == null) return null;
        return checkuser;

    }
    //DELETE USER
    public void deleteUserById(Long id){

            registerRepository.deleteAllByUser_Id(id);
            userRepository.deleteById(id);

    }





}
