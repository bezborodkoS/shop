package com.example.shop.service.crudMethodsForWorkModelDb;


import com.example.shop.converter.ProductConverter;
import com.example.shop.model.dto.ProductDto;
import com.example.shop.model.enity.Product;
import com.example.shop.repository.CategoryRepository;
import com.example.shop.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductConverter productConverter;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository, ProductConverter productConverter) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productConverter = productConverter;
    }

    public List<ProductDto> getAll() {
        try {
            return productConverter.toDTOList(productRepository.findAll());
        } catch (Exception e) {
            logger.error("Ошибка при получении списка продуктов", e);
            return Collections.emptyList();
        }
    }

    public List<ProductDto> getAllByCategoryName(String nameCategory) {
        try {
            return productConverter.toDTOList(productRepository.findAllByCategory_Name(nameCategory));
        } catch (Exception e) {
            logger.error("Ошибка при получении списка продуктов", e);
            return Collections.emptyList();
        }
    }

    public Optional<ProductDto> getByName(String name) {
        try {
            return Optional.of(productConverter.toDTO(productRepository.findByName(name).get()));
        } catch (Exception e) {
            logger.error("Ошибка при получении продукта", e);
            return Optional.empty();
        }
    }

    public Optional<ProductDto> getByNameAndFabricator(String name, String fabricator) {
        try {
            return Optional.of(productConverter.toDTO(productRepository.findByNameAndFabricator(name, fabricator).get()));
        } catch (Exception e) {
            logger.error("Ошибка при получении продукта", e);
            return Optional.empty();
        }
    }

    public Optional<ProductDto> getByProductCode(String productCode) {
        try {
            return Optional.of(productConverter.toDTO(productRepository.findByProductCode(productCode).get()));
        } catch (Exception e) {
            logger.error("Ошибка при получении продукта", e);
            return Optional.empty();
        }
    }

    public ProductDto save(ProductDto productDto, String nameCategory) {
        try {
            if (!productRepository.findByNameAndFabricator(productDto.getName(), productDto.getFabricator()).isPresent()
                    && categoryRepository.findByName(nameCategory).isPresent()) {
                Product product = productConverter.toEntity(productDto);
                product.setCategory(categoryRepository.findByName(nameCategory).get());
                return productConverter.toDTO(productRepository.save(product));
            }
            return null;
        } catch (Exception e) {
            logger.error("Ошибка при сохранении продукта", e);
            return null;
        }
    }

    public boolean delete(Long id) {
        try {
            if (productRepository.findById(id).isPresent()) {
                productRepository.deleteById(id);
                return true;
            }
            return false;
        } catch (Exception e) {
            logger.error("Ошибка при удалении продукта", e);
            return false;
        }
    }

    public boolean delete(String productCode) {
        try {
            if (productRepository.findByProductCode(productCode).isPresent()) {
                productRepository.deleteById(productRepository.findByProductCode(productCode).get().getId());
                return true;
            }
            return false;
        } catch (Exception e) {
            logger.error("Ошибка при удалении продукта", e);
            return false;
        }
    }
}
