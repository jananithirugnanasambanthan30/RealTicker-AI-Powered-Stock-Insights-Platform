package com.realticker.backend1.service;
import com.realticker.backend1.model.Stock;
import org.springframework.stereotype.Service;
import java.util.List;
import com.realticker.backend1.model.HistoricalPrice;
import java.util.ArrayList;
import java.util.Random;

@Service
public class StockService {

    public List<Stock> getTop10Stocks() {
        return List.of(
                new Stock("AAPL", "Apple Inc", 185.40, 1.2, 78000000),
                new Stock("MSFT", "Microsoft", 412.30, 0.8, 45000000),
                new Stock("GOOGL", "Alphabet Inc", 142.10, -0.5, 32000000),
                new Stock("AMZN", "Amazon", 178.20, 0.6, 29000000),
                new Stock("TSLA", "Tesla", 218.75, -1.1, 51000000),
                new Stock("NVDA", "NVIDIA Corp", 720.15, 2.3, 65000000),
                new Stock("META", "Meta Platforms", 498.60, 1.7, 38000000),
                new Stock("NFLX", "Netflix", 615.45, -0.9, 21000000),
                new Stock("INTC", "Intel Corp", 42.80, 0.4, 47000000),
                new Stock("ORCL", "Oracle Corp", 132.70, -0.3, 26000000)
        );
    }

    public List<HistoricalPrice> getHistory(String ticker) {

        List<HistoricalPrice> history = new ArrayList<>();
        Random random = new Random();

        double basePrice = 100 + random.nextInt(200);

        for (int i = 1; i <= 180; i++) {
            double dailyFluctuation = random.nextDouble() * 6 - 2;
            basePrice += dailyFluctuation;

            history.add(new HistoricalPrice(
                    "Day " + i,
                    Math.round(basePrice * 100.0) / 100.0
            ));
        }

        return history;
    }
}
