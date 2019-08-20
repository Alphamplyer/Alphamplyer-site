package com.alphamplyer.microservice.news.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class UnableToDeleteException extends RuntimeException {
    public UnableToDeleteException(String message) {
        super(message);
    }
}
