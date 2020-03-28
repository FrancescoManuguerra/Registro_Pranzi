package Elis.College.RegistroPranzi.exception.model.exceptionimpl;

import org.springframework.http.HttpStatus;

import java.util.Objects;

public class InputParameterException extends Exception {

    private HttpStatus status;
    private int errorCode;
    private String parameter;
    private String message;

    public InputParameterException() {
    }

    public InputParameterException(HttpStatus status, int errorCode, String parameter, String message) {
        this.status = status;
        this.errorCode = errorCode;
        this.parameter = parameter;
        this.message = message;
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

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InputParameterException)) return false;
        InputParameterException that = (InputParameterException) o;
        return getErrorCode() == that.getErrorCode() &&
                getStatus() == that.getStatus() &&
                Objects.equals(getParameter(), that.getParameter()) &&
                Objects.equals(getMessage(), that.getMessage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStatus(), getErrorCode(), getParameter(), getMessage());
    }
}
