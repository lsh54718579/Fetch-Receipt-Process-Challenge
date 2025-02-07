package com.example.fetchreceiptprocesschallenge.util;

import com.example.fetchreceiptprocesschallenge.model.Multiplier;

public class MultiplierUtil {

    public static int getMultiplier(int numOfReceipts) {
        if(numOfReceipts % 10 == 0){
            return Multiplier.TENTH_RECEIPT.getMultiplier();
        }
        else if(numOfReceipts % 5 == 0){
            return Multiplier.FIFTH_RECEIPT.getMultiplier();
        }
        else
            return Multiplier.FIRST_RECEIPT.getMultiplier();
    }
}
