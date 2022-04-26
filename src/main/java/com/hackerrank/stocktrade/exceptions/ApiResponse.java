package com.hackerrank.stocktrade.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiResponse {

    private String message;

    private String error;

    private Integer status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "EST")

    private LocalDateTime timestamp;

    private ApiResponse(String message, String error, Integer status) {
        this.message = message;
        this.error = error;
        this.status = status;
        this.timestamp = LocalDateTime.now();
    }

    public static ApiResponse with(String message, String error, Integer status) {
        return new ApiResponse(message, error, status);
    }
}
