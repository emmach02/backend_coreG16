package com.backend3K6_2024.backendG16.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class ArgumentNotValid extends RuntimeException {
    public ArgumentNotValid(String message) {
        super(message);
    }
}
