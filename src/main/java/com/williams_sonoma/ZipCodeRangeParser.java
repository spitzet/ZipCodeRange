package com.williams_sonoma;

import java.util.*;

/**
 * Created by Travis Spitze on 4/16/2017.
 *
 * Given a collection of 5-digit ZIP code ranges (each range includes both their upper and lower bounds), produces the
 * minimum number of ranges required to represent the same restrictions as the input. Works for any set of arbitrary
 * ranges in any order. Ranges may or may not overlap. Console input format: #####,##### #####,#####
 *
 * Example:
 *      Input: 94133,94133 94200,94299 94226,94399
 *      Output: [94133,94133] [94200,94399]
 */
public class ZipCodeRangeParser {
    private List<ZipCodeRange> ranges;

    /**
     * Parses an array of ranges into a LinkedList of {@link ZipCodeRange}. Each range string must be in the following
     * format: #####,#####
     * @param rangesString  String representing the upper and lower bounds of a ZIP code range
     */
    public ZipCodeRangeParser(String[] rangesString) {
        ranges = new LinkedList<>();
        validateArguments(rangesString);

        for (String rangeString : rangesString) {
            ZipCodeRange range = new ZipCodeRange(rangeString);
            ranges.add(range);
        }
    }

    /**
     * If the passed argument is null or empty, throws an IllegalArgumentException
     * @param rangesString
     */
    private void validateArguments(String[] rangesString) {
        if (rangesString == null) {
            throw new IllegalArgumentException("Argument is null");
        }

        if (rangesString.length == 0) {
            throw new IllegalArgumentException("Argument is empty");
        }
    }

    /**
     * Sorts, merges, and returns a List of {@link ZipCodeRange}. Two ZipCodeRanges will be merged if their bounds
     * overlap. Since ranges is sorted by the value of the lower bound, we know that if current element's lower is
     * between the previous element's bounds, the ranges must be overlapping.
     *
     * @return  List of sorted and merged ZipCodeRange
     */
    public List<ZipCodeRange> getRanges() {
        // Sort the ranges so that the overlapping ranges can be found more efficiently
        Collections.sort(ranges);

        // The constructor's argument is validated to have at least one range, so it's safe to call iter.next()
        Iterator<ZipCodeRange> iter = ranges.iterator();
        ZipCodeRange prev = iter.next();

        // Check if each range is overlapping the previous range, and merge the ranges if they overlap
        while (iter.hasNext()) {
            ZipCodeRange current = iter.next();

            if (prev.isOverlappingLower(current)) {
                prev.mergeUpper(current);
                iter.remove();
            }

            prev = current;
        }

        return ranges;
    }
}
