package com.sample.urlshortener.repository;

import com.sample.urlshortener.entity.UrlInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends CrudRepository<UrlInfo, Integer> {

    UrlInfo findBySourceUrl(String sourceUrl);
}
