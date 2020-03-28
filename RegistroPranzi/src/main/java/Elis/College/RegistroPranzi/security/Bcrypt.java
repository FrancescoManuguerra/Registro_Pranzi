/*package Elis.College.RegistroPranzi.security;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class Bcrypt {


    public String encryptPassword(String password){
        return BCrypt.hashpw(password,BCrypt.gensalt(10));
    }

    public boolean checkPass(String password,String hashedPassw){
        if(BCrypt.checkpw(password, hashedPassw)) return true;
        return false;
    }
}
*/