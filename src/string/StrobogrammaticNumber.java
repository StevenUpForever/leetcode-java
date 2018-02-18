package string;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class StrobogrammaticNumber {

    //TAG: Google
    //TAG: String
    //Difficulty: Easy

    /**
     * 246. Strobogrammatic Number
     * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

     Write a function to determine if a number is strobogrammatic. The number is represented as a string.

     For example, the numbers "69", "88", and "818" are all strobogrammatic.
     */

    /**
     * Solution:
     * Similar to valid palindrome, the only valid strobogrammatic pair is 1-1, 6-9, 8-8, 0-0
     * So, loop from start end to middle, if char1 == char2 only 0, 1, 8 valid
     * if char1 != char2 only 6 - 9 or 9 - 6 valid
     *
     * At last need to check middle char if num is odd length means start == end, return if it's 0, 1, 8
     * otherwise return true
     *
     * Time: O(n)
     * Space: O(1)
     */

    public boolean isStrobogrammatic(String num) {
        if (num == null || num.length() == 0) return true;
        int start = 0, end = num.length() - 1;
        while (start < end) {
            char a = num.charAt(start), b = num.charAt(end);
            if (a == b) {
                if (a != '0' && a != '1' && a != '8') return false;
            } else {
                if (!((a == '6' && b == '9') || (a == '9' && b == '6'))) return false;
            }
            start++;
            end--;
        }
        if (start == end) {
            char c = num.charAt(start);
            return c == '0' || c == '1' || c == '8';
        }
        return true;
    }

}
