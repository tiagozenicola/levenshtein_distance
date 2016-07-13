package com.levenshtein.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.levenshtein.model.Product;
import com.levenshtein.service.ProductService;

@Controller
public class ProductController {

    @Autowired
    private ProductService service;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    private List<Product> read() {
        return service.read();
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    private void create(@RequestBody String name) {
        service.create(name);
    }

}