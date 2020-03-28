package Elis.College.RegistroPranzi.exception.service;

import Elis.College.RegistroPranzi.exception.*;

import Elis.College.RegistroPranzi.exception.model.GenericResponse;
import Elis.College.RegistroPranzi.exception.model.Result;
import Elis.College.RegistroPranzi.exception.model.exceptionimpl.EntityNotFoundException;
import Elis.College.RegistroPranzi.exception.model.exceptionimpl.InputParameterException;
import Elis.College.RegistroPranzi.exception.model.exceptionimpl.UnauthorizeException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Logger;


@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {


    /**
     * Handler of MissingServletRequestParameterException (for example missing params that are
     * mandatory in the request
     * @param ex  Exception
     * @param headers
     * @param status
     * @param ex Http Request
     * @return A ResponseEntity with Bad_Request Status (400)
     */
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String name = ex.getParameterName();
        Result result = new Result(HttpStatus.BAD_REQUEST.value(),"ERROR. Missing mandatory field: " + name);

        return buildResponseEntity(result, request, HttpStatus.BAD_REQUEST);

    }

    /**
     *  Handler of generic exception response
     * @param ex Exception
     * @param ex Http Request
     * @return  A ResponseEntity with Internal_Server_Error Status (500)
     */
   @ExceptionHandler({Exception.class, IOException.class})
    public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {
        Result result = new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(),"INTERNAL ERROR");

     return buildResponseEntity(result, request, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    /**
     *
     * @param ex Exception
     * @param ex Http Request
     * @return  A ResponseEntity with Unauthorized Status (401)
     */
    @ExceptionHandler(UnauthorizeException.class)
    public ResponseEntity<Object> handleUnauthorize(UnauthorizeException ex, WebRequest request) {
        Result result = new Result(HttpStatus.UNAUTHORIZED.value(),ex.getMessage());

        return buildResponseEntity(result, request, HttpStatus.UNAUTHORIZED);
    }

    /**
     *
     * @param ex Exception
     * @param ex Http Request
     * @return
     */
    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex, WebRequest request) {
        Result result = new Result(ex.getErrorCode(),ex.getMessage());

        return buildResponseEntity(result, request, ex.getStatus());
    }

    /**
     *
     * @param ex Exception
     * @param ex Http Request
     * @return
     */
    @ExceptionHandler(InputParameterException.class)
    protected ResponseEntity<Object> handleInputParameterException (InputParameterException ex, WebRequest request) {
        Result result = new Result(ex.getErrorCode(),ex.getMessage());

        return buildResponseEntity(result, request, ex.getStatus());
    }

    /**
     *
     * @param ex Exception
     * @param ex Http Request
     * @return
     */
    public final ResponseEntity<Object> handleServletRequestBindingException(Exception ex, WebRequest request)
    {
        Result result = new Result(HttpStatus.BAD_REQUEST.value(),"missing header");

        return buildResponseEntity(result, request, HttpStatus.BAD_REQUEST);
    }

    /**
     * Builds the response entity in the
     * @param result
     * @param request
     * @param status
     * @return
     */
    private ResponseEntity<Object> buildResponseEntity(Result result, WebRequest request, HttpStatus status) {
        GenericResponse response = new GenericResponse();
        response.setResult(result);

        if(request==null) {
            return new ResponseEntity(response,status);
        }
        else {
            HttpHeaders headers = new HttpHeaders();
            Iterator iterator = request.getHeaderNames();
            while (iterator.hasNext()) {
                String key = (String) iterator.next();
                String value = request.getHeader(key);
                headers.add(key, value);
            }
            return new ResponseEntity(response, HeaderService.fillResponseHeaders(headers), status);
        }
    }

    /**
     * Logs the exceptions thrown
     * @param e
     * @param status
     */

}

