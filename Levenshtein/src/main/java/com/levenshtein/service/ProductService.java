package com.levenshtein.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.levenshtein.helper.LevenshteinDistanceWithDynamicProgramming;
import com.levenshtein.model.Product;
import com.levenshtein.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public Product create(Product product) {
        final Product newProduct = new Product(product.getName());

        return repository.save(newProduct);
    }

    public List<Product> read() {
        return repository.findAll();
    }

    public List<Product> search(String word, int limit) {
        final List<Product> products = new ArrayList<>();

        for (Product product : repository.findAll()) {
            if (LevenshteinDistanceWithDynamicProgramming.calculateDistance(word, product.getName()) <= limit) {
                products.add(product);
            }
        }

        return products;
    }

}