package com.hackerrank.stocktrade.service.impl;

import com.hackerrank.stocktrade.dto.request.CreateTradeCommand;
import com.hackerrank.stocktrade.enums.TradeType;
import com.hackerrank.stocktrade.exceptions.RecordAlreadyExistException;
import com.hackerrank.stocktrade.exceptions.RecordNotFoundException;
import com.hackerrank.stocktrade.model.Trade;
import com.hackerrank.stocktrade.repository.TradesRepository;
import com.hackerrank.stocktrade.repository.UserRepository;
import com.hackerrank.stocktrade.service.TradesService;
import com.hackerrank.stocktrade.utils.TradeValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;

@Slf4j
@Service
@RequiredArgsConstructor
public class TradesServiceImpl implements TradesService {

    private final TradesRepository tradesRepository;

    private final ModelMapper modelMapper;

    private final UserRepository userRepository;

    @Override
    public void deleteAll() {
        tradesRepository.deleteAll();
    }

    @Override
    public Trade createTrade(CreateTradeCommand command) {

        TradeValidator.validate(command);

        val existsById = tradesRepository.existsById(command.getId());

        if (existsById) {
            throw new RecordAlreadyExistException("Trade record already exist");
        }
        if (!userRepository.existsById(command.getUser().getId())) {
            userRepository.save(command.getUser());
        }

        val trade = modelMapper.map(command, Trade.class);

        return tradesRepository.save(trade);
    }

    @Override
    public Trade findTradeById(Long id) {
        return tradesRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Trade does not exist"));
    }

    @Override
    public Collection<Trade> findAllTrades() {
        return tradesRepository.findAllByOrderByIdAsc();
    }

    @Override
    public Collection<Trade> findAllTradesByUserId(Long userId) {

        val user = userRepository.findById(userId)
                .orElseThrow(() -> new RecordNotFoundException("User does not exist"));

        val trades = tradesRepository.findAllByUserOrderByIdAsc(user);

        if (trades.isEmpty()) {
            throw new RecordNotFoundException("Trades not found for provided user id");
        }
        return trades;
    }

    @Override
    public Collection<Trade> findAllTradesByStockSymbolAndType(String stockSymbol, TradeType type, String start,
                                                               String end) {
        Timestamp startTimestamp = Timestamp.valueOf(LocalDate.parse(start).atTime(LocalTime.of(0, 0)));
        Timestamp endTimestamp = Timestamp.valueOf(LocalDate.parse(end).atTime(LocalTime.of(23, 59)));

        if (!tradesRepository.existsBySymbol(stockSymbol)) {
            throw new RecordNotFoundException("No records found with the provided stock symbol");
        }
        return tradesRepository.findAllBySymbolAndTypeAndTimestampBetween(stockSymbol, type.name(),
                startTimestamp, endTimestamp);
    }
}
