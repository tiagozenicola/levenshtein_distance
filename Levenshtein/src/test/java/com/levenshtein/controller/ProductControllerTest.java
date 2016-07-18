package com.levenshtein.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.ResultActions;

import com.google.gson.Gson;
import com.levenshtein.model.Product;
import com.levenshtein.repository.ProductRepository;

public class ProductControllerTest extends TestSuperClass {

    private static final String APPLICATION_JSON = "application/json";

    private static final String CONTENT_TYPE = "Content-Type";

    private static final String PRODUCTS = "/products";

    private Product product = new Product(productName);

    private String productName = "banana";

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void before() throws Exception {
        super.before();

        productRepository.deleteAll();
    }

    @Test
    public void createNewProduct() throws Exception {
        callService(product).andExpect(status().isOk());

        assertsForSuccess(productName);
    }

    @Test
    public void createProductWithDuplicatedName() throws Exception {
        callService(product).andExpect(status().isOk());
        callService(product).andExpect(status().isBadRequest());

        assertsForSuccess(productName);
    }

    @Test
    public void createProductWithNameEmpty() throws Exception {
        product.setName("");
        callService(product).andExpect(status().isBadRequest());

        assertsForFail();
    }

    @Test
    public void createProductWithNameNull() throws Exception {
        product.setName(null);
        callService(product).andExpect(status().isBadRequest());

        assertsForFail();
    }

    @Test
    public void read() throws Exception {
        mockMvc.perform(get(PRODUCTS)).andExpect(status().isOk());
    }

    private void assertsForFail() {
        Assert.assertEquals(0, productRepository.count());
    }

    private void assertsForSuccess(String productName) {
        Assert.assertEquals(1, productRepository.count());
        Assert.assertEquals(productName, productRepository.findAll().get(0).getName());
    }

    private ResultActions callService(Product product) throws Exception {
        final String productAsJson = new Gson().toJson(product, Product.class);
        return mockMvc.perform(post(PRODUCTS).header(CONTENT_TYPE, APPLICATION_JSON).content(productAsJson));
    }

}
