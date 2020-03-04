package com.sample.urlshortener.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Base62EncodeTest {
    private static final String BASE_62_CHARACTERS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final int EXPECTED_NUM = 213041;
    private static final String EXPECTED_STRING = "Tq9";
    private static final int ID_LIMIT_NUM = Integer.MAX_VALUE;

    private int base62Size;

    @BeforeEach
    void setup() {
        final int expectedSize = 62;
        assertEquals(expectedSize, BASE_62_CHARACTERS.length());
        this.base62Size = BASE_62_CHARACTERS.length();
    }

    @Test
    void encodeTest() {
        int num = EXPECTED_NUM;

        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            sb.append(BASE_62_CHARACTERS.charAt(num % this.base62Size));
            num /= this.base62Size;
        }
        String s = sb.reverse().toString();

        assertEquals(EXPECTED_STRING, s);
    }

    @Test
    void decodeTest() {
        int num = 0;

        for (int i = 0; i < EXPECTED_STRING.length(); i++) {
            num = num * this.base62Size + BASE_62_CHARACTERS.indexOf(EXPECTED_STRING.charAt(i));
        }

        assertEquals(EXPECTED_NUM, num);
    }

    @Test
    void maxIdSize() {
        final int idSizeLimit = 8;
        int num = ID_LIMIT_NUM;

        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            int n = num % this.base62Size;
            sb.append(BASE_62_CHARACTERS.charAt(n));
            num /= this.base62Size;
        }

        assertTrue(idSizeLimit > sb.length());
    }

}
