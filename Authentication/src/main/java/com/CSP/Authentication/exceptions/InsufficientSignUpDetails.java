package com.CSP.Authentication.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Insufficient details for adding user")
public class InsufficientSignUpDetails extends Exception{
    public InsufficientSignUpDetails(){
        super("Insufficient details for adding user");
    }
}
