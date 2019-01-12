package com.levenshtein.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;
import com.levenshtein.Application;
import com.levenshtein.model.Product;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@WebIntegrationTest(randomPort = true)
public class TestSuperClass {

    private static final String APPLICATION_JSON = "application/json";

    private static final String CONTENT_TYPE = "Content-Type";

    protected static final String PRODUCTS = "/products";

    @Resource
    private WebApplicationContext webApplicationContext;

    protected MockMvc mockMvc;

    @Before
    public void before() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    protected ResultActions callCreateService(Product product) throws Exception {
        final String productAsJson = new Gson().toJson(product, Product.class);
        return mockMvc.perform(post(PRODUCTS).header(CONTENT_TYPE, APPLICATION_JSON).content(productAsJson));
    }

}