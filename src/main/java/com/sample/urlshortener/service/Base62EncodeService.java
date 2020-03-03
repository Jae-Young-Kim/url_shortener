package com.sample.urlshortener.service;

import org.springframework.stereotype.Service;

@Service
public class Base62EncodeService implements EncodeService {
    private static final String BASE_62_CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int SIZE = 62;

    @Override
    public String encode(long n) {
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            sb.append(BASE_62_CHARACTERS.charAt((int) n % SIZE));
            n /= SIZE;
        }
        return sb.reverse().toString();
    }

    @Override
    public int decode(String s) {
        int num = 0;

        for (int i = 0; i < s.length(); i++) {
            num = num * SIZE + BASE_62_CHARACTERS.indexOf(s.charAt(i));
        }
        return num;
    }
}
