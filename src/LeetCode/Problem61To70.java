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
    67. Add Binary
    Given two binary strings, return their sum (also a binary string).

For example,
a = "11"
b = "1"
Return "100".
     */
    /*
    Approach: use normal binary calculation from the end to front
    Time: O(Math.max(a.length(), b.length()))
    Spance: O(Math.max(a.length(), b.length()))
     */
    public String addBinary(String a, String b) {
        if (a == null || a.length() == 0) return b;
        else if (b == null || b.length() == 0) return a;
        int lena = a.length() - 1, lenb = b.length() - 1;
        int goesUp = 0;
        StringBuilder builder = new StringBuilder();
        while (lena >= 0 || lenb >= 0) {
            int cur = goesUp;
            if (lena >= 0) {
                cur += a.charAt(lena) - '0';
                lena--;
            }
            if (lenb >= 0) {
                cur += b.charAt(lenb) - '0';
                lenb--;
            }
            builder.append(cur % 2);
            goesUp = cur / 2;
        }
        if (goesUp != 0) builder.append(goesUp);
        return builder.reverse().toString();
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
