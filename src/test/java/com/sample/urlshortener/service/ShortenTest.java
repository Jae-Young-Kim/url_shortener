package com.sample.urlshortener.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ShortenTest {
    private final String base62Characters = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final int expectedNum = 213041;
    private final String expectedString = "Tq9";

    private int base62Size;

    @BeforeEach
    void setup() {
        final int expectedSize = 62;
        assertEquals(expectedSize, base62Characters.length());
        this.base62Size = base62Characters.length();
    }

    @Test
    void encodeTest() {
        int num = this.expectedNum;

        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            sb.append(base62Characters.charAt(num % this.base62Size));
            num /= this.base62Size;
        }
        String s = sb.reverse().toString();

        assertEquals(this.expectedString, s);
    }

    @Test
    void decodeTest() {
        int num = 0;

        for (int i=0; i< expectedString.length(); i++) {
            num = num * this.base62Size + base62Characters.indexOf(expectedString.charAt(i));
        }

        assertEquals(expectedNum, num);
    }
}
