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

    // retrieve highest price of a given stock on given date
    @GetMapping("/high/{symbol}/{date}")
    public Stock retrieveHighestPrice(@PathVariable String date, @PathVariable String symbol) {
        return stockRepository.getMaxPriceByDateAndSymbol(date, symbol);
    }

    // retrieve lowest price of a given stock on given date
    @GetMapping("/low/{symbol}/{date}")
    public Stock retrieveLowestPrice(@PathVariable String date, @PathVariable String symbol) {
        return stockRepository.getMinPriceByDateAndSymbol(date, symbol);
    }

    // retrieve total volume of a given stock traded on given date
    @GetMapping("total/{symbol}/{date}")
    public Stock retrieveTotalVolume(@PathVariable String date, @PathVariable String symbol) {
        return stockRepository.getTotalVolumeByDateAndSymbol(date, symbol);
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




    @GetMapping("/high/{symbol}")
    public Stock retrieveHighestPrice(@PathVariable String symbol) {
        return stockRepository.getMaxPriceBySymbol(symbol);
    }


}
