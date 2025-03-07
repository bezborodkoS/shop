package com.example.shop.model.dto;

import com.example.shop.model.enity.Product;
import lombok.Data;

import java.util.List;

@Data
public class CategoryDto {
    private String name;
    private List<ProductDto> products;
}
