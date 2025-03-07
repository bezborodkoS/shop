package com.example.shop.converter;

import com.example.shop.model.dto.ProductDto;
import com.example.shop.model.enity.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductConverter {
    public ProductDto toDTO(Product product) {
        ProductDto dto = new ProductDto();
        dto.setName(product.getName());
        dto.setFabricator(product.getFabricator());
        dto.setProductCode(product.getProductCode());
        dto.setPrice(product.getPrice());
        dto.setCategoryName(product.getName());
        return dto;
    }

    public Product toEntity(ProductDto dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setFabricator(dto.getFabricator());
        product.setPrice(dto.getPrice());
        return product;
    }

    public List<ProductDto> toDTOList(List<Product> productList) {
        return productList.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<Product> toEntityList(List<ProductDto> dtoList) {
        return dtoList.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
