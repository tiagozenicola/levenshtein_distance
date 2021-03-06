package com.tiagozenicola.levenshtein.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiagozenicola.levenshtein.exception.ProductNotFoundException;
import com.tiagozenicola.levenshtein.helper.LevenshteinDistanceWithDynamicProgramming;
import com.tiagozenicola.levenshtein.helper.StringHelper;
import com.tiagozenicola.levenshtein.model.Product;
import com.tiagozenicola.levenshtein.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public Product create(Product product) {
        final String name = StringHelper.removeSpaces(product.getName());

        return repository.saveAndFlush(new Product(name));
    }

    public List<Product> read() {
        return repository.findAll();
    }

    public Product read(Long id) {
        final Optional<Product> product = repository.findById(id);
        
        return product.orElseThrow(() -> new ProductNotFoundException());
    }

    public List<Product> search(String word, int limit) {
        final List<Product> products = new ArrayList<>();

        for (Product product : repository.findAllByNameSize(word.length() - limit, word.length() + limit)) {
            if (LevenshteinDistanceWithDynamicProgramming.calculateDistance(word, product.getName()) <= limit) {
                products.add(product);
            }
        }

        return products;
    }

}