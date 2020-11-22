package Elis.College.RegistroPranzi.utility;

import Elis.College.RegistroPranzi.RegistroPranziApplication;
import Elis.College.RegistroPranzi.exception.model.exceptionimpl.InputParameterException;
import Elis.College.RegistroPranzi.model.Register;
import Elis.College.RegistroPranzi.model.User;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;

import java.util.List;

public class RequestValidator {

    //To check if a parameter is null or empty
    public static void checkParameter(String parameter, String parameterName)
            throws MissingServletRequestParameterException {
        if (parameter == null || parameter.equals("")) {
            throw new MissingServletRequestParameterException(parameterName, "String");
        }
    }

    public static void checkParameter(Long parameter, String parameterName)
            throws MissingServletRequestParameterException {
        if (parameter == null) {
            throw new MissingServletRequestParameterException(parameterName, "Long");
        }
    }

    public static void checkParameter(Object parameter, String parameterName)
            throws MissingServletRequestParameterException {
        if (parameter == null) {
            throw new MissingServletRequestParameterException(parameterName, "Object");
        }
    }


    public static void checkUserId(Long parameter) throws InputParameterException {
        if (parameter <= 0)
            throw new InputParameterException(HttpStatus.BAD_REQUEST, 404, "ERROR", "User id cannot be <= 0");
    }

    public static void checkBody(User user) throws InputParameterException {

        if (user.getName().isEmpty() || user.getNumber().isEmpty() || user.getPassword().isEmpty() || user.getSurname().isEmpty() || user.getLable_number() <= 0)
            throw new InputParameterException(HttpStatus.BAD_REQUEST, 404, "ERROR", "The body must have all parameter");

    }

    public static void checkBody(Register register) throws InputParameterException {

        if (register.getBreakfast() == null || register.getDate() == null || register.getId() == null || register.getLunch() == null || register.getUser() == null)
            throw new InputParameterException(HttpStatus.BAD_REQUEST, 404, "ERROR", "The body must have all parameter");
    }

    //To check login parameters
    public static void checkLoginParameter(String email, String password) throws MissingServletRequestParameterException, InputParameterException {
        checkParameter(email, "email");
        checkParameter(password, "password");

        isValidEmailAddress(email);
    }

    //To check if the email format is correct
    public static void isValidEmailAddress(String email) throws InputParameterException {

        if (!EmailValidator.getInstance().isValid(email))
            throw new InputParameterException(HttpStatus.BAD_REQUEST, 400, "ERROR", "Email format is not valid");
    }

}
