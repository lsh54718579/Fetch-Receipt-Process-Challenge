package com.example.fetchreceiptprocesschallenge.strategy;

import com.example.fetchreceiptprocesschallenge.model.Receipt;
import com.example.fetchreceiptprocesschallenge.strategy.impl.AfternoonPurchaseStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AfternoonPurchaseStrategyTest {

    private PointCalculationStrategy afternoonPurchaseStrategy;
    private Receipt sampleReceipt;

    @BeforeEach
    void setUp() {
        afternoonPurchaseStrategy = new AfternoonPurchaseStrategy();
    }

    @Test
    void testCalculatePoints_AfternoonPurchase() {
        sampleReceipt = new Receipt("Walmart", LocalDate.of(2024, 2, 1), "14:30", Collections.emptyList(), "20.99");
        long points = afternoonPurchaseStrategy.calculatePoints(sampleReceipt);
        assertEquals(10, points);
    }

    @Test
    void testCalculatePoints_BeforeAfternoon() {
        sampleReceipt = new Receipt("Walmart", LocalDate.of(2024, 2, 1), "13:59", Collections.emptyList(), "20.99");
        long points = afternoonPurchaseStrategy.calculatePoints(sampleReceipt);
        assertEquals(0, points);
    }

    @Test
    void testCalculatePoints_AfterAfternoon() {
        sampleReceipt = new Receipt("Walmart", LocalDate.of(2024, 2, 1), "16:01", Collections.emptyList(), "20.99");
        long points = afternoonPurchaseStrategy.calculatePoints(sampleReceipt);
        assertEquals(0, points);
    }

    @Test
    void testCalculatePoints_ExactlyAt4PM() {
        sampleReceipt = new Receipt("Walmart", LocalDate.of(2024, 2, 1), "16:00", Collections.emptyList(), "20.99");
        long points = afternoonPurchaseStrategy.calculatePoints(sampleReceipt);
        assertEquals(0, points);
    }

    @Test
    void testCalculatePoints_InvalidTimeFormat() {
        sampleReceipt = new Receipt("Walmart", LocalDate.of(2024, 2, 1), "invalid-time", Collections.emptyList(), "20.99");
        long points = afternoonPurchaseStrategy.calculatePoints(sampleReceipt);
        assertEquals(0, points);
    }
}
