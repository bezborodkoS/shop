package com.example.shop.repository;

import com.example.shop.model.enity.History;
import com.example.shop.model.enity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {
    List<History> findAllByUser_Username(String username);
}
