package com.hackerrank.stocktrade.exceptions;

public class RecordAlreadyExistException extends RuntimeException {
    public RecordAlreadyExistException(String message) {
        super(message);
    }
}
