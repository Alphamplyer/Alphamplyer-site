package com.alphamplyer.microservice.users.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * to throw when we failed to update an element
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class UnableToUpdateException extends RuntimeException {
    public UnableToUpdateException(String message) {
        super(message);
    }
}
