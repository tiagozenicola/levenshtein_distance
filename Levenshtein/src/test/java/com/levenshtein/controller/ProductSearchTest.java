package com.levenshtein.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.ResultActions;

import com.levenshtein.model.Product;
import com.levenshtein.repository.ProductRepository;

public class ProductSearchTest extends TestSuperClass {

    private static final String SEARCH = "/search";

    private static boolean testInitialized = false;

    private final String keyword = "chapolin";

    @Autowired
    private ProductRepository productRepository;

    @Before
    public void before() throws Exception {
        super.before();

        if (!testInitialized) {
            testInitialized = true;
            productRepository.deleteAll();

            callCreateService(new Product("banana")).andExpect(status().isOk());
            callCreateService(new Product("melão")).andExpect(status().isOk());
            callCreateService(new Product("goiaba")).andExpect(status().isOk());
            callCreateService(new Product("chuva")).andExpect(status().isOk());
            callCreateService(new Product("computador")).andExpect(status().isOk());
            callCreateService(new Product("iphone")).andExpect(status().isOk());
            callCreateService(new Product("vento")).andExpect(status().isOk());
            callCreateService(new Product("bolosalgado")).andExpect(status().isOk());
            callCreateService(new Product("xuxasóparabaixinhos")).andExpect(status().isOk());
            callCreateService(new Product("losangeles")).andExpect(status().isOk());
            callCreateService(new Product("paris")).andExpect(status().isOk());
            callCreateService(new Product("silviosantos")).andExpect(status().isOk());
        }
    }

    @Test
    public void testingSearchWithNoThreshold() throws Exception {
        final String expectedResult = "[]";

        callSearchService(keyword).andExpect(status().isOk()).andExpect(content().json(expectedResult));
    }

    @Test
    public void testingSearchWithThreshold0() throws Exception {
        final Integer threshold = 0;

        final String expectedResult = "[]";

        callSearchService(keyword, threshold).andExpect(status().isOk()).andExpect(content().json(expectedResult));
    }

    @Test
    public void testingSearchWithThreshold1() throws Exception {
        final Integer threshold = 1;

        final String expectedResult = "[]";

        callSearchService(keyword, threshold).andExpect(status().isOk()).andExpect(content().json(expectedResult));
    }

    @Test
    public void testingSearchWithThreshold2() throws Exception {
        final Integer threshold = 2;

        final String expectedResult = "[]";

        callSearchService(keyword, threshold).andExpect(status().isOk()).andExpect(content().json(expectedResult));
    }

    @Test
    public void testingSearchWithThreshold3() throws Exception {
        final Integer threshold = 3;

        final String expectedResult = "[]";

        callSearchService(keyword, threshold).andExpect(status().isOk()).andExpect(content().json(expectedResult));
    }

    @Test
    public void testingSearchWithThreshold4() throws Exception {
        final Integer threshold = 4;

        final String expectedResult = "[]";

        callSearchService(keyword, threshold).andExpect(status().isOk()).andExpect(content().json(expectedResult));
    }

    @Test
    public void testingSearchWithThreshold5() throws Exception {
        final Integer threshold = 5;

        final String expectedResult = "[]";

        callSearchService(keyword, threshold).andExpect(status().isOk()).andExpect(content().json(expectedResult));
    }

    // TODO retirar id dos asserts
    @Ignore
    @Test
    public void testingSearchWithThreshold6() throws Exception {
        final Integer threshold = 6;

        final String expectedResult = "[{\"id\":4,\"name\":\"chuva\"},{\"id\":11,\"name\":\"paris\"}]";

        callSearchService(keyword, threshold).andExpect(status().isOk()).andExpect(content().json(expectedResult));
    }

    // TODO retirar id dos asserts
    @Ignore
    @Test
    public void testingSearchWithThreshold7() throws Exception {
        final Integer threshold = 7;

        final String expectedResult = "[{\"id\":1,\"name\":\"banana\"},{\"id\":2,\"name\":\"melão\"},{\"id\":4,\"name\":\"chuva\"},{\"id\":6,\"name\":\"iphone\"},{\"id\":7,\"name\":\"vento\"},{\"id\":11,\"name\":\"paris\"}]";

        callSearchService(keyword, threshold).andExpect(status().isOk()).andExpect(content().json(expectedResult));
    }

    private ResultActions callSearchService(String keyword) throws Exception {
        return callSearchService(keyword, null);
    }

    private ResultActions callSearchService(String keyword, Integer threshold) throws Exception {
        if (threshold != null) {
            return mockMvc.perform(get(String.format("%s%s?word=%s&limit=%d", PRODUCTS, SEARCH, keyword, threshold)));
        }

        return mockMvc.perform(get(String.format("%s%s?word=%s", PRODUCTS, SEARCH, keyword)));
    }
}