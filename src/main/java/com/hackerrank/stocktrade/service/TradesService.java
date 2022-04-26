package com.hackerrank.stocktrade.service;

import com.hackerrank.stocktrade.dto.request.CreateTradeCommand;
import com.hackerrank.stocktrade.enums.TradeType;
import com.hackerrank.stocktrade.model.Trade;

import java.util.Collection;

public interface TradesService {
    void deleteAll();

    Trade createTrade(CreateTradeCommand command);

    Trade findTradeById(Long id);

    Collection<Trade> findAllTrades();

    Collection<Trade> findAllTradesByUserId(Long userId);

    Collection<Trade> findAllTradesByStockSymbolAndType(String stockSymbol, TradeType type, String start, String end);
}
