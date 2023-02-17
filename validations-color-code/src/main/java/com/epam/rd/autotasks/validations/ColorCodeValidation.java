package com.epam.rd.autotasks.validations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ColorCodeValidation {
    public static boolean validateColorCode(String color) {

        if (color != null) {
            return color.matches("^#([0-9a-fA-F]{3}){1,2}");
        }
        return false;

    }
}





