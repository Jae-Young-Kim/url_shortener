package com.sample.urlshortener.service;

public interface EncodeService {
    String encode(long n);
    int decode(String s);
}
