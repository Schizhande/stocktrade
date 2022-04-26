package com.hackerrank.stocktrade.dto.response;

public interface TradeBySymbolRankResponse extends GenericResponse {

    String getSymbol();

    Float getHighest();

    Float getLowest();

}
