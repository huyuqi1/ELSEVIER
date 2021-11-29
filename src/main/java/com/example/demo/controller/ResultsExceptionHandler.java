package com.example.demo.controller;

import com.example.demo.model.ApiResponse;
import com.example.demo.service.ResultNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * ResultsExceptionHandler
 * The exception which be throwed when couldn't get the result.
 * @author YUQI
 */
public class ResultsExceptionHandler {

    @ExceptionHandler(ResultNotFoundException.class)
    public ResponseEntity<ApiResponse> handleNotFoundApiException(
            ResultNotFoundException ex) {
        ApiResponse response =
                ApiResponse.builder()
                        .error("not-found-001")
                        .message(String.format("result is empty"))
                        .build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
