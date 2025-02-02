package com.example.fetchreceiptprocesschallenge.util;

/*
    Helper method to convert between cents, quarters and dollars
 */
public class CurrencyConversionUtil {

    public static int parseCents(String amount){
        return Integer.parseInt(amount.replace(".", ""));
    }

    public static boolean isRoundDollar(int totalCents){
        return totalCents % 100 == 0;
    }


    public static boolean isMultipleOfQuarter(int totalCents){
        return totalCents % 25 == 0;
    }

}
