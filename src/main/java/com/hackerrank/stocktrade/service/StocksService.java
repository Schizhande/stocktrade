package com.hackerrank.stocktrade.service;

import com.hackerrank.stocktrade.dto.response.GenericResponse;

public interface StocksService {
    GenericResponse findTradesByStockSymbolRank(String stockSymbol, String start, String end);
}
