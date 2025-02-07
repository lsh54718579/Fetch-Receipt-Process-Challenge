package com.example.fetchreceiptprocesschallenge.controller;


import com.example.fetchreceiptprocesschallenge.model.Receipt;
import com.example.fetchreceiptprocesschallenge.model.response.PointsResponse;
import com.example.fetchreceiptprocesschallenge.model.response.ProcessReceiptResponse;
import com.example.fetchreceiptprocesschallenge.service.ReceiptService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/receipts")
public class ReceiptController {
    
    private final ReceiptService receiptService;
    private final Logger logger = LoggerFactory.getLogger(ReceiptService.class);

    @Autowired
    public ReceiptController(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @PostMapping(value = "/process/user/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProcessReceiptResponse> processReceipt(@Valid @RequestBody Receipt receipt,
                                                                 @PathVariable Long userId) {
        logger.info("Calling processReceipt");
        String receiptId = receiptService.processReceipt(userId, receipt);
        ProcessReceiptResponse response = new ProcessReceiptResponse(receiptId);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "user/{userId}/receipt/{receiptId}/points")
    public ResponseEntity<PointsResponse> getPoints(@PathVariable Long userId, @PathVariable String receiptId) {
        logger.info("Calling getPoints");
        long points = receiptService.getPoints(userId, receiptId);
        PointsResponse pointsResponse = new PointsResponse(points);
        return ResponseEntity.ok(pointsResponse);
    }
}