package com.practice.servingemail.controller;

import com.practice.servingemail.domain.HttpResponse;
import com.practice.servingemail.domain.User;
import com.practice.servingemail.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping
    public ResponseEntity<HttpResponse> createUser(@RequestBody User user) {
        User newUser = userService.saveUser(user);
        return ResponseEntity.created(URI.create(""))
                .body(HttpResponse
                        .builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .data(Map.of("user", newUser))
                        .message("user created")
                        .status(HttpStatus.CREATED)
                        .statusCode(HttpStatus.CREATED.value())
                        .build()
                );
    };

    @PutMapping
    public ResponseEntity<HttpResponse> updateUserMembers(@RequestParam("email") String email,
                                                          @RequestParam("transactionValue") int transactionValue) {
        User updatedUser = userService.updateUser(email, transactionValue);
        return ResponseEntity.created(URI.create(""))
                .body(HttpResponse
                        .builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .data(Map.of("user", updatedUser))
                        .message("user updated")
                        .status(HttpStatus.CREATED)
                        .statusCode(HttpStatus.CREATED.value())
                        .build()
                );
    };

    @GetMapping()
    public ResponseEntity<HttpResponse> confirmNewUserAcc(@RequestParam("token") String token) {
        Boolean isSuccess = userService.verifyToken(token);
        return ResponseEntity.ok()
                .body(HttpResponse
                        .builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .data(Map.of("Success", isSuccess))
                        .message("User Verified")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
                );
    };

    @GetMapping("/user")
    public ResponseEntity<Object> getUser(@RequestParam("email") String email,
                                        @RequestParam("password") String password ) {
        return ResponseEntity.ok().body(userService.loginUser(email, password));
    }
}
