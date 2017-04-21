package com.williams_sonoma;

import java.util.Comparator;

/**
 * Created by Travis Spitze on 4/16/2017.
 *
 * ZIP code range with a lower and upper bound. Performs validation upon construction.
 */
public class ZipCodeRange implements Comparable<ZipCodeRange> {
    private int lower;
    private int upper;

    /**
     * Creates a ZipCodeRange with the given bounds, then calls {@link #validateBounds()}
     *
     * @param lower Lower bound
     * @param upper Upper bound
     */
    public ZipCodeRange(int lower, int upper) {
        this.lower = lower;
        this.upper = upper;
        validateBounds();
    }

    /**
     * Calls {@link #parseBounds(String)} to parse the passed argument into two Strings, then attempts to set the bounds
     * with these Strings. Calls {@link #validateBounds()} after bounds are set.
     * @param unparsedBounds
     */
    public ZipCodeRange(String unparsedBounds) {
        String[] bounds = parseBounds(unparsedBounds);

        try {
            lower = Integer.parseInt(bounds[0]);
            upper = Integer.parseInt(bounds[1]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Zip codes must be a positive integer. Correct format: #####,#####", e);
        }

        validateBounds();
    }

    /**
     * @return The lower bound
     */
    public int getLower() {
        return lower;
    }

    /**
     * Sets the lower bound then calls {@link #validateBounds()}.
     *
     * @param lower The lower bound
     */
    public void setLower(int lower) {
        this.lower = lower;
        validateBounds();
    }

    /**
     * @return The upper bound
     */
    public int getUpper() {
        return upper;
    }

    /**
     * Sets the upper bound then calls {@link #validateBounds()}.
     *
     * @param upper The lower bound
     */
    public void setUpper(int upper) {
        this.upper = upper;
        validateBounds();
    }

    /**
     * Sets the current ZipCodeRange's upper bound to the greater of the current ZipCodeRange's upper bound and the
     * passed ZipCodeRange's upper bound, then calls {@link #validateBounds()}
     * @param o The ZipCodeRange to merge with
     */
    public void mergeUpper(ZipCodeRange o) {
        upper = Math.max(upper, o.getUpper());
        validateBounds();
    }

    /**
     * Sets the current ZipCodeRange's lower bound to the greater of the current ZipCodeRange's lower bound and the
     * passed ZipCodeRange's lower bound, then calls {@link #validateBounds()}
     * @param o The ZipCodeRange to merge with
     */
    public void mergeLower(ZipCodeRange o) {
        lower = Math.min(lower, o.getLower());
        validateBounds();
    }

    /**
     * Splits the passed argument into exactly two 5 character Strings. Throws an IllegalArgumentException if the
     * passed argument is in the wrong format.
     *
     * @param rangeString   Format: #####,#####
     * @return The parsed String
     */
    private String[] parseBounds(String rangeString) {
        if (rangeString == null) {
            throw new IllegalArgumentException("Argument is null");
        }

        String[] bounds = rangeString.split(",");
        String wrongFormat = "Wrong format for argument: '" + rangeString + "'. Correct format is: #####,#####";
        if (bounds.length != 2) {
            throw new IllegalArgumentException(wrongFormat);
        }

        return bounds;
    }

    /**
     * Checks to see if the lower and upper bound are both 5 digit integers, and that lower is less than or equal to
     * upper. Throws an IllegalArgumentException if these conditions are not met.
     */
    private void validateBounds() {
        if (lower < 10000) {
            throw new IllegalArgumentException("Lower bound is less than 10000: " + lower);
        }

        if (upper > 99999) {
            throw new IllegalArgumentException("Upper bound is greater than 99999: " + upper);
        }

        if (lower > upper) {
            throw new IllegalArgumentException("Lower bound is greater than upper bound for range: " + this);
        }
    }

    /**
     * Checks to see if the passed ZipCodeRange's lower bound is between the bounds of the current ZipCodeRange.
     *
     * @param o The ZipCodeRange to check
     * @return  true if the passed ZipCodeRange overlaps the current ZipCodeRange, false otherwise
     */
    public boolean isOverlappingLower(ZipCodeRange o) {
        return (lower <= o.getLower() && upper >= o.getLower());
    }

    /**
     * Checks to see if the passed ZipCodeRange's upper bound is between the bounds of the current ZipCodeRange.
     *
     * @param o The ZipCodeRange to check
     * @return  true if the passed ZipCodeRange overlaps the current ZipCodeRange, false otherwise
     */
    public boolean isOverlappingUpper(ZipCodeRange o) {
        return (lower <= o.getUpper() && upper >= o.getUpper());
    }

    /**
     * Checks to see if the passed ZipCodeRange's lower or upper bound is between the bounds of the current
     * ZipCodeRange.
     *
     * @param o The ZipCodeRange to check
     * @return  true if the passed ZipCodeRange overlaps the current ZipCodeRange, false otherwise
     */
    public boolean isOverlapping(ZipCodeRange o) {
        return isOverlappingLower(o) || isOverlappingUpper(o);
    }

    /**
     * Compares the current ZipCodeRange to the passed ZipCodeRange based on their lower bounds, then on their upper
     * bounds.
     *
     * @param o The ZipCodeRange to compare against
     * @return  a negative integer, zero, or a positive integer if the current ZipCodeRange is less than, equal to, or
     *          greater than the passed ZipCodeRange.
     */
    @Override
    public int compareTo(ZipCodeRange o) {
        return Comparator.comparingInt(ZipCodeRange::getLower)
                .thenComparing(ZipCodeRange::getUpper)
                .compare(this, o);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof ZipCodeRange)) {
            return false;
        }

        return compareTo((ZipCodeRange)o) == 0;
    }

    @Override
    public String toString() {
        return "[" + lower + "," + upper + "]";
    }

}
