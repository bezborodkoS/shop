package com.example.shop.service.crudMethodsForWorkModelDb;

import com.example.shop.converter.ProductConverter;
import com.example.shop.model.dto.ProductDto;
import com.example.shop.model.enity.Product;
import com.example.shop.repository.CategoryRepository;
import com.example.shop.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductConverter productConverter;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository, ProductConverter productConverter) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productConverter = productConverter;
    }

    public List<ProductDto> getAll() {
        return productConverter.toDTOList(productRepository.findAll());
    }

    public List<ProductDto> getAllByCategoryName(String nameCategory) {
        return productConverter.toDTOList(productRepository.findAllByCategory_Name(nameCategory));
    }

    public Optional<ProductDto> getByName(String name) {
        return Optional.of(productConverter.toDTO(productRepository.findByName(name).get()));
    }

    public Optional<ProductDto> getByNameAndFabricator(String name, String fabricator) {
        return Optional.of(productConverter.toDTO(productRepository.findByNameAndFabricator(name, fabricator).get()));
    }

    public Optional<ProductDto> getByProductCode(String productCode) {
        return Optional.of(productConverter.toDTO(productRepository.findByProductCode(productCode).get()));
    }

    public ProductDto save(ProductDto productDto, String nameCategory) {
        if (!productRepository.findByNameAndFabricator(productDto.getName(), productDto.getFabricator()).isPresent()
                && categoryRepository.findByName(nameCategory).isPresent()) {
            Product product = productConverter.toEntity(productDto);
            product.setCategory(categoryRepository.findByName(nameCategory).get());
            return productConverter.toDTO(productRepository.save(product));
        }
        return null;
    }

    public boolean delete(Long id) {
        if (productRepository.findById(id).isPresent()) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean delete(String productCode) {
        if (productRepository.findByProductCode(productCode).isPresent()) {
            productRepository.deleteById(productRepository.findByProductCode(productCode).get().getId());
            return true;
        }
        return false;
    }
}
