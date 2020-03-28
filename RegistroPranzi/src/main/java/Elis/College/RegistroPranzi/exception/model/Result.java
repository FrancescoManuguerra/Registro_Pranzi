package Elis.College.RegistroPranzi.exception.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Objects;


@JsonPropertyOrder({ "ErrorCode", "Message"})
public class Result {
     private int errorCode;
     private String message;

     public Result() {
     }

    public Result(int errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    @JsonProperty("Message")
     public String getMessage() {
         return message;
     }
     public void setMessage(String message) {
         this.message = message;
     }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Result)) return false;
        Result result = (Result) o;
        return getErrorCode() == result.getErrorCode() &&
                Objects.equals(getMessage(), result.getMessage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getErrorCode(), getMessage());
    }
}