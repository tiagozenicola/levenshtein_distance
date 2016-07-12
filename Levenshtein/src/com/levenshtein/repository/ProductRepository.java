package com.levenshtein.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.levenshtein.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}