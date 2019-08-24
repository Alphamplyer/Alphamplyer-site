package com.alphamplyer.microservice.userspermissions.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class UnableToUpdateException extends RuntimeException {
    public UnableToUpdateException(String message) {
        super(message);
    }
}
