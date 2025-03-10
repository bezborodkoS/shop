package com.example.shop.controller;

import com.example.shop.model.enity.User;
import com.example.shop.service.authAndLogin.UserServiceAuth;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserServiceAuth userServiceAuth;

    public AuthController(UserServiceAuth userServiceAuth) {
        this.userServiceAuth = userServiceAuth;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User userInp) {
        System.out.println(userInp.getUsername()+"/////////");
        return new ResponseEntity<>(userServiceAuth.login(userInp), HttpStatus.OK);
    }

}
