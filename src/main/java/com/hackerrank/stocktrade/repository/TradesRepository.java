package com.hackerrank.stocktrade.repository;

import com.hackerrank.stocktrade.dto.response.TradeBySymbolRankResponse;
import com.hackerrank.stocktrade.model.Trade;
import com.hackerrank.stocktrade.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

@Repository
public interface TradesRepository extends JpaRepository<Trade, Long> {
    List<Trade> findAllByOrderByIdAsc();

    Collection<Trade> findAllByUserOrderByIdAsc(User user);

    Collection<Trade> findAllBySymbolAndTypeAndTimestampBetween(String stockSymbol, String type,
                                                                Timestamp start, Timestamp end);

    boolean existsBySymbol(String stockSymbol);

    @Query(value = "select symbol, min(price) as lowest, max(price) as highest from trade where symbol =:symbol and timestamp between" +
            " :start and :end", nativeQuery = true)
    TradeBySymbolRankResponse findAllBySymbolAndTimestampBetween(@Param("symbol") String symbol,
                                                                 @Param("start") Timestamp start,
                                                                 @Param("end") Timestamp end);
}
