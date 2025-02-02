package com.example.fetchreceiptprocesschallenge.strategy;

import com.example.fetchreceiptprocesschallenge.model.Receipt;
import com.example.fetchreceiptprocesschallenge.strategy.impl.OddDayStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OddDayStrategyTest {

    private OddDayStrategy oddDayStrategy;

    @BeforeEach
    void setUp() {
        oddDayStrategy = new OddDayStrategy();
    }

    @Test
    void testCalculatePoints_WhenDayIsOdd() {
        Receipt receipt = new Receipt("Store", LocalDate.of(2024, 2, 1), "10:00", 
                                      List.of(), "5.00"); // 1st Feb is odd day

        long points = oddDayStrategy.calculatePoints(receipt);

        assertEquals(6, points);
    }

    @Test
    void testCalculatePoints_WhenDayIsEven() {
        Receipt receipt = new Receipt("Store", LocalDate.of(2024, 2, 2), "10:00", 
                                      List.of(), "5.00"); // 2nd Feb is even day

        long points = oddDayStrategy.calculatePoints(receipt);

        assertEquals(0, points);
    }

    @Test
    void testCalculatePoints_WhenDayIsOddInLeapYear() {
        Receipt receipt = new Receipt("Store", LocalDate.of(2024, 2, 29), "10:00", 
                                      List.of(), "10.00"); // 29th Feb in a leap year is odd day

        long points = oddDayStrategy.calculatePoints(receipt);

        assertEquals(6, points);
    }

    @Test
    void testCalculatePoints_WhenDayIsEvenInLeapYear() {
        Receipt receipt = new Receipt("Store", LocalDate.of(2024, 2, 28), "10:00", 
                                      List.of(), "10.00"); // 28th Feb in a leap year is even day

        long points = oddDayStrategy.calculatePoints(receipt);

        assertEquals(0, points);
    }
}
