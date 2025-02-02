package com.example.fetchreceiptprocesschallenge.strategy.impl;


import com.example.fetchreceiptprocesschallenge.model.Receipt;
import com.example.fetchreceiptprocesschallenge.service.ReceiptService;
import com.example.fetchreceiptprocesschallenge.strategy.PointCalculationStrategy;
import com.example.fetchreceiptprocesschallenge.util.CurrencyConversionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/*
If the trimmed length of the item description is a multiple of 3,
multiply the price by 0.2 and round up to the nearest integer.
The result is the number of points earned.
 */

@Component
public class DescriptionLengthStrategy implements PointCalculationStrategy {
    private final Logger logger = LoggerFactory.getLogger(ReceiptService.class);

    @Override
    public long calculatePoints(Receipt receipt) {
        return receipt.getItems().stream()
                .mapToLong(item -> {
                    String trimedString = item.getShortDescription().trim();
                    long points = 0L;
                    if (trimedString.length() % 3 == 0) {
                        int priceCents = CurrencyConversionUtil.parseCents(item.getPrice());
                        points = (long) Math.ceil(priceCents * 0.2 / 100);
                        logger.info("Calculating description length " + points);
                    }
                    return points;
                })
                .sum();
    }
}