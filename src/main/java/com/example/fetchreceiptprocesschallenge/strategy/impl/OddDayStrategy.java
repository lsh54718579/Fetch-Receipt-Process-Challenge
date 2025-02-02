package com.example.fetchreceiptprocesschallenge.strategy.impl;

import com.example.fetchreceiptprocesschallenge.model.Receipt;
import com.example.fetchreceiptprocesschallenge.service.ReceiptService;
import com.example.fetchreceiptprocesschallenge.strategy.PointCalculationStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/*
6 points if the day in the purchase date is odd.
 */
@Component
class OddDayStrategy implements PointCalculationStrategy {
    private final Logger logger = LoggerFactory.getLogger(ReceiptService.class);

    @Override
    public long calculatePoints(Receipt receipt) {
        long points = receipt.getPurchaseDate().getDayOfMonth() % 2 != 0 ? 6 : 0;
        logger.info("Calculating OddDayStrategy is " + points);
        return points;
    }
}