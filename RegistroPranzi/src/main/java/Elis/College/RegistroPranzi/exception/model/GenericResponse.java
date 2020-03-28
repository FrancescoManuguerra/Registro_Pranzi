package Elis.College.RegistroPranzi.exception.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Objects;

@JsonPropertyOrder({ "Result", "Generic"})
public class GenericResponse {

    @JsonInclude(JsonInclude.Include.ALWAYS)
    private Result result;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object generic;

    @JsonProperty("Result")
    public Result getResult() {
        return result;
    }
    public void setResult(Result result) {
        this.result = result;
    }

    @JsonProperty("Generic")
    public Object getGeneric() {
        return generic;
    }
    public void setGeneric(Object generic) {
        this.generic = generic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GenericResponse)) return false;
        GenericResponse that = (GenericResponse) o;
        return Objects.equals(getResult(), that.getResult()) &&
                Objects.equals(getGeneric(), that.getGeneric());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getResult(), getGeneric());
    }
}
