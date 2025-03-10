package com.example.shop.model.dto;

import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class HistoryDto {
    private String category;
    private String nameProduct;
    private String fabricator;
    private String productCode;
    private LocalDateTime purchaseDate = LocalDateTime.now();
}
