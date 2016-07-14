package LeetCode;

/**
 * Created by ChengzhiJia on 6/5/16.
 */
public class Problem61To70 {
    /*
    66. Plus One
    Given a non-negative number represented as an array of digits, plus one to the number.

The digits are stored such that the most significant digit is at the head of the list.
     */
    public int[] plusOne(int[] digits) {
        // Write your code here
        if (digits == null) return null;
        if (digits.length == 0) {
            int[] result = {1};
            return result;
        }
        digits[digits.length - 1]++;
        boolean plus = false;
        int i = digits.length - 1;
        while (i >= 0) {
            if (plus) digits[i]++;
            if (digits[i] == 10) {
                plus = true;
                digits[i] = 0;
            } else plus = false;
            i--;
        }
        if (plus) {
            int[] result = new int[digits.length + 1];
            result[0] = 1;
            return result;
        } else return digits;
    }

    /*
    70. Climbing Stairs
    You are climbing a stair case. It takes n steps to reach to the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
     */
    public int climbStairs(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        if (n == 2) return 2;
        int result[] = new int[n];
        result[0] = 1;
        result[1] = 2;
        for (int i = 2; i < n; i++) {
            result[i] = result[i - 1] + result[i - 2];
        }
        return result[n - 1];
    }

}
