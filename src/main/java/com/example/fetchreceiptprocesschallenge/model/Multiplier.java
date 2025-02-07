package com.example.fetchreceiptprocesschallenge.model;

public enum Multiplier {
    FIRST_RECEIPT(1, 1, "Default case, no multiplier"),
    FIFTH_RECEIPT(5, 2, "Every 5th receipt, multiply points by 2"),
    TENTH_RECEIPT(10, 3, "Every 10th receipt, multiply points by 3");

    int multiplier;
    int numOfReceipts;
    String description;

    Multiplier(int numOfReceipts, int multiplier, String description) {
        this.numOfReceipts = numOfReceipts;
        this.multiplier = multiplier;
        this.description = description;
    }

    public int getNumOfReceipts() {
        return numOfReceipts;
    }

    public int getMultiplier() {
        return multiplier;
    }

    public String getDescription() {
        return description;
    }

}
