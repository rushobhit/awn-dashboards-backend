package com.awn.awndashboards.product.repository;

import com.awn.awndashboards.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}