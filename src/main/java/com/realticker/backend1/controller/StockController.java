package com.realticker.backend1.controller;

import com.realticker.backend1.model.Stock;
import com.realticker.backend1.model.HistoricalPrice;
import com.realticker.backend1.service.AiAnalysisService;
import com.realticker.backend1.service.StockService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/api/stocks")

public class StockController {

    private final StockService stockService;
    private final AiAnalysisService aiAnalysisService;

    public StockController(StockService stockService,
                           AiAnalysisService aiAnalysisService) {
        this.stockService = stockService;
        this.aiAnalysisService = aiAnalysisService;
    }

    @GetMapping("/top10")
    public List<Stock> getTopStocks() {
        return stockService.getTop10Stocks();
    }

    @GetMapping("/{ticker}/history")
    public List<HistoricalPrice> getStockHistory(@PathVariable String ticker) {
        return stockService.getHistory(ticker);
    }

    @PostMapping("/{ticker}/analyze")
    public String analyzeStock(@PathVariable String ticker) {
        return aiAnalysisService.analyzeStock(
                stockService.getHistory(ticker)
        );
    }
}
