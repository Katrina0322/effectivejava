package com.oneapm.chap20;

import java.util.List;

/**
 * used to
 * Created by tianjin on 8/2/16.
 */
public class PasswordUtils {
    @UseCase(id = 47,description = "password must contain at least one numeric")
    public boolean validatePassword(String password){
        return password.matches("\\w*\\d\\w*");
    }

    @UseCase(id = 48)
    public String encryptPassword(String password){
        return new StringBuilder(password).reverse().toString();
    }

    @UseCase(id = 49,description = "new password cannot equals previously used ones")
    public boolean checkForNewPassword(List<String> prePassword, String password){
        return !prePassword.contains(password);
    }
}
