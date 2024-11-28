package com.backend3K6_2024.backendG16.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;


@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    private static final String EXCEPTION_MESSAGE = "(Rest)ResponseEntityExceptionHandler (@ControllerAdvice)";

    // ------------------------------------------------------------------------
    // Overriding métodos de clase base ya gestionados (mediante
    // @ExceptionHandler en di
    // cha clase base)
    // ------------------------------------------------------------------------
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException e,
            HttpHeaders headers,
            HttpStatusCode statusCode,
            WebRequest request
    ) {

        List<String> errors = new ArrayList<String>();

        // for simple (ver abajo opción foreach + lambda)
        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }

        // for simple (ver abajo opción foreach + lambda)
        for (ObjectError error : e.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }

        ErrorEntity apiError = new ErrorEntity(
                EXCEPTION_MESSAGE,
                "MethodArgumentNotValidException (overriden)",
                statusCode,
                HttpStatus.BAD_REQUEST,
                ((ServletWebRequest)request).getRequest().getRequestURI(),
                e.getLocalizedMessage(),
                errors
        );

        //return handleExceptionInternal(e, apiError, headers, apiError.getStatus(), request);
        return new ResponseEntity<Object>(apiError, headers, apiError.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException e,
            HttpHeaders headers,
            HttpStatusCode statusCode,
            WebRequest request
    ) {

        String error = e.getParameterName() + " parameter is missing";

        ErrorEntity apiError = new ErrorEntity(
                EXCEPTION_MESSAGE,
                "MissingServletRequestParameterException (overriden)",
                statusCode,
                HttpStatus.BAD_REQUEST,
                ((ServletWebRequest)request).getRequest().getRequestURI(),
                e.getLocalizedMessage(),
                error
        );

        //return handleExceptionInternal(e, apiError, headers, apiError.getStatus(), request);
        return new ResponseEntity<Object>(apiError, headers, apiError.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException e,
            HttpHeaders headers,
            HttpStatusCode statusCode,
            WebRequest request
    ) {

        String error = "Request body inexistente o mal formado";

        ErrorEntity apiError = new ErrorEntity(
                EXCEPTION_MESSAGE,
                "HttpMessageNotReadableException (overriden)",
                statusCode,
                HttpStatus.BAD_REQUEST,
                ((ServletWebRequest)request).getRequest().getRequestURI(),
                e.getLocalizedMessage(),
                error
        );

        //return handleExceptionInternal(e, apiError, headers, apiError.getStatus(), request);
        return new ResponseEntity<Object>(apiError, headers, apiError.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(
            NoHandlerFoundException e,
            HttpHeaders headers,
            HttpStatusCode statusCode,
            WebRequest request
    ) {

        String error = "No handler found for " + e.getHttpMethod() + " " + e.getRequestURL();

        ErrorEntity apiError = new ErrorEntity(
                EXCEPTION_MESSAGE,
                "NoHandlerFoundException (overriden)",
                statusCode,
                HttpStatus.NOT_FOUND,
                ((ServletWebRequest)request).getRequest().getRequestURI(),
                e.getLocalizedMessage(),
                error
        );

        //return handleExceptionInternal(e, apiError, headers, apiError.getStatus(), request);
        return new ResponseEntity<Object>(apiError, headers, apiError.getStatus());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity handleResourceNotFound(
            ResourceNotFoundException ex, WebRequest request) {

        String error = ex.getMessage();

        ErrorEntity apiError = new ErrorEntity(
                EXCEPTION_MESSAGE,
                "(Custom)ResourceNotFoundException @(ExceptionHandler)",
                null,
                HttpStatus.NOT_FOUND,
                ((ServletWebRequest)request).getRequest().getRequestURI(),
                ex.getLocalizedMessage(),
                error
        );

        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity handleBadRequestException(
            BadRequestException ex, WebRequest request)
    {

        String error = ex.getMessage();

        ErrorEntity apiError = new ErrorEntity(
                EXCEPTION_MESSAGE,
                "(Custom)BadRequestException @(ExceptionHandler)",
                null,
                HttpStatus.BAD_REQUEST,
                ((ServletWebRequest)request).getRequest().getRequestURI(),
                ex.getLocalizedMessage(),
                error
        );

        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    }
}
