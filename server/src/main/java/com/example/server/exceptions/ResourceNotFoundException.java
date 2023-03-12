package com.example.server.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String resourceType, String resourceField, Object resourceValue) {
        super(String.format("%s not found with %s : '%s'", resourceType, resourceField, resourceValue));
    }
}