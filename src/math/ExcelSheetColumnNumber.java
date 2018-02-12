package math;

public class ExcelSheetColumnNumber {

    //TAG: Uber
    //TAG: Math
    //Difficulty: Easy

    /**
     * 171. Excel Sheet Column Number
     * Related to question Excel Sheet Column Title

     Given a column title as appear in an Excel sheet, return its corresponding column number.

     For example:

     A -> 1
     B -> 2
     C -> 3
     ...
     Z -> 26
     AA -> 27
     AB -> 28
     */

    /**
     * Solution:
     * Similar to String to Integer, but not 10 bit number, it's 26 bit number
     *
     * Time: O(n)
     * Space: O(1)
     */

    public int titleToNumber(String s) {
        if (s == null || s.length() == 0) return 0;
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            res = 26 * res + s.charAt(i) - 'A' + 1;
        }
        return res;
    }

}
