package com.CSP.postservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "No posts were found")
public class NoPostsFoundException extends Exception{
    public NoPostsFoundException(){
        super("No posts were found");
    }
}
