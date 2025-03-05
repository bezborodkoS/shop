package com.example.shop.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "product")
@Data
public class Product {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String fabricator;

//    Code product must be in this format (XXXXX-XXXXX-XXXXX-XXXXX-XXXXX)
//    TODO create methods for generate auto "productCode" if don't give in requestBody
    @Column(name = "product_code")
    private String productCode;
    private BigDecimal price;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany(mappedBy = "products")
    private Set<Customer> customers;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<History> purchaseHistory;
}
