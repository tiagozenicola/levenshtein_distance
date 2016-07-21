package com.levenshtein.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponentsBuilder;

import com.levenshtein.model.Product;
import com.levenshtein.service.ProductService;

@RequestMapping(value = ProductController.PRODUCTS)
@Controller
public class ProductController {

    protected static final String PRODUCTS = "/products";

    @Autowired
    private ProductService service;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    private ResponseEntity<?> create(@RequestBody Product product, UriComponentsBuilder builder) {
        final Product newProduct = service.create(product);

        final HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path(PRODUCTS + "/{id}").buildAndExpand(newProduct.getId()).toUri());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    private List<Product> read() {
        return service.read();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    private Product read(@PathVariable Long id) {
        return service.read(id);
    }
    
    @RequestMapping(value = "search", method = RequestMethod.GET)
    @ResponseBody
    private List<Product> search(@RequestParam String word, @RequestParam(defaultValue = "3") int limit) {
        return service.search(word, limit);
    }

}