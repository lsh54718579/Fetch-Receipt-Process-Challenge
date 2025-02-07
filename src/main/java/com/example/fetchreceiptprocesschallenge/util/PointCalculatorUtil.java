package com.example.fetchreceiptprocesschallenge.util;


import com.example.fetchreceiptprocesschallenge.model.Receipt;
import com.example.fetchreceiptprocesschallenge.strategy.PointCalculationStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PointCalculatorUtil {


    private final List<PointCalculationStrategy> strategies;

    @Autowired
    public PointCalculatorUtil(List<PointCalculationStrategy> strategies) {
        this.strategies = strategies;
    }

    public long calculateTotalPoints(Receipt receipt, int multiplier) {
        return strategies.stream()
                .mapToLong(strategy -> strategy.calculatePoints(receipt, multiplier))
                .sum();
    }
}
