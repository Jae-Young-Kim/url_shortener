package com.sample.urlshortener.service;

import com.sample.urlshortener.entity.UrlInfo;
import com.sample.urlshortener.repository.UrlRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UrlShortenService implements ShortenService {

    private final UrlRepository urlRepository;
    private final EncodeService encodeService;

    public UrlShortenService(UrlRepository urlRepository, EncodeService encodeService) {
        this.urlRepository = urlRepository;
        this.encodeService = encodeService;
    }

    @Transactional
    @Override
    public String shorten(String sourceUrl) {

        UrlInfo legacyUrlInfo = this.urlRepository.findBySourceUrl(sourceUrl);
        if (legacyUrlInfo != null && legacyUrlInfo.getConvertedUrl() != null) {
            this.urlRepository.updateCount(legacyUrlInfo.getId());
            return legacyUrlInfo.getConvertedUrl();
        }

        UrlInfo newUrlInfo = new UrlInfo(sourceUrl);
        this.urlRepository.save(newUrlInfo);

        int key = newUrlInfo.getId();
        String convertedKey = this.encodeService.encode(key);

        newUrlInfo.setConvertedUrl(convertedKey);
        this.urlRepository.save(newUrlInfo);

        return convertedKey;
    }

    @Override
    public String normalize(String shortenUrl) {
        UrlInfo urlInfo = this.urlRepository.findByConvertedUrl(shortenUrl);
        return urlInfo.getSourceUrl();
    }
}
