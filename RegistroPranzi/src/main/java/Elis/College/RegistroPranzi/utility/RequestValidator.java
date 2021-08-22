package Elis.College.RegistroPranzi.utility;

import Elis.College.RegistroPranzi.exception.model.exceptionimpl.InputParameterException;
import Elis.College.RegistroPranzi.model.Register;
import Elis.College.RegistroPranzi.model.User;
import org.apache.commons.validator.routines.DateValidator;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;

import static Elis.College.RegistroPranzi.utility.Utilities.isNullorEmpty;

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
            throw new InputParameterException(HttpStatus.BAD_REQUEST, 400, "ERROR", "User id cannot be <= 0");
    }

    public static void checkBody(User user) throws InputParameterException {

        isValidEmailAddress(user.getEmail());

        if (isNullorEmpty(user.getName()))
            throw new InputParameterException(HttpStatus.BAD_REQUEST, 400, "ERROR", "The body must have 'name' as parameter");
        if (isNullorEmpty(user.getSurname()))
            throw new InputParameterException(HttpStatus.BAD_REQUEST, 400, "ERROR", "The body must have 'surname' as parameter");
        if (isNullorEmpty(user.getNumber()))
            throw new InputParameterException(HttpStatus.BAD_REQUEST, 400, "ERROR", "The body must have 'number' as parameter");
        if (isNullorEmpty(user.getPassword()))
            throw new InputParameterException(HttpStatus.BAD_REQUEST, 400, "ERROR", "The body must have 'password' as parameter");
        if (user.getLable_number() == null)
            throw new InputParameterException(HttpStatus.BAD_REQUEST, 400, "ERROR", "The body must have 'lable_number' as parameter");
        if (user.getLable_number() <= 0)
            throw new InputParameterException(HttpStatus.BAD_REQUEST, 400, "ERROR", "The 'lable_number' paramenter must be greater then 0");

    }

    public static void checkBody(Register register) throws InputParameterException {

        System.out.println(register.getDate() + " id:" + register.getId() + " dinner " + register.getDinner() + " lunch " + register.getLunch());

        if (register.getDate() == null) throw new InputParameterException(HttpStatus.BAD_REQUEST, 400, "ERROR", "The body must have 'date' parameter");
        //if (register.getId() == null) throw new InputParameterException(HttpStatus.BAD_REQUEST, 400, "ERROR", "The body must have 'id' parameter");
        if(register.getUser().getId() == null ) throw new InputParameterException(HttpStatus.BAD_REQUEST, 400, "ERROR", "The body must have user 'id' parameter");
        if( isNullorEmpty(register.getDinner()))throw new InputParameterException(HttpStatus.BAD_REQUEST, 400, "ERROR", "The body must have 'dinner' parameter");
        if( isNullorEmpty(register.getLunch())) throw new InputParameterException(HttpStatus.BAD_REQUEST, 400, "ERROR", "The body must have 'lunch' parameter");

    }

    //To check login parameters
    public static void checkLoginParameter(String email, String password) throws MissingServletRequestParameterException, InputParameterException {
        checkParameter(email, "email");
        checkParameter(password, "password");

    }

    //To check if the email format is correct
    public static void isValidEmailAddress(String email) throws InputParameterException {

        if (!EmailValidator.getInstance().isValid(email))
            throw new InputParameterException(HttpStatus.BAD_REQUEST, 400, "ERROR", "Email format is not valid");
    }

    public static void isDateMonth(String date ) throws InputParameterException {

        if(date.length()>7) throw new  InputParameterException(HttpStatus.BAD_REQUEST, 400, "Error","Date parameter format must be 'yyyy-mm'");
        DateValidator dateValidator = new DateValidatorUsingDateFormat("yyyy-MM-dd");

        dateValidator.isValid(date+"-01");
    }
}
