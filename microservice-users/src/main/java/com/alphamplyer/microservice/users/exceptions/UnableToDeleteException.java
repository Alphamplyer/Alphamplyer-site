package com.alphamplyer.microservice.users.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * to throw when we failed to delete an element
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class UnableToDeleteException extends RuntimeException {
    public UnableToDeleteException(String message) {
        super(message);
    }
}
