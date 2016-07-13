package com.levenshtein.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.levenshtein.repository.ProductRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@WebIntegrationTest(randomPort = true)
public class ProductControllerTest {

    private static final String APPLICATION_JSON = "application/json";

    private static final String CONTENT_TYPE = "Content-Type";

    private static final String PRODUCTS = "/products";

    private MockMvc mockMvc;

    private String productName = "banana";

    private Product product = new Product(productName);

    @Autowired
    private ProductRepository productRepository;

    @Resource
    private WebApplicationContext webApplicationContext;

    @Before
    public void before() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

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

    private void assertsForSuccess(String productName) {
        Assert.assertEquals(1, productRepository.count());
        Assert.assertEquals(productName, productRepository.findAll().get(0).getName());
    }

    private void assertsForFail() {
        Assert.assertEquals(0, productRepository.count());
    }

    private ResultActions callService(Product product) throws Exception {
        final String productAsJson = new Gson().toJson(product, Product.class);
        return mockMvc.perform(post(PRODUCTS).header(CONTENT_TYPE, APPLICATION_JSON).content(productAsJson));
    }

}
