package com.sample.urlshortener.service;

import com.sample.urlshortener.exception.IdSizeOverException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class Base62EncodeServiceTest {

    @Autowired
    private EncodeService encodeService;

    @Test
    void overSizeIDHandleTest() {
        final int overSizeId = Integer.MAX_VALUE;

        Assertions.assertThrows(IdSizeOverException.class, () -> encodeService.encode(overSizeId));
    }
}
