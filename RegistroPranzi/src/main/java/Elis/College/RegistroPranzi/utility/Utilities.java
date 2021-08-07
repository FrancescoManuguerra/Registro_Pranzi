package Elis.College.RegistroPranzi.utility;

import org.springframework.context.annotation.Bean;

public class Utilities {

    public static boolean isNullorEmpty(String element){

        return element == null || element.isEmpty();

    }



}
