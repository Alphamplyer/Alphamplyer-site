package com.alphamplyer.website.administration.utils.validation.date;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimestampUtilsTest {

    @Test
    void isValid() {
        assertTrue(TimestampUtils.isValid("1998-12-11 0:0:0.0").x);
        assertTrue(TimestampUtils.isValid("1998-12-11 12:0:0.0").x);
        assertTrue(TimestampUtils.isValid("1998-12-11 12:00:0.0").x);
        assertTrue(TimestampUtils.isValid("1998-12-11 12:00:00.0").x);
        assertTrue(TimestampUtils.isValid("1998-12-11 12:00:00.00").x);
        assertTrue(TimestampUtils.isValid("1998-12-11 12:00:00.000").x);
        assertTrue(TimestampUtils.isValid("98-12-11 12:00:00.000").x);
        assertTrue(TimestampUtils.isValid("98-2-11 12:00:00.000").x);
        assertTrue(TimestampUtils.isValid("98-2-1 12:00:00.000").x);


        assertFalse(TimestampUtils.isValid("982-1 12:00:00.000").x);
        assertFalse(TimestampUtils.isValid("98-21 12:00:00.000").x);
        assertFalse(TimestampUtils.isValid("98-2-1 1200:00.000").x);
        assertFalse(TimestampUtils.isValid("98-2-1 12:0000.000").x);
        assertFalse(TimestampUtils.isValid("98-2-1 12:00:00000").x);
        assertFalse(TimestampUtils.isValid("98-2-1 12:00:00:000").x);
    }
}