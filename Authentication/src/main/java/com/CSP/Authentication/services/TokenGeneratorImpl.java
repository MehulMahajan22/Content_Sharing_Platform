package com.CSP.Authentication.services;

import com.CSP.Authentication.domain.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
@Slf4j
public class TokenGeneratorImpl implements TokenGenerator{
    @Override
    public Map<String, String> generateToken(User user) {
        Map<String, Object> userData = new HashMap<>();
        Map<String, String> response = new HashMap<>();
        userData.put("Name",user.getName());
        userData.put("Contact",String.valueOf(user.getContact()));
        userData.put("Id", String.valueOf(user.getUser_id()));
        log.info("Creating Token, adding claims and signing with HS256 Algorithm");
        String token = Jwts.builder()
                .setClaims(userData)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256,"CSP_Auth")
                .compact();
        response.put("Token", token);
        response.put("Message","Logged in");
        return response;
    }
}
