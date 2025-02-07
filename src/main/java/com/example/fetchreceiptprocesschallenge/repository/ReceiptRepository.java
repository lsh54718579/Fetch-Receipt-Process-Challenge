package com.example.fetchreceiptprocesschallenge.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/*
 Using a map as an in-memory storage since data doesn't need to be persisted between runs
 */
@Repository
public class ReceiptRepository {

    private final Map<String, Long> receiptId_points = new ConcurrentHashMap<>();
    private final Map<Long, Map<String, Long>> user_receipts = new ConcurrentHashMap<>();
    private final Logger logger = LoggerFactory.getLogger(ReceiptRepository.class);
    //Generate UUID ID to receipt
    public String storeReceipt(long userId, long points) {
        String id = UUID.randomUUID().toString();
        receiptId_points.put(id, points);
        user_receipts.put(userId, receiptId_points);
        return id;
    }


    public long getPointsByUserIdAndReceiptId(Long userId, String receiptId) {
        if(user_receipts.containsKey(userId)) {
            return user_receipts.get(userId).get(receiptId);
        }
        return -1L;
    }

    public int getNumberOfReceiptsByUserId(Long userId) {
        if(user_receipts.containsKey(userId)) {
            int size = user_receipts.get(userId).size();
            logger.info("Number of receipts by userId: {} is {}", userId, size);
            return size;
        }
        return 0;
    }
}
