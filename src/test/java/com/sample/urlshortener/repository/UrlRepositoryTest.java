package com.sample.urlshortener.repository;

import com.sample.urlshortener.entity.UrlInfo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
class UrlRepositoryTest {

    @Autowired
    private UrlRepository urlRepository;

    @Test
    void basicSaveAndFindTest() {
        final String testUrl = "http://localhost:8080";
        UrlInfo urlInfo = new UrlInfo(testUrl);

        urlRepository.save(urlInfo);

        UrlInfo found = urlRepository.findBySourceUrl(testUrl);
        assertNotNull(found);
        assertEquals(testUrl, found.getSourceUrl());
    }
}
