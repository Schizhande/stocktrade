package com.hackerrank.stocktrade.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ResponseErrorHandler {

    @ExceptionHandler({RecordNotFoundException.class})
    public ResponseEntity<ApiResponse> handleRecordNotFound(RecordNotFoundException e) {
        ApiResponse apiResponse = ApiResponse.with(e.getMessage(), new RecordNotFoundException(e.getMessage()).toString(),
                HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({RecordAlreadyExistException.class})
    public ResponseEntity<ApiResponse> handleRecordAlreadyExistException(RecordAlreadyExistException e) {
        ApiResponse apiResponse = ApiResponse.with(e.getMessage(), new RecordAlreadyExistException(e.getMessage()).toString(),
                HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }
}
