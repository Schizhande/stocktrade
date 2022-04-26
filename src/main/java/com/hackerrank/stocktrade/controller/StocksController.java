package com.hackerrank.stocktrade.controller;

import com.hackerrank.stocktrade.dto.response.GenericResponse;
import com.hackerrank.stocktrade.service.StocksService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/stocks")
public class StocksController {

    private final StocksService stocksService;

    public StocksController(StocksService stocksService) {
        this.stocksService = stocksService;
    }

    @GetMapping("/{stockSymbol}/price")
    public ResponseEntity<GenericResponse> getTradesByStockSymbolRank(@PathVariable String stockSymbol,
                                                                      @DateTimeFormat(pattern = "yyyy-MM-dd") String start,
                                                                      @DateTimeFormat(pattern = "yyyy-MM-dd") String end) {
        return ResponseEntity.ok(stocksService.findTradesByStockSymbolRank(stockSymbol, start, end));
    }
}
