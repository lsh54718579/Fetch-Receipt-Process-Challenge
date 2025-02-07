package com.example.fetchreceiptprocesschallenge.strategy.impl;

import com.example.fetchreceiptprocesschallenge.model.Receipt;
import com.example.fetchreceiptprocesschallenge.service.ReceiptService;
import com.example.fetchreceiptprocesschallenge.strategy.PointCalculationStrategy;
import com.example.fetchreceiptprocesschallenge.util.CurrencyConversionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/*
25 points if the total is a multiple of 0.25.
 */
@Component
public class MultipleOfQuarterStrategy implements PointCalculationStrategy {
    private final Logger logger = LoggerFactory.getLogger(ReceiptService.class);

    @Override
    public long calculatePoints(Receipt receipt, int multiplier) {
        int totalCents = CurrencyConversionUtil.parseCents(receipt.getTotal());
        long points = CurrencyConversionUtil.isMultipleOfQuarter(totalCents) ? 25L * multiplier : 0;
        logger.info("Calculating the multiple of quarter" + points);
        return points;

    }
}