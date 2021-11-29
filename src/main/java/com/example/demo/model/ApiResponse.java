package com.example.demo.model;

import lombok.Builder;
import lombok.Value;
/**
 * ApiResponse
 * This is uesd for show message for ResultsException
 * @author YUQI
 */
@Value
@Builder
public class ApiResponse {
    String error;
    String message;
}
