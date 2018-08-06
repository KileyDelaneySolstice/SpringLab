package com.kileydelaney.controller;

import com.kileydelaney.model.Stock;
import com.kileydelaney.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/stocks")
public class StockController {

    private StockRepository stockRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    public StockController(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    // load stocks
    @GetMapping("/load")
    public Iterable<Stock> saveStocks() throws Exception {
        List<Stock> stocksList = Stock.jsonToList();
        return stockRepository.saveAll(stocksList);
    }

    // list stocks
    @GetMapping("/list")
    public Iterable<Stock> list() {
        return stockRepository.findAll();
    }

    // test
    @GetMapping("/datecount")
    public long count() { return stockRepository.getTotalCount(); }

    // retrieve highest price of a given stock on given date
    // SQL query = "SELECT price FROM stocks WHERE price = (SELECT MAX(price) FROM stocks_table WHERE date_only = '"+date+"' AND symbol = '"+symbol+"')"
    @GetMapping("/high/{symbol}/{date}")
    public String retrieveHighestPrice(@PathVariable String symbol, @PathVariable String date) {
        return "Success for: " + symbol + " " + date;
    }

    // retrieve lowest price of a given stock on given date
    // SQL query = "SELECT price FROM stocks WHERE price = (SELECT MIN(price) FROM stocks_table WHERE date_only = '"+date+"' AND symbol = '"+symbol+"')"
    @GetMapping("/low/{symbol}/{date}")
    public String retrieveLowestPrice(@PathVariable String symbol, @PathVariable String date) {
        return "Success for: " + symbol + " " + date;
    }

    // retrieve total volume of a given stock traded on given date
    // SQL query = "SELECT COUNT(volume) FROM stocks WHERE date_only = '"+date+"' AND symbol = '"+symbol+"'"
    @GetMapping("total/{symbol}/{date}")
    public String retrieveTotalVolume(@PathVariable String symbol, @PathVariable String date) {
        return "Success for: " + symbol + " " + date;
    }

    // get a single stock (by id)
    @GetMapping("/{id}")
    public Optional<Stock> getStockById(@PathVariable(value = "id") Long stockId) {
        if (stockRepository.existsById(stockId)) {
            return stockRepository.findById(stockId);
        } else {
            return null;
        }
    }


}
