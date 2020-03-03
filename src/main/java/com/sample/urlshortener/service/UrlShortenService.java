package com.sample.urlshortener.service;

import com.sample.urlshortener.entity.UrlInfo;
import com.sample.urlshortener.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UrlShortenService implements ShortenService {

    @Value("${urlshortener.base-address}")
    private String baseAddress;

    private final UrlRepository urlRepository;
    private final EncodeService encodeService;

    public UrlShortenService(UrlRepository urlRepository, EncodeService encodeService) {
        this.urlRepository = urlRepository;
        this.encodeService = encodeService;
    }

    @Override
    public String shorten(String sourceUrl) {

        UrlInfo urlInfo = this.urlRepository.findBySourceUrl(sourceUrl);
        if (urlInfo != null && urlInfo.getConvertedUrl() != null) {
            return urlInfo.getConvertedUrl();
        }

        UrlInfo newUrlInfo = new UrlInfo(sourceUrl);
        this.urlRepository.save(newUrlInfo);

        long key = newUrlInfo.getId();
        String convertedKey = this.encodeService.encode(key);

        newUrlInfo.setConvertedUrl(convertedKey);
        this.urlRepository.save(newUrlInfo);

        return this.baseAddress + convertedKey;
    }

}
