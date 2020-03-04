package com.sample.urlshortener.controller;

import com.sample.urlshortener.service.ShortenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> shorten(@RequestBody String url) {
        return new ResponseEntity<>(this.shortenService.shorten(url), HttpStatus.OK);
    }
}
