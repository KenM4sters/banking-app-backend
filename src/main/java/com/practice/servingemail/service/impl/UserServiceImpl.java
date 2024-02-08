package com.practice.servingemail.service.impl;

import com.practice.servingemail.domain.Confirmation;
import com.practice.servingemail.domain.User;
import com.practice.servingemail.repo.ConfirmationRepo;
import com.practice.servingemail.repo.UserRepo;
import com.practice.servingemail.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final ConfirmationRepo confirmationRepo;
    @Override
    public User saveUser(User user) {
        if(userRepo.existByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists!");
        }
        user.setEnable(false);
        userRepo.save(user);

        Confirmation confirmation = new Confirmation(user);
        confirmationRepo.save(confirmation);


        return user;
    }

    @Override
    public Boolean verifyToken(String token) {
        Confirmation confirmation = confirmationRepo.findByToken(token);
        User user = userRepo.findByEmailIgnoreCase(confirmation.getUser().getEmail());
        if(user == null) throw new RuntimeException("No user exists by that token!");
        user.setEnable(true);
        userRepo.save(user);
        return Boolean.TRUE;
    }
}
