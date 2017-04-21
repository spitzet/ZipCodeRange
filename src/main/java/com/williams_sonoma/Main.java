package com.williams_sonoma;

/**
 * Created by Travis on 4/16/2017.
 */
public class Main {
    public static void main(String[] args) {
        ZipCodeRangeParser parser = new ZipCodeRangeParser(args);
        System.out.println(parser.getRanges());
    }
}
