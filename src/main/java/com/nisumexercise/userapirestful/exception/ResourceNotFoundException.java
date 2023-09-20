package com.nisumexercise.userapirestful.exception;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(final String message) {
        super(message);
    }
}
