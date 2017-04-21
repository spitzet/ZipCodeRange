package com.spitzet;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by Travis Spitze on 4/16/2017.
 *
 * Unit tests for {@link ZipCodeRange}.
 */
public class ZipCodeRangeTest {

    @Test
    public void testMergeUpper() {
        ZipCodeRange first = new ZipCodeRange(90000, 95000);
        ZipCodeRange second = new ZipCodeRange(91000, 96000);
        first.mergeUpper(second);
        ZipCodeRange expected = new ZipCodeRange(90000, 96000);
        assertEquals(expected, first);
    }

    @Test
    public void testMergeLower() {
        ZipCodeRange first = new ZipCodeRange(91000, 96000);
        ZipCodeRange second = new ZipCodeRange(90000, 95000);
        first.mergeLower(second);
        ZipCodeRange expected = new ZipCodeRange(90000, 96000);
        assertEquals(expected, first);
    }

    @Test
    public void testBadDataType() {
        try {
            ZipCodeRange range = new ZipCodeRange("a0000,90000");
            fail("Expected ZipCodeRange to throw IllegalArgumentException for bad data type: " + range);
        } catch (IllegalArgumentException e) {}
    }

    @Test
    public void testLowerTooSmall() {
        try {
            ZipCodeRange range = new ZipCodeRange("-90000,90000");
            fail("Expected ZipCodeRange to throw IllegalArgumentException for lower bound being too small: " + range);
        } catch (IllegalArgumentException e) {}
    }

    @Test
    public void testUpperTooLarge() {
        try {
            ZipCodeRange range = new ZipCodeRange("90000,900000");
            fail("Expected ZipCodeRange to throw IllegalArgumentException for upper bound being too large: " + range);
        } catch (IllegalArgumentException e) {}
    }

    @Test
    public void testMissingUpperBound() {
        try {
            ZipCodeRange range = new ZipCodeRange("90001");
            fail("Expected ZipCodeRange to throw IllegalArgumentException for missing upper bound: " + range);
        } catch (IllegalArgumentException e) {}
    }

    @Test
    public void testEmptyArguments() {
        try {
            ZipCodeRange range = new ZipCodeRange("");
            fail("Expected ZipCodeRange to throw IllegalArgumentException for empty arguments.");
        } catch (IllegalArgumentException e) {}
    }

    @Test
    public void testNullArguments() {
        try {
            ZipCodeRange range = new ZipCodeRange(null);
            fail("Expected ZipCodeRange to throw IllegalArgumentException for null arguments.");
        } catch (IllegalArgumentException e) {}
    }

    @Test
    public void testInvalidRangeString() {
        try {
            ZipCodeRange range = new ZipCodeRange("90000,89999");
            fail("Expected ZipCodeRange to throw IllegalArgumentException for invalid range: " + range);
        } catch (IllegalArgumentException e) {}
    }

    @Test
    public void testInvalidRangeInt() {
        try {
            ZipCodeRange range = new ZipCodeRange(90000, 89999);
            fail("Expected ZipCodeRange to throw IllegalArgumentException for invalid range: " + range);
        } catch (IllegalArgumentException e) {}
    }

    @Test
    public void testSetInvalidRange() {
        ZipCodeRange range = new ZipCodeRange(90000, 90000);
        try {
            range.setUpper(89999);
            fail("Expected ZipCodeRange to throw IllegalArgumentException for invalid range: " + range);
        } catch (IllegalArgumentException e) {}
    }

}
