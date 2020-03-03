package com.sample.urlshortener.controller;

import com.sample.urlshortener.service.ShortenService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShortenController {

    private final ShortenService shortenService;

    @Value("${urlshortener.base-address}")
    private String baseAddress;

    public ShortenController(ShortenService shortenService) {
        this.shortenService = shortenService;
    }

    @PostMapping("/shorten")
    public String shorten(@RequestBody String url) {
        boolean isShortenUrl = url.contains(baseAddress);
        if (isShortenUrl) {
            return this.shortenService.normalize(url);
        }

        return this.baseAddress + this.shortenService.shorten(url);
    }
}
