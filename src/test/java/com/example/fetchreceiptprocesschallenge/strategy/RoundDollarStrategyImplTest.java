package com.example.fetchreceiptprocesschallenge.strategy;

import com.example.fetchreceiptprocesschallenge.model.Receipt;
import com.example.fetchreceiptprocesschallenge.strategy.impl.RoundDollarStrategyImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RoundDollarStrategyImplTest {

    private RoundDollarStrategyImpl roundDollarStrategy;

    @BeforeEach
    void setUp() {
        roundDollarStrategy = new RoundDollarStrategyImpl();
    }

    @Test
    void testCalculatePoints_WhenTotalIsRoundDollar() {
        Receipt receipt = new Receipt("Store", LocalDate.of(2024, 2, 1), "10:00", 
                                      List.of(), "5.00"); // "5.00" is a round dollar

        long points = roundDollarStrategy.calculatePoints(receipt);

        assertEquals(50, points);
    }

    @Test
    void testCalculatePoints_WhenTotalIsNotRoundDollar() {
        Receipt receipt = new Receipt("Store", LocalDate.of(2024, 2, 1), "10:00", 
                                      List.of(), "5.99"); // "5.99" is not a round dollar

        long points = roundDollarStrategy.calculatePoints(receipt);

        assertEquals(0, points);
    }

    @Test
    void testCalculatePoints_WhenTotalIsRoundDollarWithNoCents() {
        Receipt receipt = new Receipt("Store", LocalDate.of(2024, 2, 1), "10:00", 
                                      List.of(), "10.00"); // "10.00" is a round dollar

        long points = roundDollarStrategy.calculatePoints(receipt);

        assertEquals(50, points);
    }

    @Test
    void testCalculatePoints_WhenTotalHasCents() {
        Receipt receipt = new Receipt("Store", LocalDate.of(2024, 2, 1), "10:00", 
                                      List.of(), "15.50"); // "15.50" has cents

        long points = roundDollarStrategy.calculatePoints(receipt);

        assertEquals(0, points);
    }
}
