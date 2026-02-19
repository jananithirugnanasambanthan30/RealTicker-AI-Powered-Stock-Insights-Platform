package com.realticker.backend1.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Stock {
    private String ticker;
    private String companyName;
    private double currentPrice;
    private double changePercent;
    private long volume;
}

