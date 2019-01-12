package com.tiagozenicola.levenshtein.controller;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.ResultActions;

import com.tiagozenicola.levenshtein.model.Product;
import com.tiagozenicola.levenshtein.repository.ProductRepository;

public class ProductReadingTest extends TestSuperClass {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void before() throws Exception {
        super.before();

        productRepository.deleteAll();
    }

    @Test
    public void getByIdFound() throws Exception {
        final ResultActions resultActions = callCreateService(new Product("blablabla"));

        final String id = getIdFromResponse(resultActions);

        mockMvc.perform(get(PRODUCTS + "/" + id))
            .andExpect(status().isOk())
            .andExpect(jsonPath("name").value("blablabla"));
    }

    @Test
    public void getByIdNotFound() throws Exception {
        final String id = "999999999";
        
        mockMvc.perform(get(PRODUCTS + "/" + id))
            .andExpect(status().isNotFound());
    }

    @Test
    public void listAllProductsWorksWithManyProducts() throws Exception {
        callCreateService(new Product("book"));
        callCreateService(new Product("apple"));
        callCreateService(new Product("pencil"));

        mockMvc.perform(get(PRODUCTS))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(3)))
            .andExpect(jsonPath("$[*].name", contains("book", "apple", "pencil")));
    }

    @Test
    public void listAllProductsWorksWithNoProducts() throws Exception {
        mockMvc.perform(get(PRODUCTS))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(0)));
    }

    private String getIdFromResponse(final ResultActions resultActions) {
        final String header = resultActions.andReturn().getResponse().getHeader("Location");
        return header.replaceAll(".*products/", "");
    }

}