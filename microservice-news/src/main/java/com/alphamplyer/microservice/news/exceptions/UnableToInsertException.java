package com.alphamplyer.microservice.news.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * to throw when we failed to insert an element
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class UnableToInsertException extends RuntimeException {
    public UnableToInsertException(String message) {
        super(message);
    }
}
