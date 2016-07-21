package com.levenshtein.controller;

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

import com.levenshtein.model.Product;
import com.levenshtein.repository.ProductRepository;

public class ProductSearchTest extends TestSuperClass {

    private static final String SEARCH = "/search";

    private static boolean testInitialized = false;

    private String keyword = "chapolin";

    @Autowired
    private ProductRepository productRepository;

    @Before
    public void before() throws Exception {
        super.before();

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
    	callSearchService(keyword).andExpect(status().isOk())
    	.andExpect((jsonPath("$", hasSize(6))))
    	.andExpect((jsonPath("$[*].name", contains("chapolin1","chapolin2","chapolin11","chapolin22","chapolin111","chapolin222"))));
    }

    @Test
    public void testingSearchWithThreshold0AndWordFound() throws Exception {
    	keyword = "chapolin1";
    	
        final Integer threshold = 0;

    	callSearchService(keyword, threshold).andExpect(status().isOk())
    	.andExpect((jsonPath("$", hasSize(1))))
    	.andExpect((jsonPath("$[*].name", contains("chapolin1"))));
    }

    @Test
    public void testingSearchWithThreshold0AndWordNotFound() throws Exception {
    	final Integer threshold = 0;
    	
    	callSearchService(keyword, threshold).andExpect(status().isOk())
    	.andExpect((jsonPath("$", hasSize(0))));
    }
    
    @Test
    public void testingSearchWithThreshold1() throws Exception {
        final Integer threshold = 1;

    	callSearchService(keyword, threshold).andExpect(status().isOk())
    	.andExpect((jsonPath("$", hasSize(2))))
    	.andExpect((jsonPath("$[*].name", contains("chapolin1","chapolin2"))));
    }

    @Test
    public void testingSearchWithThreshold2() throws Exception {
        final Integer threshold = 2;

    	callSearchService(keyword, threshold).andExpect(status().isOk())
    	.andExpect((jsonPath("$", hasSize(4))))
    	.andExpect((jsonPath("$[*].name", contains("chapolin1","chapolin2","chapolin11","chapolin22"))));
    }

    @Test
    public void testingSearchWithThreshold3() throws Exception {
        final Integer threshold = 3;

    	callSearchService(keyword, threshold).andExpect(status().isOk())
    	.andExpect((jsonPath("$", hasSize(6))))
    	.andExpect((jsonPath("$[*].name", contains("chapolin1","chapolin2","chapolin11","chapolin22","chapolin111","chapolin222"))));
    }

    @Test
    public void testingSearchWithThreshold4() throws Exception {
        final Integer threshold = 4;

    	callSearchService(keyword, threshold).andExpect(status().isOk())
    	.andExpect((jsonPath("$", hasSize(8))))
    	.andExpect((jsonPath("$[*].name", contains("chapolin1","chapolin2","chapolin11","chapolin22","chapolin111","chapolin222","chapolin1111","chapolin2222"))));
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