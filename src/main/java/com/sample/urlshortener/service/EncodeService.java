package com.sample.urlshortener.service;

public interface EncodeService {
    String encode(int n);
    int decode(String s);
}
