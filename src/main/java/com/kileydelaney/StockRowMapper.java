package com.kileydelaney;

import com.kileydelaney.model.Stock;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StockRowMapper implements RowMapper<Stock> {

    @Override
    public Stock mapRow(ResultSet row, int index) throws SQLException {
        Stock stock = new Stock();
        stock.setId(row.getLong("id"));
        stock.setSymbol(row.getString("symbol"));
        stock.setPrice(row.getDouble("price"));
        stock.setVolume(row.getInt("volume"));
        stock.setDate(row.getTimestamp("date"));
        return stock;
    }

}
