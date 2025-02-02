package com.example.fetchreceiptprocesschallenge.service;

import com.example.fetchreceiptprocesschallenge.model.Receipt;
import com.example.fetchreceiptprocesschallenge.repository.ReceiptRepository;
import com.example.fetchreceiptprocesschallenge.util.PointCalculatorUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;

import java.time.LocalDate;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReceiptServiceTest {

    @Mock
    private PointCalculatorUtil pointCalculatorUtil;

    @Mock
    private ReceiptRepository receiptRepo;

    @Mock
    private Logger logger;

    @InjectMocks
    private ReceiptService receiptService;

    private Receipt sampleReceipt;

    @BeforeEach
    void setUp() {
        sampleReceipt = new Receipt(
                "Walmart",
                LocalDate.of(2024, 2, 1),
                "14:30",
                Collections.emptyList(),
                "20.99"
        );
    }

    @Test
    void testProcessReceipt() {
        long mockPoints = 150;
        String mockReceiptId = "receipt123";
        when(pointCalculatorUtil.calculateTotalPoints(sampleReceipt)).thenReturn(mockPoints);
        when(receiptRepo.storeReceipt(mockPoints)).thenReturn(mockReceiptId);
        String result = receiptService.processReceipt(sampleReceipt);
        assertNotNull(result);
        assertEquals(mockReceiptId, result);
        verify(pointCalculatorUtil, times(1)).calculateTotalPoints(sampleReceipt);
        verify(receiptRepo, times(1)).storeReceipt(mockPoints);
    }

    @Test
    void testGetPoints() {
        String receiptId = "receipt123";
        long mockPoints = 150;
        when(receiptRepo.getPoints(receiptId)).thenReturn(mockPoints);
        long result = receiptService.getPoints(receiptId);
        assertEquals(mockPoints, result);
        verify(receiptRepo, times(1)).getPoints(receiptId);
    }
}
