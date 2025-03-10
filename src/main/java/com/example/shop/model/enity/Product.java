package com.example.shop.model.enity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "product")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private List<User> users;


    @PrePersist
    protected void autofillVWhenSaveNewProduct() {
        if (this.productCode == null || this.productCode.isEmpty()) {
            this.productCode = generateProductCode();
        }
    }

    private String generateProductCode() {
        return UUID.randomUUID().toString().toUpperCase().replace("-", "").substring(0, 25)
                .replaceAll("(.{5})", "$1-").substring(0, 29);
    }

}
