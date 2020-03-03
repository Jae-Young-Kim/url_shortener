package com.sample.urlshortener.controller;

import com.sample.urlshortener.service.ShortenService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class ViewController {

    private final ShortenService shortenService;

    public ViewController(ShortenService shortenService) {
        this.shortenService = shortenService;
    }

    @GetMapping("/")
    public String indexPage() {
        return "index";
    }

    @GetMapping("/{shortenUrl}")
    public RedirectView redirectPage(@PathVariable("shortenUrl") String shortenUrl) {
        return new RedirectView(this.shortenService.normalize(shortenUrl));
    }
}
