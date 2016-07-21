package com.levenshtein.controller;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.levenshtein.model.Product;
import com.levenshtein.repository.ProductRepository;

public class ProductReadingTest extends TestSuperClass {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void before() throws Exception {
        super.before();

        productRepository.deleteAll();
    }

    @Test
    public void readWorksWithManyProducts() throws Exception {
        callCreateService(new Product("book"));
        callCreateService(new Product("apple"));
        callCreateService(new Product("pencil"));

        mockMvc.perform(get(PRODUCTS))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(3)))
            .andExpect(jsonPath("$[*].name", contains("book","apple","pencil")));
    }

    @Test
    public void readWorksWithNoProducts() throws Exception {
        mockMvc.perform(get(PRODUCTS))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(0)));
    }

}