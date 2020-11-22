package Elis.College.RegistroPranzi.service;

import Elis.College.RegistroPranzi.controller.PrenotationController;
import Elis.College.RegistroPranzi.exception.model.exceptionimpl.EntityNotFoundException;
import Elis.College.RegistroPranzi.exception.model.exceptionimpl.InternalServerErrorException;
import Elis.College.RegistroPranzi.model.LoginKey;
import Elis.College.RegistroPranzi.model.Register;
import Elis.College.RegistroPranzi.model.User;
import Elis.College.RegistroPranzi.repository.RegisterRepository;
import Elis.College.RegistroPranzi.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    //GET ALL USERS
    public List<User> getUsers(String logId) {

        List<User> userList= userRepository.findAll();
        logger.info(logId+ " Got result from db");
        return userList;
    }


    //CHECK AUTHENTICATION
    public User login(LoginKey loginKey, String logId) {

        User user = userRepository.getByEmailAndPassword(loginKey.getEmail(), loginKey.getPassword());
        logger.info(logId + " Got result from db");
        if (user == null)
            throw new EntityNotFoundException(HttpStatus.NOT_FOUND, 404, "Error", "The email or the password is wrong");
        return user;
    }

    //GET USER
    public User getUser(Long id, String logId) {

        User user = userRepository.getById(id);
        logger.info(logId + " Got result from db");
        if (user == null)
            throw new EntityNotFoundException(HttpStatus.NOT_FOUND, 404, "User", "User not found with id: " + id);

        return user;
    }

    //SAVE USER
    public User saveUser(User user, String logId) {

        userRepository.save(user);
        logger.info(logId + " Post repo done");
        User checkuser = userRepository.getByEmailAndPassword(user.getEmail(), user.getPassword());
        logger.info(logId + " Got result from db");
        if (checkuser == null)
            throw new InternalServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, 500, "User", "The server failed to perform the operation");
        return checkuser;
    }

    //DELETE USER
    public void deleteUserById(Long id, String logId) {

        registerRepository.deleteAllByUser_Id(id);
        logger.info(logId + " Delete register repo done");
        userRepository.deleteById(id);
        logger.info(logId + " Delete user repo done");

        List<Register> registerList = registerRepository.getAllByUser_id(id);
        User user = userRepository.getById(id);
        if (!registerList.isEmpty() || user != null)
            throw new InternalServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, 500, "User", "The server failed to perform the operation");
    }


}
