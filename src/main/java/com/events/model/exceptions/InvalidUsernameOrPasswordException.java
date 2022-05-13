package com.events.model.exceptions;

public class InvalidUsernameOrPasswordException extends RuntimeException{
    public InvalidUsernameOrPasswordException() {
        super("Invalid username and password exception");
    }
}
