package com.kileydelaney.repository;

import com.kileydelaney.model.Stock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.Date;

@Repository
public interface StockRepository extends CrudRepository<Stock, Long> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE stocks SET date_only = cast(timestamp as date)", nativeQuery = true)
    void updateDateOnlyField();

    @Query(value = "SELECT MAX(price) FROM stocks WHERE date_only = :date_only AND symbol = :symbol", nativeQuery = true)
    double getMaxPriceByDateAndSymbol(@Param("date_only") String date_only, @Param("symbol") String symbol);

    @Query(value = "SELECT MIN(price) FROM stocks WHERE date_only = :date_only AND symbol = :symbol", nativeQuery = true)
    double getMinPriceByDateAndSymbol(@Param("date_only") String date_only, @Param("symbol") String symbol);

    // TODO: Fix SQL syntax so that it actually retrieves total volume of stock traded on date (27417 = 481 * 57)
    @Query(value = "SELECT COUNT(volume) FROM stocks WHERE date_only = :date_only AND symbol = :symbol", nativeQuery = true)
    int getTotalVolumeByDateAndSymbol(@Param("date_only") String date_only, @Param("symbol") String symbol);




}
