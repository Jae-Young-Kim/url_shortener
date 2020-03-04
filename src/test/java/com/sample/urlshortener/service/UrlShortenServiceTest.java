package com.sample.urlshortener.service;

import com.sample.urlshortener.entity.UrlInfo;
import com.sample.urlshortener.repository.UrlRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UrlShortenServiceTest {

    private static final String SOURCE_URL = "http://localhost/";
    private static final String SHORTEN_URL = "dT2";
    private static final int TEST_ID = 21341;

    @Mock
    private UrlRepository urlRepository;
    @Mock
    private EncodeService encodeService;
    @InjectMocks
    private UrlShortenService shortenService;

    @Test
    void storedShortenUrlTest() {
        when(this.urlRepository.findBySourceUrl(SOURCE_URL)).thenReturn(new UrlInfo(TEST_ID, SOURCE_URL, SHORTEN_URL));

        final String shortenUrl = this.shortenService.shorten(SOURCE_URL);

        verify(this.urlRepository, atLeastOnce()).findBySourceUrl(SOURCE_URL);
        verify(this.urlRepository, atLeastOnce()).updateCount(TEST_ID);
        assertEquals(SHORTEN_URL, shortenUrl);
    }

    @Test
    void freshShortenUrlTest() {
        when(this.urlRepository.findBySourceUrl(anyString())).thenReturn(null);
        when(this.encodeService.encode(anyInt())).thenReturn(SHORTEN_URL);

        final String shortenUrl = this.shortenService.shorten(SOURCE_URL);

        verify(this.urlRepository, atLeastOnce()).findBySourceUrl(anyString());
        verify(this.encodeService, times(1)).encode(anyInt());
        verify(this.urlRepository, times(2)).save(any(UrlInfo.class));

        assertEquals(SHORTEN_URL, shortenUrl);
    }

    @Test
    void normalizeTest() {
        when(this.urlRepository.findByConvertedUrl(SHORTEN_URL))
                .thenReturn(new UrlInfo(TEST_ID, SOURCE_URL, SHORTEN_URL));

        final String sourceUrl = this.shortenService.normalize(SHORTEN_URL);

        verify(this.urlRepository, atLeastOnce()).findByConvertedUrl(SHORTEN_URL);

        assertEquals(SOURCE_URL, sourceUrl);
    }
}
