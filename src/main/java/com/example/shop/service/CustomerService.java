package com.example.shop.service;

import com.example.shop.model.Customer;
import com.example.shop.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAll() { return customerRepository.findAll(); }
    public Customer getByUsername(String username) { return customerRepository.findByUsername(username); }
    public Customer save(Customer customer) {
//        TODO coding password in customer
        return customerRepository.save(customer);
    }
    public void delete(Long id) { customerRepository.deleteById(id); }
}
