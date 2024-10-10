package com.ra.repository;

import com.ra.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsProductByProductName(String productName);
    List<Product> findProductByProductNameContainingIgnoreCase(String productName);
}
