package com.nisumexercise.userapirestful.validation;

import java.util.regex.Pattern;

public class EmailValidation {

    private final static String REGEX_PATTERN = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

    public static boolean patternMatches(String email) {
        return Pattern.compile(REGEX_PATTERN)
                .matcher(email)
                .matches();
    }
}
