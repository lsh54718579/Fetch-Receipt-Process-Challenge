package com.example.fetchreceiptprocesschallenge.strategy.impl;

import com.example.fetchreceiptprocesschallenge.model.Receipt;
import com.example.fetchreceiptprocesschallenge.service.ReceiptService;
import com.example.fetchreceiptprocesschallenge.strategy.PointCalculationStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/*
    One point for every alphanumeric character in the retailer name so a-z, A-Z, 0-9
 */
@Component
public class RetailerStrategyImpl implements PointCalculationStrategy {
    private final Logger logger = LoggerFactory.getLogger(ReceiptService.class);

    @Override
    public long calculatePoints(Receipt receipt) {
        long points = receipt.getRetailer().replaceAll("[^a-zA-Z0-9]", "").length();
        logger.info("Calculating retailer point" + points);
        return points;
    }
}
