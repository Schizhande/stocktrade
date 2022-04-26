package com.hackerrank.stocktrade.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hackerrank.stocktrade.enums.TradeType;
import com.hackerrank.stocktrade.model.User;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class CreateTradeCommand {
    private Long id;
    private TradeType type;
    private User user;
    private String symbol;
    private Integer shares;
    private Float price;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "EST")
    private Timestamp timestamp;
}
