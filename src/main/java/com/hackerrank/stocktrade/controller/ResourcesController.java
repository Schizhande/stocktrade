package com.hackerrank.stocktrade.controller;

import com.hackerrank.stocktrade.service.TradesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/erase")
public class ResourcesController {

    private final TradesService tradesService;

    public ResourcesController(TradesService tradesService) {
        this.tradesService = tradesService;
    }

    @DeleteMapping
    public ResponseEntity<Void> eraseAllTrades() {
        tradesService.deleteAll();
        return ResponseEntity.ok().build();
    }
}
