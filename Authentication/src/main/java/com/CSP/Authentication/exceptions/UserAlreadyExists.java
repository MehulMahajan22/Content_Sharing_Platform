package com.CSP.Authentication.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "User already exists")
public class UserAlreadyExists extends Exception{
    public UserAlreadyExists(){
        super("User already exists");
    }
}
