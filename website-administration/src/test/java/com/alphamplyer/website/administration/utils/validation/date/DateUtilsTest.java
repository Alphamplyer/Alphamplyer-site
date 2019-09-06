package com.alphamplyer.website.administration.utils.validation.date;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateUtilsTest {

    @Test
    void isValid() {
        assertTrue(DateUtils.isValid("1/12/98").x);
        assertTrue(DateUtils.isValid("01/12/1998").x);
        assertTrue(DateUtils.isValid("01/2/98").x);
        assertTrue(DateUtils.isValid("01/2/1998").x);
        assertTrue(DateUtils.isValid("01/02/98").x);
        assertTrue(DateUtils.isValid("01/02/1998").x);

        assertFalse(DateUtils.isValid("1/a2/98").x);
        assertFalse(DateUtils.isValid("a1/2/98").x);
        assertFalse(DateUtils.isValid("0b/2/1b98").x);
        assertFalse(DateUtils.isValid("01/0b/9b").x);
        assertFalse(DateUtils.isValid("0b/b0/1998").x);
        assertFalse(DateUtils.isValid("0b/b0/1998/aa").x);
        assertFalse(DateUtils.isValid("0b/b0/1998aaa").x);
        assertFalse(DateUtils.isValid("0b/1998").x);
        assertFalse(DateUtils.isValid("0b/b0").x);
    }
}