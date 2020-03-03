package com.sample.urlshortener.controller;

import com.sample.urlshortener.service.ShortenService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShortenController {

    private final ShortenService shortenService;

    public ShortenController(ShortenService shortenService) {
        this.shortenService = shortenService;
    }

    @PostMapping("/shorten")
    public String shorten(@RequestBody String url) {
        return this.shortenService.shorten(url);
    }
}
