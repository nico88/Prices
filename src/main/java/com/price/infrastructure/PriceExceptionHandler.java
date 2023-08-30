package com.price.infrastructure;

import com.price.domain.dto.Response;
import com.price.domain.exception.PriceException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class PriceExceptionHandler {

    @ExceptionHandler(PriceException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response handlePriceException(PriceException ex) {
        return Response.builder()
                .message(ex.getMessage())
                .time(String.valueOf(Instant.now()))
                .build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response handleGenericException(Exception ex) {
        return Response.builder()
                .message("Internal Server Error")
                .time(String.valueOf(Instant.now()))
                .build();
    }
}