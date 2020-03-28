package Elis.College.RegistroPranzi.exception.service;

import Elis.College.RegistroPranzi.exception.*;
import Elis.College.RegistroPranzi.exception.model.Headers;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.MissingServletRequestParameterException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class HeaderService {

    public static void isHeaderValid(HttpHeaders httpHeaders) throws MissingServletRequestParameterException {
        Map<String,String> headerMap = httpHeaders.toSingleValueMap();
        Map<String, String> headerMapIgnoreCase = new HashMap<String, String>();
        for (Map.Entry<String, String> entry : headerMap.entrySet()) {
            headerMapIgnoreCase.put(entry.getKey().toLowerCase(), entry.getValue());
        }
        for(Headers header: Headers.values()) {
            if (! headerMapIgnoreCase.containsKey(header.headerName().toLowerCase())) {
                throw new MissingServletRequestParameterException(header.headerName(), "header");
            }
        }
    }

    public static HttpHeaders fillResponseHeaders(HttpHeaders httpRequestHeader) {
        HttpHeaders httpResponseHeaders = new HttpHeaders();
        Map<String,String> headerMap = httpRequestHeader.toSingleValueMap();
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String start_date_string = df.format(new Date());

        httpResponseHeaders.set(Headers.TransactionID.headerName(), headerMap.get(Headers.TransactionID.headerName()));
        httpResponseHeaders.set(Headers.DateTimeMessage.headerName(), start_date_string);
        httpResponseHeaders.set(Headers.Operation.headerName(), headerMap.get(Headers.Operation.headerName()));
        httpResponseHeaders.set(Headers.Functionality.headerName(), headerMap.get(Headers.Functionality.headerName()));
        httpResponseHeaders.set(Headers.ClientSystem.headerName(), headerMap.get(Headers.ClientSystem.headerName()));
        httpResponseHeaders.set(Headers.ServerSystem.headerName(), headerMap.get(Headers.ServerSystem.headerName()));

        return httpResponseHeaders;

    }
}
