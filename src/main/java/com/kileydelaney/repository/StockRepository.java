package com.kileydelaney.repository;

import com.kileydelaney.model.Stock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends CrudRepository<Stock, Long> {

    @Query(value = "SELECT price FROM stocks WHERE price = (SELECT MAX(price) FROM stocks_table WHERE date_only = :date_only AND symbol = :symbol)", nativeQuery = true)
    Stock getMaxPriceByDateAndSymbol(@Param("date_only") String date_only, @Param("symbol") String symbol);

    @Query(value = "SELECT price FROM stocks WHERE price = (SELECT MIN(price) FROM stocks_table WHERE date_only = :date_only AND symbol = :symbol)", nativeQuery = true)
    Stock getMinPriceByDateAndSymbol(@Param("date_only") String date_only, @Param("symbol") String symbol);

    @Query(value = "SELECT COUNT(volume) FROM stocks WHERE date_only = :date_only AND symbol = :symbol", nativeQuery = true)
    Stock getTotalVolumeByDateAndSymbol(@Param("date_only") String date_only, @Param("symbol") String symbol);

    @Query(value = "SELECT price FROM stocks WHERE price = (SELECT MAX(price) FROM stocks WHERE symbol = :symbol)", nativeQuery = true)
    Stock getMaxPriceBySymbol(@Param("symbol") String symbol);


}
