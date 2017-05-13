package Problem41To50;

import java.util.Arrays;

public class Multiply_Strings {

    /**
     * 43. Multiply Strings
     * Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2.

     Note:

     The length of both num1 and num2 is < 110.
     Both num1 and num2 contains only digits 0-9.
     Both num1 and num2 does not contain any leading zero.
     You must not use any built-in BigInteger library or convert the inputs to integer directly.
     */

    /**
     * Solution 1: Brute force
     * Follow the steps of a normal multiplication
     *      1. Multiple each char in num2 by the whole string num1, and save to an array
     *      2. in the array, add one carry each time, and add all strings together, is the final result
     * Time: O(mn) + O(m + m + ... + m) number of n = O(2mn) = O(mn) m represent length of num1, n represent length of num2
     * Space: O(mn) space of array to store all strings
     */

    /**
     * Solution 2.1: Optimized Brute force
     * 1. Based on idea of solution 1, use an array to represent the final result, as when two int multiple together the result max length is len(num1) + len(num2), so alloc an array of length len(num1) + len(num2),
     *      *** when two numbers len >= 2, it's len1 + len2 - 1, when at least one len == 1, the total len max to len1 + len2, for example: 99 * 99 = len 3, 20 * 5 = len 3 ***
     * 2. Multiple each char in num2 by the whole string num1, and save to this array, be aware of the current carry, carry++ when num2 index1++, carry is current index1 so added index is index1 + index2 + 1 (+1 for for sure the valid bit like 20 * 5, consider about prefix 0s at post step)
     * 3. some digit in int array may over one bit, like 9 * 9, so transfer every digit to a total string
     * 4. skip all the prefix 0s of the final string
     * 5. return the right string when consider about len == 0, means "0", otherwise return the string
     *
     * Time: O(mn) + O(m + n) (real number transfer) + O(m + n) (skip 0s) = O(mn) + O(m + n)
     * Space: O(m + n) = O(m + n)
     */

    public String multiplyS2_1(String num1, String num2) {
        //Base case, when any string is invalid, return another one
        if (num1 == null || num1.length() == 0) return num2;
        if (num2 == null || num2.length() == 0) return num1;
        char[] chars1 = num1.toCharArray(), chars2 = num2.toCharArray();
        int[] res = new int[chars1.length + chars2.length];
        for (int i = chars1.length - 1; i >= 0; i--) {
            for (int j = chars2.length - 1; j >= 0; j--) {
                res[i + j + 1] += (chars1[i] - '0') * (chars2[j] - '0');
            }
        }
        int carry = 0;
        for (int i = res.length - 1; i >= 0; i--) {
            int realNum = (res[i] + carry)%10;
            carry = (res[i] + carry)/10;
            res[i] = realNum;
        }
        //reuse carry for skip all prefix 0s
        carry = 0;
        StringBuilder builder = new StringBuilder();
        while (carry < res.length && res[carry] == 0) carry++;
        while (carry < res.length) {
            builder.append(res[carry]);
            carry++;
        }
        return builder.length() == 0 ? "0" : builder.toString();
    }

    /**
     * Solution 2.2:
     * Depends on the Solution 2.1, put the real number (num % 10) to i + j, carry (number / 10) to i + j + 1 when in mn loop, skip the real number transfer step
     */

    public String multiplyS2_2(String num1, String num2) {
        //Base case, when any string is invalid, return another one
        if (num1 == null || num1.length() == 0) return num2;
        if (num2 == null || num2.length() == 0) return num1;
        char[] chars1 = num1.toCharArray(), chars2 = num2.toCharArray();
        int[] res = new int[chars1.length + chars2.length];
        for (int i = chars1.length - 1; i >= 0; i--) {
            for (int j = chars2.length - 1; j >= 0; j--) {
                //Set real number and carry to array
                int curNum = (chars1[i] - '0') * (chars2[j] - '0') + res[i + j + 1];
                res[i + j + 1] = curNum % 10;
                res[i + j] += curNum / 10;
            }
        }
        int carry = 0;
        StringBuilder builder = new StringBuilder();
        while (carry < res.length && res[carry] == 0) carry++;
        while (carry < res.length) {
            builder.append(res[carry]);
            carry++;
        }
        return builder.length() == 0 ? "0" : builder.toString();
    }

    /**
     * Solution 3: Divide and conquer
     * As the Integer.max = 2^31 - 1, so we divide the num1 and num2 for several times
     * for each time, make sure len(sub num1) + len(sub num2) - 1 < 10 (len of 2 ^ 31 - 1)
     *      1. parse these two substrings to integer and multiple together
     *      2. insert the result integer parsed string to the front
     * For the rest part of which string is longer part, insert into result string index 0
     *
     * Time: O(m/5 * n/5) = O(mn) when not consider the parse integer time
     * when consider about parse integer it's O(mn)
     * Space: O(m + n) = O(m + n)
     */



}
