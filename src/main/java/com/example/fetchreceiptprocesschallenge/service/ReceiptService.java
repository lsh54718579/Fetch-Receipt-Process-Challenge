package com.example.fetchreceiptprocesschallenge.service;


import com.example.fetchreceiptprocesschallenge.model.Receipt;
import com.example.fetchreceiptprocesschallenge.repository.ReceiptRepository;
import com.example.fetchreceiptprocesschallenge.util.MultiplierUtil;
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

    public String processReceipt(Long userId, @Valid Receipt receipt) {
        int previousNumberOfReceipts = countNumberOfReceipts(userId);
        int currentNumOfReceipts = previousNumberOfReceipts + 1;
        logger.info("Current number of receipt for user {} is {}", userId, currentNumOfReceipts);
        int multiplier = MultiplierUtil.getMultiplier(currentNumOfReceipts);
        long points = pointCalculatorUtil.calculateTotalPoints(receipt, multiplier);
        return receiptRepo.storeReceipt(userId, points);
    }

    public long getPoints(Long userId, String receiptId) {
        logger.info("get points for user {} with receipt id {}", userId, receiptId);
        return receiptRepo.getPointsByUserIdAndReceiptId(userId, receiptId);
    }

    private int countNumberOfReceipts(Long userId) {
        return receiptRepo.getNumberOfReceiptsByUserId(userId);
    }

}
