package com.CSP.Authentication.services;

import com.CSP.Authentication.domain.User;
import com.CSP.Authentication.exceptions.IncorrectPassword;
import com.CSP.Authentication.exceptions.InsufficientSignUpDetails;
import com.CSP.Authentication.exceptions.UserAlreadyExists;
import com.CSP.Authentication.exceptions.UserNotFound;
import com.CSP.Authentication.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepo ur;

    @Autowired
    TokenGeneratorImpl tgi;

    //Add New User
    @Override
    public User signUp(User user) throws Exception {
        if (user.getContact()==Long.getLong(null) || user.getPassword()==null || user.getName()==null){
            throw new InsufficientSignUpDetails();
        }
        if (ur.findByContact(user.getContact())!=null){
            log.error("A user with given contact info already exists");
            throw new UserAlreadyExists();
        }
        else
            return ur.save(user) ;
    }

    //Login for existing User
    @Override
    public Map<String, String> login(User user) throws Exception {
        if (ur.findByContact(user.getContact())==null){
            log.error("No User was found with given details");
            throw new UserNotFound();
        }
        else {
            User check = ur.findByContact(user.getContact());
            if (check.getPassword().equals(user.getPassword())) {
                return tgi.generateToken(check);
            } else {
                log.error("Incorrect password");
                throw new IncorrectPassword();
            }
        }
    }
}
