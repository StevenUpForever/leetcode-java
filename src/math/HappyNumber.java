package math;

import java.util.HashSet;
import java.util.Set;

public class HappyNumber {

    //TAG: Uber
    //TAG: Math

    /**
     * 202. Happy Number
     * Write an algorithm to determine if a number is "happy".

     A happy number is a number defined by the following process: Starting with any positive integer, replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers.

     Example: 19 is a happy number

     1^2 + 9^2 = 82
     8^2 + 2^2 = 68
     6^2 + 8^2 = 100
     1^2 + 0^2 + 0^2 = 1
     */

    /**
     * Solution:
     * Key point is end up loop when in a cycle, specially when not 1
     * Use HashSet to filter the number, because if number if generated before,
     * it's next number will sometime go back to it again, will be a cycle
     */

    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        while (set.add(n)) {
            int temp = n;
            n = 0;
            while (temp > 0) {
                n += (temp%10) * (temp%10);
                temp /= 10;
            }
        }
        return n == 1;
    }

}

