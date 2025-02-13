package com.elengel.api.fullstack.persistence.repository;

import com.elengel.api.fullstack.persistence.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
