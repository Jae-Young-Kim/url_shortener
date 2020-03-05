package com.sample.urlshortener.controller;

import com.sample.urlshortener.service.ShortenService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShortenController {

    @Value("${urlshortener.base-address}")
    private String baseAddress = "";

    private final ShortenService shortenService;

    public ShortenController(ShortenService shortenService) {
        this.shortenService = shortenService;
    }

    @PostMapping("/shorten")
    public ResponseEntity<String> shorten(@RequestBody String url) {
        final String protocolPattern = "https?://";
        final String result = this.baseAddress + this.shortenService.shorten(url.replaceAll(protocolPattern, ""));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
