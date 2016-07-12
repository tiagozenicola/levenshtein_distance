package com.levenshtein.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ProductController {

    @RequestMapping("/")
    @ResponseBody
    private String home() {
        return "Hello World!!!!!!!!!!!";
    }

}