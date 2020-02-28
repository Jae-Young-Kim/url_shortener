package com.sample.urlshortener.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShortenController {

    @PostMapping("/shorten")
    public String shorten(@RequestBody String sourceURL) {
        return "OK : " + sourceURL;
    }
}
