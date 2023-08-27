package com.CSP.Authentication.repository;

import com.CSP.Authentication.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

    User findByContact(long contact);

}
