package com.example.fetchreceiptprocesschallenge.strategy;

import com.example.fetchreceiptprocesschallenge.model.Item;
import com.example.fetchreceiptprocesschallenge.model.Receipt;
import com.example.fetchreceiptprocesschallenge.strategy.impl.DescriptionLengthStrategy;
import com.example.fetchreceiptprocesschallenge.util.CurrencyConversionUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DescriptionLengthStrategyTest {

    private DescriptionLengthStrategy descriptionLengthStrategy;

    @BeforeEach
    void setUp() {
        descriptionLengthStrategy = new DescriptionLengthStrategy();
    }

    @Test
    void testCalculatePoints_DescriptionMultipleOf3() {
        Item item = new Item("Apple Pie", "10.00");
        Receipt receipt = new Receipt("Walmart", LocalDate.of(2024, 2, 1), "14:30", 
                                      Collections.singletonList(item), "10.00");

        long points = descriptionLengthStrategy.calculatePoints(receipt);

        assertEquals(2, points);
    }



}
