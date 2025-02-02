package com.example.fetchreceiptprocesschallenge.strategy;

import com.example.fetchreceiptprocesschallenge.model.Receipt;
import com.example.fetchreceiptprocesschallenge.strategy.impl.MultipleOfQuarterStrategy;
import com.example.fetchreceiptprocesschallenge.util.CurrencyConversionUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MultipleOfQuarterStrategyTest {

    private MultipleOfQuarterStrategy multipleOfQuarterStrategy;

    @Mock
    private CurrencyConversionUtil currencyConversionUtil;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        multipleOfQuarterStrategy = new MultipleOfQuarterStrategy();
    }

    @Test
    void testCalculatePoints_WhenTotalIsMultipleOfQuarter() {
        Receipt receipt = new Receipt("Store", LocalDate.of(2024, 2, 1), "10:00", 
                                      List.of(), "10.25"); // $10.25 -> multiple of 0.25

        long points = multipleOfQuarterStrategy.calculatePoints(receipt);

        assertEquals(25, points);
    }

    @Test
    void testCalculatePoints_WhenTotalIsNotMultipleOfQuarter() {
        Receipt receipt = new Receipt("Store", LocalDate.of(2024, 2, 1), "10:00", 
                                      List.of(), "10.30"); // $10.30 -> not a multiple of 0.25

        long points = multipleOfQuarterStrategy.calculatePoints(receipt);

        assertEquals(0, points);
    }

    @Test
    void testCalculatePoints_WhenTotalIsMultipleOfQuarter_ZeroCents() {
        Receipt receipt = new Receipt("Store", LocalDate.of(2024, 2, 1), "10:00", 
                                      List.of(), "5.00"); // $5.00 -> multiple of 0.25

        long points = multipleOfQuarterStrategy.calculatePoints(receipt);

        assertEquals(25, points);
    }

    @Test
    void testCalculatePoints_WhenTotalIsNotMultipleOfQuarter_CentNotDivisibleBy25() {
        Receipt receipt = new Receipt("Store", LocalDate.of(2024, 2, 1), "10:00", 
                                      List.of(), "10.15"); // $10.15 -> not a multiple of 0.25

        long points = multipleOfQuarterStrategy.calculatePoints(receipt);

        assertEquals(0, points);
    }
}
