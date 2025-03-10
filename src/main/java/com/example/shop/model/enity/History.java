package com.example.shop.model.enity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "history")
@Data
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String category;
    @Column(name = "name")
    private String nameProduct;
    private String fabricator;
    @Column(name = "product_code")
    private String productCode;
    private LocalDateTime purchaseDate = LocalDateTime.now();
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
