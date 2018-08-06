package com.kileydelaney.repository;

import com.kileydelaney.model.Stock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends CrudRepository<Stock, Long> {

    @Query(value = "SELECT count(timestamp) FROM stocks", nativeQuery = true)
    long getTotalCount();

//    @Query(value = "SELECT price FROM stocks WHERE price = (SELECT MAX(price) FROM stocks_table WHERE date_only = :date AND symbol = :symbol)")


}
