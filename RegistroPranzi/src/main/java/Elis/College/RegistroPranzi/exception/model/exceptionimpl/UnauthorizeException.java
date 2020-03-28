package Elis.College.RegistroPranzi.exception.model.exceptionimpl;

import org.springframework.http.HttpStatus;

import javax.servlet.ServletException;
import java.util.Objects;

public class UnauthorizeException extends ServletException {
    private HttpStatus status;
    private int errorCode;
    private String message;

    public UnauthorizeException() {
    }

    public UnauthorizeException(HttpStatus status, int errorCode, String message) {
        this.status = status;
        this.errorCode = errorCode;
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
        if (!(o instanceof UnauthorizeException)) return false;
        UnauthorizeException that = (UnauthorizeException) o;
        return getErrorCode() == that.getErrorCode() &&
                getStatus() == that.getStatus() &&
                Objects.equals(getMessage(), that.getMessage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStatus(), getErrorCode(), getMessage());
    }
}
