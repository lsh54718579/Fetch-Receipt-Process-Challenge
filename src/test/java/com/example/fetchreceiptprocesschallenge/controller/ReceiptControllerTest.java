package com.example.fetchreceiptprocesschallenge.controller;

import com.example.fetchreceiptprocesschallenge.model.Receipt;
import com.example.fetchreceiptprocesschallenge.model.response.PointsResponse;
import com.example.fetchreceiptprocesschallenge.model.response.ProcessReceiptResponse;
import com.example.fetchreceiptprocesschallenge.service.ReceiptService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReceiptControllerTest {

    @Mock
    private ReceiptService receiptService;
    @InjectMocks
    private ReceiptController receiptController;

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
        String mockReceiptId = "12345";
        when(receiptService.processReceipt(sampleReceipt)).thenReturn(mockReceiptId);
        ResponseEntity<ProcessReceiptResponse> response = receiptController.processReceipt(sampleReceipt);
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockReceiptId, response.getBody().getId());
        verify(receiptService, times(1)).processReceipt(sampleReceipt);
    }

    @Test
    void testGetPoints() {
        String receiptId = "12345";
        long mockPoints = 100;
        when(receiptService.getPoints(receiptId)).thenReturn(mockPoints);
        ResponseEntity<PointsResponse> response = receiptController.getPoints(receiptId);
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockPoints, response.getBody().getPoints());
        verify(receiptService, times(1)).getPoints(receiptId);
    }
}
