package com.example.fetchreceiptprocesschallenge.strategy;

import com.example.fetchreceiptprocesschallenge.model.Item;
import com.example.fetchreceiptprocesschallenge.model.Receipt;
import com.example.fetchreceiptprocesschallenge.strategy.impl.ItemCountStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ItemCountStrategyTest {

    private ItemCountStrategy itemCountStrategy;

    @BeforeEach
    void setUp() {
        itemCountStrategy = new ItemCountStrategy();
    }

    @Test
    void testCalculatePoints_WithEvenItems() {
        Item item1 = new Item("Item 1", "5.00");
        Item item2 = new Item("Item 2", "3.00");
        Receipt receipt = new Receipt("Store", LocalDate.of(2024, 2, 1), "10:00", 
                                      List.of(item1, item2), "8.00");

        long points = itemCountStrategy.calculatePoints(receipt);

        assertEquals(5, points);
    }

    @Test
    void testCalculatePoints_WithOddItems() {
        Item item1 = new Item("Item 1", "5.00");
        Item item2 = new Item("Item 2", "3.00");
        Item item3 = new Item("Item 3", "2.50");
        Receipt receipt = new Receipt("Store", LocalDate.of(2024, 2, 1), "10:00", 
                                      List.of(item1, item2, item3), "10.50");

        long points = itemCountStrategy.calculatePoints(receipt);

        assertEquals(5, points);
    }

    @Test
    void testCalculatePoints_WithNoItems() {
        Receipt receipt = new Receipt("Store", LocalDate.of(2024, 2, 1), "10:00", 
                                      List.of(), "0.00");

        long points = itemCountStrategy.calculatePoints(receipt);

        assertEquals(0, points);
    }

    @Test
    void testCalculatePoints_WithSingleItem() {
        Item item1 = new Item("Item 1", "5.00");
        Receipt receipt = new Receipt("Store", LocalDate.of(2024, 2, 1), "10:00", 
                                      List.of(item1), "5.00");

        long points = itemCountStrategy.calculatePoints(receipt);

        assertEquals(0, points);
    }
}
