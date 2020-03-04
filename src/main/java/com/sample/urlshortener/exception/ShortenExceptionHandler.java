package com.sample.urlshortener.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ShortenExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(ShortenExceptionHandler.class);

    @ExceptionHandler(IdSizeOverException.class)
    public ResponseEntity<String> idSizeOverHandler(IdSizeOverException e) {
        // todo more exception
        logger.error(e.getMessage());
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
