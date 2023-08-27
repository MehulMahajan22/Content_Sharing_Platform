package com.CSP.Authentication.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Incorrect password entered")
public class IncorrectPassword extends Exception{
    public IncorrectPassword(){
        super("Incorrect password entered");
    }
}
