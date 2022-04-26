package com.hackerrank.stocktrade.controller;

import com.hackerrank.stocktrade.dto.request.CreateTradeCommand;
import com.hackerrank.stocktrade.enums.TradeType;
import com.hackerrank.stocktrade.model.Trade;
import com.hackerrank.stocktrade.service.TradesService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(value = "/trades")
public class TradesController {

    private final TradesService tradesService;

    public TradesController(TradesService tradesService) {
        this.tradesService = tradesService;
    }

    @PostMapping
    public ResponseEntity<Trade> createTrade(@RequestBody CreateTradeCommand command) {
        return new ResponseEntity<>(tradesService.createTrade(command), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Trade> getTradeById(@PathVariable Long id) {
        return ResponseEntity.ok(tradesService.findTradeById(id));
    }

    @GetMapping
    public ResponseEntity<Collection<Trade>> getAllTrades() {
        return ResponseEntity.ok(tradesService.findAllTrades());
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<Collection<Trade>> getAllTradesByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(tradesService.findAllTradesByUserId(userId));
    }

    @GetMapping("/stocks/{stockSymbol}")
    public ResponseEntity<Collection<Trade>> getAllTradesByStockSymbolAndType(@PathVariable String stockSymbol,
                                                                              @RequestParam TradeType type,
                                                                              @DateTimeFormat(pattern = "yyyy-MM-dd") String start,
                                                                              @DateTimeFormat(pattern = "yyyy-MM-dd") String end) {
        return ResponseEntity.ok(tradesService.findAllTradesByStockSymbolAndType(stockSymbol, type, start, end));
    }
}
