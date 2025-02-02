package com.example.fetchreceiptprocesschallenge.repository;

import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/*
 Using a map as an in-memory storage since data doesn't need to be persisted between runs
 */
@Repository
public class ReceiptRepository {

    private final Map<String, Long> storage = new ConcurrentHashMap<>();

    //Generate UUID ID to receipt
    public String storeReceipt(long points) {
        String id = UUID.randomUUID().toString();
        storage.put(id, points);
        return id;
    }

    public long getPoints(String id) {
        return storage.getOrDefault(id, -1L);
    }
}
