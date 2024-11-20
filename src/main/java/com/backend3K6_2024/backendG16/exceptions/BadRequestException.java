package com.backend3K6_2024.backendG16.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends Exception {
    public BadRequestException() {super();}
    public BadRequestException(String message){
        super(message);
    }
}
