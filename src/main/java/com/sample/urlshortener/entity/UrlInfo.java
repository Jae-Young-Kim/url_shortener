package com.sample.urlshortener.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class UrlInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String sourceUrl;
    private String convertedUrl;

    public UrlInfo() {
    }

    public UrlInfo(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public int getId() {
        return id;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public String getConvertedUrl() {
        return convertedUrl;
    }
}
