package com.practice.servingemail.service;

import com.practice.servingemail.domain.User;

public interface UserService {
    User saveUser(User user);
    Boolean verifyToken(String token);
}
