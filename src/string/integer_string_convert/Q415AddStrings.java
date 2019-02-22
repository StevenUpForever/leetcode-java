package string.integer_string_convert;

public class Q415AddStrings {

    //Difficulty: Easy
    //TAG: Snap
    //TAG: String

    /**
     * 415. Add Strings
     * Given two non-negative integers num1 and num2 represented as string, return the sum of num1 and num2.
     *
     * Note:
     *
     * The length of both num1 and num2 is < 5100.
     * Both num1 and num2 contains only digits 0-9.
     * Both num1 and num2 does not contain any leading zero.
     * You must not use any built-in BigInteger library or convert the inputs to integer directly.
     */

    /*
    Solution:

    add digit from end to start one by one from num1 and num2, add carry and record carry
    finally reverse the string

    Time: O(m + n + max(m, n))
    Space: O(max(m, n))
     */

    public String addStrings(String num1, String num2) {
        int i = num1.length() - 1, j = num2.length() - 1, carry = 0;
        StringBuilder builder = new StringBuilder();
        while (i >= 0 || j >= 0) {
            int digit1 = i >= 0 ? num1.charAt(i) - '0' : 0;
            int digit2 = j >= 0 ? num2.charAt(j) - '0' : 0;
            int sum = digit1 + digit2 + carry;
            builder.append(sum % 10);
            carry = sum / 10;
            i--;
            j--;
        }
        if (carry > 0) builder.append(carry);
        return builder.reverse().toString();
    }

}
