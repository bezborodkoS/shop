package com.example.shop.service.authAndLogin;

import com.example.shop.model.enity.Product;
import com.example.shop.model.enity.User;
import com.example.shop.repository.UserRepository;
import com.example.shop.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class UserServiceAuth {
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserServiceAuth(JwtUtil jwtUtil, PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public String login(User userInp){
        System.out.println(" in service login");
        System.out.println(userInp.getUsername());
        System.out.println(userInp.getPassword());
        Optional<User> user = userRepository.findByUsername(userInp.getUsername());
        if (user.isPresent() ) {
            String token = jwtUtil.generateToken(userInp.getUsername());
            return token;
        }else {
            User newUser = new User();
            newUser.setUsername(userInp.getUsername());
            newUser.setPassword(passwordEncoder.encode(userInp.getPassword()));
            newUser.setRole("USER");
            newUser.setWallet(BigDecimal.valueOf(0));
            List<Product> productSet = new ArrayList<>();
            newUser.setProducts(productSet);
            userRepository.save(newUser);
            String token = jwtUtil.generateToken(userInp.getUsername());
            return token;
        }
    }
}
