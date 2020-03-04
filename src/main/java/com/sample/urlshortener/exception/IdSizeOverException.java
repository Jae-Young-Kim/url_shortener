package com.sample.urlshortener.exception;

public class IdSizeOverException extends RuntimeException {
    public IdSizeOverException(String message) {
        super(message);
    }
}
