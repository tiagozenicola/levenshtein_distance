package com.tiagozenicola.levenshtein.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.tiagozenicola.levenshtein.model.Product;
import com.tiagozenicola.levenshtein.repository.ProductRepository;

public class ProductCreationTest extends TestSuperClass {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void before() throws Exception {
        super.before();

        productRepository.deleteAll();
    }

    @Test
    public void createProductWithDuplicatedName() throws Exception {
        final Product product = new Product("banana");

        callCreateService(product).andExpect(status().isCreated());
        callCreateService(product).andExpect(status().isConflict());

        assertsForSuccess("banana");
    }

    @Test
    public void createProductWithLargeName() throws Exception {
        final Product product = new Product("LargeNameLargeNameLargeNameLargeNameLargeNameLargeNameLargeNameLargeNameLargeName");

        callCreateService(product).andExpect(status().isUnprocessableEntity());

        assertsForFail();
    }

    @Test
    public void createProductWithNameEmpty() throws Exception {
        final Product product = new Product("");

        callCreateService(product).andExpect(status().isUnprocessableEntity());

        assertsForFail();
    }

    @Test
    public void createProductWithNameNull() throws Exception {
        final Product product = new Product();

        callCreateService(product).andExpect(status().isConflict());

        assertsForFail();
    }

    @Test
    public void createProductWithNameWithAccents() throws Exception {
        final Product product = new Product("áéíóú   ãẽĩõũ   àèìòù   äëïöü");

        callCreateService(product).andExpect(status().isCreated());

        assertsForSuccess("áéíóú ãẽĩõũ àèìòù äëïöü");
    }

    @Test
    public void createProductWithSpaces() throws Exception {
        final Product product = new Product("      ");

        callCreateService(product).andExpect(status().isUnprocessableEntity());

        assertsForFail();
    }

    @Test
    public void createProductWithValidName() throws Exception {
        final Product product = new Product("banana");

        callCreateService(product).andExpect(status().isCreated());

        assertsForSuccess("banana");
    }

    @Test
    public void createProductWithValidNameAndSpaces() throws Exception {
        final Product product = new Product("    ban    ana    bla    ");

        callCreateService(product).andExpect(status().isCreated());

        assertsForSuccess("ban ana bla");
    }

    private void assertsForFail() {
        Assert.assertEquals(0, productRepository.count());
    }

    private void assertsForSuccess(String productName) {
        Assert.assertEquals(1, productRepository.count());
        Assert.assertEquals(productName, productRepository.findAll().get(0).getName());
    }

}