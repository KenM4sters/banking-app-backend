package com.practice.servingemail.service;

import com.practice.servingemail.domain.User;
import org.springframework.http.ResponseEntity;

public interface UserService {
    User saveUser(User user);
    Object loginUser(String Email, String password);

    User updateUser(String email, int transactionValue);
    Boolean verifyToken(String token);
}
