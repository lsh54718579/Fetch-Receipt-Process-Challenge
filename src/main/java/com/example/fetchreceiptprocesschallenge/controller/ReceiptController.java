package com.example.fetchreceiptprocesschallenge.controller;


import com.example.fetchreceiptprocesschallenge.model.Receipt;
import com.example.fetchreceiptprocesschallenge.model.response.PointsResponse;
import com.example.fetchreceiptprocesschallenge.model.response.ProcessReceiptResponse;
import com.example.fetchreceiptprocesschallenge.service.ReceiptService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/receipts")
public class ReceiptController {
    
    private final ReceiptService receiptService;

    @Autowired
    public ReceiptController(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @PostMapping("/process")
    public ResponseEntity<ProcessReceiptResponse> processReceipt(@Valid @RequestBody Receipt receipt) {
        String receiptId = receiptService.processReceipt(receipt);
        ProcessReceiptResponse response = new ProcessReceiptResponse(receiptId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/points")
    public ResponseEntity<PointsResponse> getPoints(@PathVariable String id) {
        long points = receiptService.getPoints(id);
        PointsResponse pointsResponse = new PointsResponse(points);
        return ResponseEntity.ok(pointsResponse);
    }
}