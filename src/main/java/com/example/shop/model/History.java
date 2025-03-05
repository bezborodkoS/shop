package com.example.shop.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "history")
@Data
public class History {
    @Id
    @GeneratedValue
    private Long id;

    private String category;
    @Column(name = "name")
    private String nameProduct;
    private String fabricator;
    @Column(name = "product_code")
    private String productCode;
    private LocalDateTime purchaseDate = LocalDateTime.now();
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;
}
