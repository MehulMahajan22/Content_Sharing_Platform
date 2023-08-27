package com.CSP.postservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT,reason = "Unauthorized access")
public class UnAuthorizedAccessException extends Exception{
    public UnAuthorizedAccessException(){
        super("Unauthorized Access");
    }
}
