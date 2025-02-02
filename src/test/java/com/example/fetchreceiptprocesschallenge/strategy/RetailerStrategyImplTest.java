package com.example.fetchreceiptprocesschallenge.strategy;

import com.example.fetchreceiptprocesschallenge.model.Receipt;
import com.example.fetchreceiptprocesschallenge.strategy.impl.RetailerStrategyImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RetailerStrategyImplTest {

    private RetailerStrategyImpl retailerStrategy;

    @BeforeEach
    void setUp() {
        retailerStrategy = new RetailerStrategyImpl();
    }

    @Test
    void testCalculatePoints_WhenRetailerHasAlphanumericCharacters() {
        Receipt receipt = new Receipt("Store123", LocalDate.of(2024, 2, 1), "10:00", 
                                      List.of(), "5.00"); // "Store123" has 8 alphanumeric characters

        long points = retailerStrategy.calculatePoints(receipt);

        assertEquals(8, points);
    }

    @Test
    void testCalculatePoints_WhenRetailerHasNonAlphanumericCharacters() {
        Receipt receipt = new Receipt("Store@!#123", LocalDate.of(2024, 2, 1), "10:00", 
                                      List.of(), "5.00"); // "Store@!#123" has 8 alphanumeric characters

        long points = retailerStrategy.calculatePoints(receipt);

        assertEquals(8, points);
    }

    @Test
    void testCalculatePoints_WhenRetailerHasNoAlphanumericCharacters() {
        Receipt receipt = new Receipt("!@#$%^&*", LocalDate.of(2024, 2, 1), "10:00", 
                                      List.of(), "10.00"); // No alphanumeric characters

        long points = retailerStrategy.calculatePoints(receipt);

        assertEquals(0, points);
    }
}
