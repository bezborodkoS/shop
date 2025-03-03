package com.example.shop.service;

import com.example.shop.model.Category;
import com.example.shop.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public List<Category> getAll() { return repository.findAll(); }
    public Category getByName(String name) { return repository.findByName(name); }
    public Category save(Category category) { return repository.save(category); }
    public void delete(Long id) { repository.deleteById(id); }
}
