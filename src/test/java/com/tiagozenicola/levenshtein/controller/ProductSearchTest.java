package com.tiagozenicola.levenshtein.controller;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.ResultActions;

import com.tiagozenicola.levenshtein.model.Product;
import com.tiagozenicola.levenshtein.repository.ProductRepository;

public class ProductSearchTest extends TestSuperClass {

    private static final String SEARCH = "/search";

    private static boolean testInitialized = false;

    private String keyword = "chapolin";

    @Autowired
    private ProductRepository productRepository;

    @Before
    public void before() throws Exception {
        if (!testInitialized) {
            testInitialized = true;
            productRepository.deleteAll();

            final List<?> words = IOUtils.readLines(this.getClass().getResourceAsStream("/small-list-of-words"), "UTF-8");

            for (Object word : words) {
                callCreateService(new Product(word.toString())).andExpect(status().isCreated());
            }
        }
    }

    @Test
    public void testingSearchWithNoThreshold() throws Exception {
        callSearchService(keyword)
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(9)))
            .andExpect(jsonPath("$[*].name", contains("*chapolin", "chap*olin", "chapolin*", "*chapolin*", "ch*ap*olin", "cha*polin*",
                        "*cha*polin*", "ch*ap*ol*in", "cha*pol*in*")));
    }

    @Test
    public void testingSearchWithThreshold0AndWordFound() throws Exception {
        keyword = "*chapolin";

        final Integer threshold = 0;

        callSearchService(keyword, threshold)
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(1)))
            .andExpect(jsonPath("$[*].name", contains("*chapolin")));
    }

    @Test
    public void testingSearchWithThreshold0AndWordNotFound() throws Exception {
        final Integer threshold = 0;

        callSearchService(keyword, threshold)
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void testingSearchWithThreshold1() throws Exception {
        final Integer threshold = 1;

        callSearchService(keyword, threshold)
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(3)))
            .andExpect(jsonPath("$[*].name", contains("*chapolin", "chap*olin", "chapolin*")));
    }

    @Test
    public void testingSearchWithThreshold2() throws Exception {
        final Integer threshold = 2;

        callSearchService(keyword, threshold)
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(6)))
            .andExpect(jsonPath("$[*].name", contains("*chapolin", "chap*olin", "chapolin*", "*chapolin*", "ch*ap*olin", "cha*polin*")));
    }

    @Test
    public void testingSearchWithThreshold3() throws Exception {
        final Integer threshold = 3;

        callSearchService(keyword, threshold)
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(9)))
            .andExpect(jsonPath("$[*].name", contains("*chapolin", "chap*olin", "chapolin*", "*chapolin*", "ch*ap*olin", "cha*polin*",
                        "*cha*polin*", "ch*ap*ol*in", "cha*pol*in*")));
    }

    @Test
    public void testingSearchWithThreshold4() throws Exception {
        final Integer threshold = 4;

        callSearchService(keyword, threshold)
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(12)))
            .andExpect(jsonPath("$[*].name", contains("*chapolin", "chap*olin", "chapolin*", "*chapolin*", "ch*ap*olin", "cha*polin*",
                        "*cha*polin*", "ch*ap*ol*in", "cha*pol*in*", "*cha*pol*in*", "*ch*ap*ol*in", "c*ha*polin**")));
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