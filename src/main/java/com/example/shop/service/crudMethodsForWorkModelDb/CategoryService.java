package com.example.shop.service.crudMethodsForWorkModelDb;

import com.example.shop.converter.CategoryConverter;
import com.example.shop.model.dto.CategoryDto;
import com.example.shop.model.enity.Category;
import com.example.shop.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository repository;
    private final CategoryConverter categoryConverter;

    public CategoryService(CategoryRepository repository, CategoryConverter categoryConverter) {
        this.repository = repository;
        this.categoryConverter = categoryConverter;
    }

    public List<CategoryDto> getAll() {
        return categoryConverter.toDTOList(repository.findAll());
    }

    public Optional<CategoryDto> getByName(String name) {
            return Optional.of(categoryConverter.toDTO(repository.findByName(name).get()));
    }

    public Category save(CategoryDto categoryDto) {
        if (repository.findByName(categoryDto.getName()).isPresent()) {
            return null;
        }
        return repository.save(categoryConverter.toEntity(categoryDto));
    }

    public boolean delete(Long id) {
        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean delete(String nameCategory) {
        if (repository.findByName(nameCategory).isPresent()) {
            repository.deleteById(repository.findByName(nameCategory).get().getId());
            return true;
        }
        return false;
    }
}
