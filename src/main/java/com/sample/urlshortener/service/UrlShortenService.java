package com.sample.urlshortener.service;

import com.sample.urlshortener.repository.UrlRepository;
import org.springframework.stereotype.Service;

@Service
public class UrlShortenService implements ShortenService {

    private final UrlRepository urlRepository;

    public UrlShortenService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    @Override
    public String shorten(String source) {

        // todo 1 해당하는 레코드가 있는지 확인
        // todo 1-1 있으면 반환, 없으면 2
        // todo 2 레디스에 저장하고 키를 확인
        // todo 2-1 키를 base62 encode 하여 반환
        // todo 2-2 encoded 된 문자열을 키에 저장

        return null;
    }


}
