package com.CSP.Authentication.services;

import com.CSP.Authentication.domain.User;

import java.util.Map;

public interface UserService {
    public User signUp(User user) throws Exception;
    public Map<String, String> login(User user) throws Exception;
}
