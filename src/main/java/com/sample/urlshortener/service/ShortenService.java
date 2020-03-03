package com.sample.urlshortener.service;

public interface ShortenService {
    String shorten(String sourceUrl);
    String normalize(String shortenUrl);
}
