package com.CSP.postservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "Post not found")
public class PostNotFoundException extends Exception {
    public PostNotFoundException(){
        super("Post not found");
    }
}
