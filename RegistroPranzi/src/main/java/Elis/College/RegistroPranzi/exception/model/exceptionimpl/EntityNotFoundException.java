package Elis.College.RegistroPranzi.exception.model.exceptionimpl;

import org.springframework.http.HttpStatus;

import java.util.Objects;

public class EntityNotFoundException extends RuntimeException
{
    private HttpStatus status;
    private int errorCode;
    private String entityName;
    private String message;

    public EntityNotFoundException() {
    }

    public EntityNotFoundException(HttpStatus status, int errorCode, String entityName, String message) {
        this.status = status;
        this.errorCode = errorCode;
        this.entityName = entityName;
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

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
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
        if (!(o instanceof EntityNotFoundException)) return false;
        EntityNotFoundException that = (EntityNotFoundException) o;
        return getErrorCode() == that.getErrorCode() &&
                getStatus() == that.getStatus() &&
                Objects.equals(getEntityName(), that.getEntityName()) &&
                Objects.equals(getMessage(), that.getMessage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStatus(), getErrorCode(), getEntityName(), getMessage());
    }
}
