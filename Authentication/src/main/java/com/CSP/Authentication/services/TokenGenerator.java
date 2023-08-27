package com.CSP.Authentication.services;

import com.CSP.Authentication.domain.User;

import java.util.Map;

public interface TokenGenerator {
    Map<String,String> generateToken(User user);
}
