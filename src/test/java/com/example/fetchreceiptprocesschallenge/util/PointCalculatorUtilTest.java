package com.example.fetchreceiptprocesschallenge.util;

import com.example.fetchreceiptprocesschallenge.model.Item;
import com.example.fetchreceiptprocesschallenge.model.Receipt;
import com.example.fetchreceiptprocesschallenge.strategy.PointCalculationStrategy;
import com.example.fetchreceiptprocesschallenge.strategy.impl.AfternoonPurchaseStrategy;
import com.example.fetchreceiptprocesschallenge.strategy.impl.DescriptionLengthStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PointCalculatorUtilTest {

    @InjectMocks
    private PointCalculatorUtil pointCalculatorUtil;

    @Mock
    private PointCalculationStrategy afternoonPurchaseStrategy;

    @Mock
    private PointCalculationStrategy descriptionLengthStrategy;

    private Receipt receipt;

    @BeforeEach
    void setUp() {
        Item item = new Item("Apple Pie", "10.00");
        receipt = new Receipt("Walmart", LocalDate.of(2024, 2, 1), "14:30", 
                              List.of(item), "10.00");
    }



    @Test
    void testCalculateTotalPoints_EmptyStrategies() {
        PointCalculatorUtil emptyPointCalculatorUtil = new PointCalculatorUtil(List.of());
        long totalPoints = emptyPointCalculatorUtil.calculateTotalPoints(receipt);

        assertEquals(0, totalPoints);
    }



}