package com.levenshtein.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.levenshtein.model.Product;
import com.levenshtein.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public List<Product> read() {
        return repository.findAll();
    }

    public Product create(String name) {
        final Product product = new Product();
        product.setName(name);

        return repository.save(product);
    }

}
