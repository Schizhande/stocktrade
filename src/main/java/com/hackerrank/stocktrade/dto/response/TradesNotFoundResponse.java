package com.hackerrank.stocktrade.dto.response;

import lombok.Data;

@Data
public class TradesNotFoundResponse implements GenericResponse {

    private String message;

    public TradesNotFoundResponse(String message) {
        this.message = message;
    }
}
