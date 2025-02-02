package com.example.fetchreceiptprocesschallenge.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CurrencyConversionUtilTest {

    @Test
    void testParseCents() {
        int cents = CurrencyConversionUtil.parseCents("10.00");
        assertEquals(1000, cents);
        
        cents = CurrencyConversionUtil.parseCents("5.45");
        assertEquals(545, cents);
        
        cents = CurrencyConversionUtil.parseCents("100.99");
        assertEquals(10099, cents);
    }

    @Test
    void testIsRoundDollar() {
        assertTrue(CurrencyConversionUtil.isRoundDollar(1000));
        assertTrue(CurrencyConversionUtil.isRoundDollar(5000));
        assertFalse(CurrencyConversionUtil.isRoundDollar(1050));
    }

    @Test
    void testIsMultipleOfQuarter() {
        assertTrue(CurrencyConversionUtil.isMultipleOfQuarter(100));
        assertTrue(CurrencyConversionUtil.isMultipleOfQuarter(500));
        assertFalse(CurrencyConversionUtil.isMultipleOfQuarter(105));
    }
}
