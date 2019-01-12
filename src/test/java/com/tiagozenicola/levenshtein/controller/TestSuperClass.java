package com.tiagozenicola.levenshtein.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.google.gson.Gson;
import com.tiagozenicola.levenshtein.model.Product;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public abstract class TestSuperClass {

    private static final String APPLICATION_JSON = "application/json";

    private static final String CONTENT_TYPE = "Content-Type";

    protected static final String PRODUCTS = "/products";

    public void before() throws Exception {
    }
    
	@Autowired
	protected MockMvc mockMvc;

    protected ResultActions callCreateService(Product product) throws Exception {
        final String productAsJson = new Gson().toJson(product, Product.class);
        return mockMvc.perform(post(PRODUCTS).header(CONTENT_TYPE, APPLICATION_JSON).content(productAsJson));
    }

}