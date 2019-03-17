package string;

import java.util.HashMap;
import java.util.Map;

public class NextClosestTime {

    //TAG: Google
    //TAG: String
    //Difficulty: Medium

    /**
     * 681. Next Closest Time
     * Given a time represented in the format "HH:MM", form the next closest time by reusing the current digits. There is no limit on how many times a digit can be reused.

     You may assume the given input string is always valid. For example, "01:34", "12:09" are all valid. "1:34", "12:9" are all invalid.

     Example 1:

     Input: "19:34"
     Output: "19:39"
     Explanation: The next closest time choosing from digits 1, 9, 3, 4, is 19:39, which occurs 5 minutes later.  It is not 19:33, because this occurs 23 hours and 59 minutes later.
     Example 2:

     Input: "23:59"
     Output: "22:22"
     Explanation: The next closest time choosing from digits 2, 3, 5, 9, is 22:22. It may be assumed that the returned time is next day's time since it is smaller than the input time numerically.
     */

    /*
     * Solution:
     * Loop from end to start, try to update to a value larger than current and smaller than max value of this bit
     *      after update, reset all behind value of this bit to min value
     * if cannot update all, reset all of them to min value in these 4 numbers
     *
     * Time: O(1) since array is max 4 length
     * Space: O(1)
     */
    public String nextClosestTime(String time) {
        //Use char[] array to replace char easy and in place
        char[] chars = time.toCharArray();
        //Map represent index -> max value could use pair
        Map<Integer, Character> map = new HashMap<>();
        map.put(0, '2');
        map.put(1, chars[0] < '2' ? '9' : '3');
        map.put(3, '5');
        map.put(4, '9');
        //Record a min value when need reset all values after updated bit
        char min = time.charAt(0);
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] < min) min = chars[i];
        }
        for (int i = chars.length - 1; i >= 0; i--) {
            if (i != 2 && chars[i] < map.get(i)) {
                //':' is '9' + 1
                char replaceMin = ':';
                /*
                If met a number that smaller than max value of current bit, e.g. max 2 at first bit
                loop time again to find a value that larger than current and smaller than max value
                 */
                for (int j = 0; j < chars.length; j++) {
                    if (chars[j] > chars[i] && chars[j] <= map.get(i))
                        if (chars[j] < replaceMin) replaceMin = chars[j];
                }
                //if found, replace and reset all behind bits, otherwise go to next bit
                if (replaceMin != ':') {
                    chars[i] = replaceMin;
                    for (int k = i + 1; k < chars.length; k++)
                        if (k != 2) chars[k] = min;
                    return new String(chars);
                }
            }
        }
        //If go this step, means all bits cannot update (all are max value) reset all to min value (next day)
        for (int i = 0; i < chars.length; i++) {
            if (i != 2) chars[i] = min;
        }
        return new String(chars);
    }

    /*
    Solution 2:
    try to set from last to start, first value that can be increased, e.g. 19:43 -> 19:44,
    if the value will over limit, e.g. 19:59 then set the digit to smallest number 19:x1 then do same thing on next digit

    Time: O(n)
    Space: O(1)
     */
    public String nextClosestTime2(String time) {
        //Used to easy find larger value of current digit in sequence
        boolean[] digits = new boolean[10];
        //max value on current position
        int[] maxNums = new int[]{2, time.charAt(0) == '2' ? 3 : 9, 10, 5, 9};
        StringBuilder builder = new StringBuilder();
        int smallest = 10;
        for (int i = 0; i < time.length(); i++) {
            char c = time.charAt(i);
            if (c != ':') {
                //Record digit at the digit index, and the smallest value
                digits[c - '0'] = true;
                smallest = Math.min(smallest, c - '0');
            }
            builder.append(c);
        }
        for (int i = 4; i >= 0; i--) {
            if (i == 2) continue;
            //next is smallest number larger than i, which will replace at index i
            int digit = builder.charAt(i) - '0', next = digit;
            for (int j = digit + 1; j < 10; j++) {
                if (digits[j]) {
                    next = j;
                    break;
                }
            }
            //If i is the largest number or over limit, set to smallest and goto next pre index
            if (next == digit || next > maxNums[i]) {
                builder.setCharAt(i, (char)(smallest + '0'));
                //If next could be increased, then increase and return
            } else {
                builder.setCharAt(i, (char)(next + '0'));
                return builder.toString();
            }
        }
        return builder.toString();
    }

}
