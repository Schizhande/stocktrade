package com.hackerrank.stocktrade.utils;

import com.hackerrank.stocktrade.dto.request.CreateTradeCommand;
import com.hackerrank.stocktrade.exceptions.InvalidArgumentException;

public final class TradeValidator {

    public static void validate(CreateTradeCommand command) {

        if (command.getPrice() < 130.42 || command.getPrice() > 195.65) {
            throw new InvalidArgumentException("Price should be in the range 130.42 to 195.65");
        }
        if (command.getShares() < 10 || command.getShares() > 30) {
            throw new InvalidArgumentException("Shares should be in the range 10 to 30");
        }
    }

}
