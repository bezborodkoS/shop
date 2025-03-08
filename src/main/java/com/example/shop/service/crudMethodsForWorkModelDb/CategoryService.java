package com.example.shop.service.crudMethodsForWorkModelDb;

import com.example.shop.converter.CategoryConverter;
import com.example.shop.model.dto.CategoryDto;
import com.example.shop.model.enity.Category;
import com.example.shop.repository.CategoryRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private static final Logger logger = LoggerFactory.getLogger(CategoryService.class);

    private final CategoryRepository repository;
    private final CategoryConverter categoryConverter;

    public CategoryService(CategoryRepository repository, CategoryConverter categoryConverter) {
        this.repository = repository;
        this.categoryConverter = categoryConverter;
    }

    public List<CategoryDto> getAll() {
        try {
            return categoryConverter.toDTOList(repository.findAll());
        } catch (Exception e) {
            logger.error("Ошибка при получении списка категории", e);
            return Collections.emptyList();
        }
    }

    public Optional<CategoryDto> getByName(String name) {
        try {
            return Optional.of(categoryConverter.toDTO(repository.findByName(name).get()));
        } catch (Exception e) {
            logger.error("Ошибка при получении категории", e);
            return Optional.empty();
        }
    }

    public Category save(CategoryDto categoryDto) {
        try {
            if (repository.findByName(categoryDto.getName()).isPresent()) {
                return null;
            }
            return repository.save(categoryConverter.toEntity(categoryDto));
        } catch (Exception e) {
            logger.error("Ошибка при сохранении категории", e);
            return null;
        }
    }

    public boolean delete(Long id) {
        try {
            if (repository.findById(id).isPresent()) {
                repository.deleteById(id);
                return true;
            }
            return false;
        } catch (Exception e) {
            logger.error("Ошибка при удалении категории", e);
            return false;
        }
    }

    public boolean delete(String nameCategory) {
        try {
            if (repository.findByName(nameCategory).isPresent()) {
                repository.deleteById(repository.findByName(nameCategory).get().getId());
                return true;
            }
            return false;
        } catch (Exception e) {
            logger.error("Ошибка при удалении категории", e);
            return false;
        }
    }
}
