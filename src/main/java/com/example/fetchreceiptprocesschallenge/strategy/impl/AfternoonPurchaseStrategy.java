package com.example.fetchreceiptprocesschallenge.strategy.impl;


import com.example.fetchreceiptprocesschallenge.model.Receipt;
import com.example.fetchreceiptprocesschallenge.service.ReceiptService;
import com.example.fetchreceiptprocesschallenge.strategy.PointCalculationStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

/*
    10 points if the time of purchase is after 2:00pm and before 4:00pm.

 */
@Component
public class AfternoonPurchaseStrategy implements PointCalculationStrategy {
    private final Logger logger = LoggerFactory.getLogger(ReceiptService.class);

    @Override
    public long calculatePoints(Receipt receipt) {
        try {
            LocalTime time = LocalTime.parse(receipt.getPurchaseTime());
            long points = time.isAfter(LocalTime.of(14, 0)) &&
                   time.isBefore(LocalTime.of(16, 0)) ? 10 : 0;
            logger.info("Calculating afternoon purchase point is " + points);
            return points;
        } catch (Exception e) {
            return 0;
        }
    }
}