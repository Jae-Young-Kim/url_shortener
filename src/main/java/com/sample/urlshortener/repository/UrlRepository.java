package com.sample.urlshortener.repository;

import com.sample.urlshortener.entity.UrlInfo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UrlRepository extends CrudRepository<UrlInfo, Integer> {

    UrlInfo findById(int id);
    UrlInfo findBySourceUrl(String sourceUrl);
    UrlInfo findByConvertedUrl(String convertedUrl);

    @Transactional
    @Modifying
    @Query("UPDATE UrlInfo u set u.hit = u.hit + 1 where u.id = :id")
    void updateCount(@Param("id") int id);
}
