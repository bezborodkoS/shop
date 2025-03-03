package com.example.shop.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "product")
@Data
public class Product {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private double price;
    @ManyToOne
    private Category category;
}
