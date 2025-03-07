package com.example.shop.model.dto;

import com.example.shop.model.enity.Category;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDto {
    private String name;
    private String fabricator;
    private String productCode;
    private BigDecimal price;
    private String categoryName;


}
