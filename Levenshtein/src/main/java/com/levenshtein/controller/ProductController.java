package com.levenshtein.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
    private void create(@RequestBody Product product) {
        service.create(product);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    private List<Product> read() {
        return service.read();
    }

    @RequestMapping(value = "search", method = RequestMethod.GET)
    @ResponseBody
    private List<Product> search(@RequestParam String word, @RequestParam(defaultValue = "3") int limit) {
        return service.search(word, limit);
    }

}