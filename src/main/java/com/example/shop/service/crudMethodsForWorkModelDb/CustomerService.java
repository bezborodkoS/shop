package com.example.shop.service.crudMethodsForWorkModelDb;

import com.example.shop.model.enity.User;
import com.example.shop.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;

    public CustomerService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAll() { return userRepository.findAll(); }
    public Optional<User> getByUsername(String username) { return userRepository.findByUsername(username); }
    public User save(User user) {
//        TODO coding password in customer
//        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        user.setRole("CUSTOMER");

        return userRepository.save(user);
    }
    public void delete(Long id) { userRepository.deleteById(id); }
}
