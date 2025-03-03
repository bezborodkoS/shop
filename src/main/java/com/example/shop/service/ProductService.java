package com.example.shop.service;

import com.example.shop.model.Product;
import com.example.shop.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAll() { return productRepository.findAll(); }
    public Product getByName(String name) { return productRepository.findByName(name); }
    public Product save(Product product) { return productRepository.save(product); }
    public void delete(Long id) { productRepository.deleteById(id); }
}
