package Elis.College.RegistroPranzi.exception.model.exceptionimpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.io.UnsupportedEncodingException;
import java.time.Instant;

public class ResponseHeaderFiller {
    public static HttpHeaders setHeaders (Object response) {
        HttpHeaders headers = new HttpHeaders();
        headers.setDate(Instant.now().toEpochMilli());

        try {
            ObjectMapper Obj = new ObjectMapper();
            String jsonStr = Obj.writeValueAsString(response);
            byte[] responsebyte = jsonStr.getBytes("UTF-8");
            headers.setContentLength(responsebyte.length);
        }
        catch ( com.fasterxml.jackson.core.JsonProcessingException | UnsupportedEncodingException e) {
        }
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
