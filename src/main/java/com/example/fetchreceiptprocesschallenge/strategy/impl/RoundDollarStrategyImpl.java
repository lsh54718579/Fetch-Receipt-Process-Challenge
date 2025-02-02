package com.example.fetchreceiptprocesschallenge.strategy.impl;

import com.example.fetchreceiptprocesschallenge.model.Receipt;
import com.example.fetchreceiptprocesschallenge.service.ReceiptService;
import com.example.fetchreceiptprocesschallenge.strategy.PointCalculationStrategy;
import com.example.fetchreceiptprocesschallenge.util.CurrencyConversionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/*
    50 points if the total is a round dollar amount with no cents.
 */
@Component
public class RoundDollarStrategyImpl implements PointCalculationStrategy {
    private final Logger logger = LoggerFactory.getLogger(ReceiptService.class);

    @Override
    public long calculatePoints(Receipt receipt) {
        int totalCents = CurrencyConversionUtil.parseCents(receipt.getTotal());
        long points = CurrencyConversionUtil.isRoundDollar(totalCents) ? 50 : 0;
        logger.info("Calculating round dollar point" + points);
        return points;
    }
}
