package com.example.fetchreceiptprocesschallenge.strategy.impl;

import com.example.fetchreceiptprocesschallenge.model.Receipt;
import com.example.fetchreceiptprocesschallenge.service.ReceiptService;
import com.example.fetchreceiptprocesschallenge.strategy.PointCalculationStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/*
5 points for every two items on the receipt.

 */
@Component
public class ItemCountStrategy implements PointCalculationStrategy {
    private final Logger logger = LoggerFactory.getLogger(ReceiptService.class);

    @Override
    public long calculatePoints(Receipt receipt) {
        long points = (receipt.getItems().size() / 2) * 5L;
        logger.info("Calculating ItemCount" + points);
        return points;
    }
}