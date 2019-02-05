package array;

import java.util.HashSet;
import java.util.Set;

public class Q633SumOfSquareNumbers {

    //TAG: LinkedIn
    //TAG: math
    //TAG: array
    //Difficulty: Easy

    /**
     * 633. Sum of Square Numbers
     * Given a non-negative integer c, your task is to decide whether there're two integers a and b such that a2 + b2 = c.

     Example 1:
     Input: 5
     Output: True
     Explanation: 1 * 1 + 2 * 2 = 5
     Example 2:
     Input: 3
     Output: False
     */

    /*
     * Solution 1:
     * left from 0 to right, right from n - 1 to left, if left^2 + right^2 < c, left++, otherwise right--
     *
     * Time: O(c)
     * Space: O(1)
     */

    public boolean judgeSquareSum(int c) {
        int l = 0, r = c;
        while (l < r) {
            int num = l * l + r * r;
            if (num == c) return true;
            else if (num < c) l++;
            else r--;
        }
        return false;
    }

    /*
     * Solution 2:
     * similar to two sum
     *
     * since above l will not exceed sqrt(c) and r will not smaller than sqrt(c)
     * so loop from 0 to sqrt(c), and check if hashSet contains c - l * l
     *
     * Time: O(sqrt(c))
     * Space: O(sqrt(c))
     */

    public boolean judgeSquareSum2(int c) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i <= Math.sqrt(c); i++) {
            set.add(i * i);
            if (set.contains(c - i * i)) return true;
        }
        return false;
    }


}
