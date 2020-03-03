package com.sample.urlshortener.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table
@SequenceGenerator(
        name = "URL_INFO_ID_GENERATOR",
        sequenceName = "URL_INFO_SEQ",
        initialValue = 21341,
        allocationSize = 1
)
public class UrlInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "URL_INFO_ID_GENERATOR")
    private long id;
    private String sourceUrl;
    private String convertedUrl;
    private long hit = 1;

    public UrlInfo() {
    }

    public UrlInfo(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public long getId() {
        return id;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public String getConvertedUrl() {
        return convertedUrl;
    }

    public void setConvertedUrl(String convertedUrl) {
        this.convertedUrl = convertedUrl;
    }

    public long getHit() {
        return hit;
    }

    public void setHit(long hit) {
        this.hit = hit;
    }
}
