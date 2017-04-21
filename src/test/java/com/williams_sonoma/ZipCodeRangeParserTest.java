package com.williams_sonoma;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Travis Spitze on 4/16/2017.
 *
 * Unit tests for {@link ZipCodeRangeParser}.
 */
public class ZipCodeRangeParserTest {

    @Test
    public void testNoOverlap() {
        String[] params = new String[]{"94133,94133", "94200,94299", "94600,94699"};
        List<ZipCodeRange> expected = createRanges(94133, 94133, 94200, 94299, 94600, 94699);
        ZipCodeRangeParser parser = new ZipCodeRangeParser(params);
        assertEquals("Unexpected ranges returned from parser.", expected, parser.getRanges());
    }

    @Test
    public void testOverlap() {
        String[] params = new String[]{"94133,94133", "94200,94299", "94226,94399"};
        List<ZipCodeRange> expected = createRanges(94133, 94133, 94200, 94399);
        ZipCodeRangeParser parser = new ZipCodeRangeParser(params);
        assertEquals("Unexpected ranges returned from parser.", expected, parser.getRanges());
    }

    @Test
    public void testCompleteOverlap() {
        String[] params = new String[]{"95000,95001", "94000,96000", "90000,90001"};
        List<ZipCodeRange> expected = createRanges(90000, 90001, 94000, 96000);
        ZipCodeRangeParser parser = new ZipCodeRangeParser(params);
        assertEquals("Unexpected ranges returned from parser.", expected, parser.getRanges());
    }

    private List<ZipCodeRange> createRanges(Integer... bounds) {
        List<ZipCodeRange> rangeList = new LinkedList<>();
        for (int i = 0; i < bounds.length-1; i+=2) {
            rangeList.add(new ZipCodeRange(bounds[i], bounds[i+1]));
        }
        return rangeList;
    }

}
