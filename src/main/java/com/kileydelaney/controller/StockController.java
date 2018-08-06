package com.kileydelaney.controller;

import com.kileydelaney.model.QueryResultObject;
import com.kileydelaney.model.Stock;
import com.kileydelaney.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.*;

import javax.management.Query;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/stocks")
public class StockController {

    private StockRepository stockRepository;
    private QueryResultObject qro;


    public StockController(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    // load stocks
    @GetMapping("/load")
    public String saveStocks() throws Exception {
        List<Stock> stocksList = Stock.jsonToList();
        stockRepository.saveAll(stocksList);
        stockRepository.updateDateOnlyField();
        return "Stocks loaded successfully!";
    }

    // list stocks
    @GetMapping("/list")
    public Iterable<Stock> list() {
        return stockRepository.findAll();
    }

    // retrieve highest price of a given stock on given date
    @GetMapping("/high/{symbol}/{date}")
    public String retrieveHighestPrice(@PathVariable String date, @PathVariable String symbol) {
        qro = new QueryResultObject();
        qro.setDate(date);
        qro.setSymbol(symbol);
        qro.setMaxPrice(stockRepository.getMaxPriceByDateAndSymbol(date, symbol));

        return qro.maxToString();
    }

    // retrieve lowest price of a given stock on given date
    @GetMapping("/low/{symbol}/{date}")
    public String retrieveLowestPrice(@PathVariable String date, @PathVariable String symbol) {
        qro = new QueryResultObject();
        qro.setDate(date);
        qro.setSymbol(symbol);
        qro.setMinPrice(stockRepository.getMinPriceByDateAndSymbol(date, symbol));

        return qro.minToString();
    }

    // retrieve total volume of a given stock traded on given date
    @GetMapping("total/{symbol}/{date}")
    public String retrieveTotalVolume(@PathVariable String date, @PathVariable String symbol) {
        qro = new QueryResultObject();
        qro.setDate(date);
        qro.setSymbol(symbol);
        qro.setTotalVol(stockRepository.getTotalVolumeByDateAndSymbol(date, symbol));

        return qro.volToString();
    }

    // retrieve aggregated data summary
    @GetMapping("datasummary/{symbol}/{date}")
    public String retrieveAggregatedData(@PathVariable String date, @PathVariable String symbol) {
        qro = new QueryResultObject();
        qro.setDate(date);
        qro.setSymbol(symbol);
        qro.setMaxPrice(stockRepository.getMaxPriceByDateAndSymbol(date, symbol));
        qro.setMinPrice(stockRepository.getMinPriceByDateAndSymbol(date, symbol));
        qro.setTotalVol(stockRepository.getTotalVolumeByDateAndSymbol(date, symbol));

        return qro.toString();
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
