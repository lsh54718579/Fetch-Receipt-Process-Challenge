package com.example.fetchreceiptprocesschallenge.strategy;


import com.example.fetchreceiptprocesschallenge.model.Receipt;

public interface PointCalculationStrategy {
    long calculatePoints(Receipt receipt, int multiplier);
}
