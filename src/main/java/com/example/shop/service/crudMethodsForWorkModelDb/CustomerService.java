package com.example.shop.service.crudMethodsForWorkModelDb;

import com.example.shop.model.enity.Customer;
import com.example.shop.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
//    private final PasswordEncoder passwordEncoder;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAll() { return customerRepository.findAll(); }
    public Optional<Customer> getByUsername(String username) { return customerRepository.findByUsername(username); }
    public Customer save(Customer customer) {
//        TODO coding password in customer
//        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customer.setRole("CUSTOMER");

        return customerRepository.save(customer);
    }
    public void delete(Long id) { customerRepository.deleteById(id); }
}
