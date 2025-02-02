package com.example.fetchreceiptprocesschallenge.service;


import com.example.fetchreceiptprocesschallenge.model.Receipt;
import com.example.fetchreceiptprocesschallenge.repository.ReceiptRepository;
import com.example.fetchreceiptprocesschallenge.util.PointCalculatorUtil;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReceiptService {

    private final Logger logger = LoggerFactory.getLogger(ReceiptService.class);

    private final PointCalculatorUtil pointCalculatorUtil;
    private final ReceiptRepository receiptRepo;

    @Autowired
    public ReceiptService(PointCalculatorUtil pointCalculatorUtil, ReceiptRepository receiptRepo) {
        this.pointCalculatorUtil = pointCalculatorUtil;
        this.receiptRepo = receiptRepo;
    }

    public String processReceipt(@Valid Receipt receipt) {
        long points = pointCalculatorUtil.calculateTotalPoints(receipt);
        logger.info("total points is " + points);
        return receiptRepo.storeReceipt(points);
    }

    public long getPoints(String id) {
        return receiptRepo.getPoints(id);
    }

}
