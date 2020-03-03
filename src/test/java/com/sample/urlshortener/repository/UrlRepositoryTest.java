package com.sample.urlshortener.repository;

import com.sample.urlshortener.entity.UrlInfo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
class UrlRepositoryTest {

    private static final String TEST_URL = "http://localhost:8080";

    @Autowired
    private UrlRepository urlRepository;

    @Test
    void basicSaveAndFindTest() {
        UrlInfo urlInfo = new UrlInfo(TEST_URL);

        urlRepository.save(urlInfo);

        UrlInfo found = urlRepository.findBySourceUrl(TEST_URL);
        assertNotNull(found);
        assertEquals(TEST_URL, found.getSourceUrl());
    }

    @Test
    void autoGenerateKeyTest() {
        UrlInfo urlInfo1 = new UrlInfo(TEST_URL);
        UrlInfo urlInfo2 = new UrlInfo(TEST_URL);
        UrlInfo urlInfo3 = new UrlInfo(TEST_URL);

        Stream.of(urlInfo1, urlInfo2, urlInfo3).forEach(this.urlRepository::save);

        assertTrue(urlInfo1.getId() > 0);
        assertTrue(urlInfo2.getId() > urlInfo1.getId());
        assertTrue(urlInfo3.getId() > urlInfo2.getId());
    }

    @Test
    void updateHitCountTest() {
        UrlInfo urlInfo = new UrlInfo(TEST_URL);
        this.urlRepository.save(urlInfo);

        long key = urlInfo.getId();
        this.urlRepository.updateCount(key);

        UrlInfo found = this.urlRepository.findById(key);

        final int expectedHitCount = 2;
        assertEquals(expectedHitCount, found.getHit());
    }
}
