package com.hackerrank.stocktrade.service.impl;

import com.hackerrank.stocktrade.dto.response.GenericResponse;
import com.hackerrank.stocktrade.dto.response.TradesNotFoundResponse;
import com.hackerrank.stocktrade.exceptions.RecordNotFoundException;
import com.hackerrank.stocktrade.repository.TradesRepository;
import com.hackerrank.stocktrade.service.StocksService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class StocksServiceImpl implements StocksService {

    private final TradesRepository tradesRepository;

    @Override
    public GenericResponse findTradesByStockSymbolRank(String stockSymbol, String start, String end) {

        if (!tradesRepository.existsBySymbol(stockSymbol)) {
            throw new RecordNotFoundException("No records found with the provided stock symbol");
        }

        Timestamp startTimestamp = Timestamp.valueOf(LocalDate.parse(start).atTime(LocalTime.of(0, 0)));
        Timestamp endTimestamp = Timestamp.valueOf(LocalDate.parse(end).atTime(LocalTime.of(23, 59)));

        val trades = tradesRepository.findAllBySymbolAndTimestampBetween(stockSymbol, startTimestamp, endTimestamp);

        if (isNull(trades.getSymbol())) {
            return new TradesNotFoundResponse("There are no trades in the given date range");
        }
        return trades;
    }
}
