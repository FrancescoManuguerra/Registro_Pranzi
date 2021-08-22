package Elis.College.RegistroPranzi.utility;

import Elis.College.RegistroPranzi.model.State;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class Utilities {

    public static boolean isNullorEmpty(String element) {

        return element == null || element.isEmpty();
    }

    public static boolean isNullorEmpty(State element) {

        return element == null || element.getId() == null;
    }

    public static Date getFirstDateOfMonth(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }

    public static java.sql.Date convertDateMonthToSqlDate(String date) throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date parsed = format.parse(date.concat("-01"));

        return new java.sql.Date(parsed.getTime());
    }

    public static Date getLastDateOfMonth(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }

    public static Date getCurrentDate(){

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return new Date(dtf.format(now));

    }
    public static Date getCurrentDatePlusOneMonth(){

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now().plusMonths(1);
        return new Date(dtf.format(now));

    }


}
