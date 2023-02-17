package com.epam.rd.autotasks.validations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EpamEmailValidation {

    public static boolean validateEpamEmail(String email) {
        if(email!=null){
            return email.matches("([a-zA-z])+[_]([a-zA-Z])+[0-9]*(@epam.com)");
        }
        return false;

    }
}





