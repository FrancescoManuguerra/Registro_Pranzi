package Elis.College.RegistroPranzi.exception.model.exceptionimpl;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ServerErrorException;

import java.util.Objects;

public class InternalServerErrorException extends RuntimeException {

    private HttpStatus status;
    private int errorCode;
    private String entityName;
    private String message;

    public InternalServerErrorException() {
    }

    public InternalServerErrorException(HttpStatus status, int errorCode, String entityName, String message) {
        this.status = status;
        this.errorCode = errorCode;
        this.entityName = entityName;
        this.message = message;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InternalServerErrorException)) return false;
        InternalServerErrorException that = (InternalServerErrorException) o;
        return getErrorCode() == that.getErrorCode() &&
                getStatus() == that.getStatus() &&
                Objects.equals(getEntityName(), that.getEntityName()) &&
                Objects.equals(getMessage(), that.getMessage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStatus(), getErrorCode(), getEntityName(), getMessage());
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
