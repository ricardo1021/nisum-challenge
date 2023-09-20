package com.nisumexercise.userapirestful.validation;

import java.util.regex.Pattern;

public class PasswordValidation {

    private final static String REGEX_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{2,20}$";

    public static boolean patternMatches(String password) {
        return Pattern.compile(REGEX_PATTERN)
                .matcher(password)
                .matches();
    }
}
