package com.example.shop.service.crudMethodsForWorkModelDb;

import com.example.shop.model.Category;
import com.example.shop.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public List<Category> getAll() { return repository.findAll(); }
    public Optional<Category> getByName(String name) { return repository.findByName(name); }
    public Category save(Category category) {
        if (repository.findByName(category.getName()).isPresent()){
            return null;
        }
        return repository.save(category);
    }

    public boolean delete(Long id) {
        if (repository.findById(id).isPresent()){
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean delete(String nameCategory) {
        if (repository.findByName(nameCategory).isPresent()){
            repository.deleteById(repository.findByName(nameCategory).get().getId());
            return true;
        }
        return false;
    }
}
