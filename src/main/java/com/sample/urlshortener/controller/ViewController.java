package com.sample.urlshortener.controller;

import com.sample.urlshortener.service.ShortenService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ViewController {

    @Value("${urlshortener.base-address}")
    private String baseAddress;

    private final ShortenService shortenService;

    public ViewController(ShortenService shortenService) {
        this.shortenService = shortenService;
    }

    @GetMapping("/")
    public String indexPage(Model model) {
        model.addAttribute("baseUrl", this.baseAddress);
        return "index";
    }

    @GetMapping("/fw/{shortenUrl}")
    public RedirectView redirectPage(@PathVariable("shortenUrl") String shortenUrl,
                                     HttpServletRequest request) {
        String redirectUrl = request.getScheme() + "://" + this.shortenService.normalize(shortenUrl);
        return new RedirectView(redirectUrl);
    }
}
