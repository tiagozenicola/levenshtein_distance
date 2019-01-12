package com.tiagozenicola.levenshtein.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tiagozenicola.levenshtein.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select p from Product p where length(p.name) between ?1 and ?2")
    List<Product> findAllByNameSize(int min, int max);

}