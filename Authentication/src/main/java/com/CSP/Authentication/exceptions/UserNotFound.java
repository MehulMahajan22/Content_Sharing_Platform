package com.CSP.Authentication.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "User not found")
public class UserNotFound extends Exception{
    public UserNotFound(){
        super("User not found");
    }
}
