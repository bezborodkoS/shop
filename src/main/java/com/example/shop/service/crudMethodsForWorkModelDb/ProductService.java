package com.example.shop.service.crudMethodsForWorkModelDb;

import com.example.shop.model.Product;
import com.example.shop.repository.CategoryRepository;
import com.example.shop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Product> getAll() { return productRepository.findAll(); }
    public Optional<Product> getByName(String name) { return productRepository.findByName(name); }
    public Product save(Product product,String nameCategory) {
        if (!productRepository.findByName(product.getName()).isPresent()
                &&categoryRepository.findByName(nameCategory).isPresent()){
            product.setCategory(categoryRepository.findByName(nameCategory).get());
            return productRepository.save(product);
        }
        return null;
    }
    public boolean delete(Long id) {
        if (productRepository.findById(id).isPresent()){
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean delete(String name) {
        if (productRepository.findByName(name).isPresent()){
            productRepository.deleteById(productRepository.findByName(name).get().getId());
            return true;
        }
        return false;
    }
}
