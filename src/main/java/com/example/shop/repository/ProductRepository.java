package com.example.shop.repository;

import com.example.shop.model.enity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByName(String name);
    List<Product> findAllByCategory_Name(String nameCategory);
    Optional<Product> findByProductCode(String productCode);
    Optional<Product> findByNameAndFabricator(String name,String fabricator);


}
