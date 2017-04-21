# ZipCodeRange

Given a collection of 5-digit ZIP code ranges (each range includes both their upper and lower bounds), produces the minimum number of ranges required to represent the same restrictions as the input. Works for any set of arbitrary ranges in any order. Ranges may or may not overlap. Console input format: #####,##### #####,#####
 
Example:
    Input: 94133,94133 94200,94299 94226,94399
    Output: [94133,94133] [94200,94399]