package bit_operation;

public class Q1028ConvertToBaseNeg2 {

    //Difficulty: medium
    //TAG: math
    //TAG: binary

    /**
     * 1028. Convert to Base -2
     *Given a number N, return a string consisting of "0"s and "1"s that represents its value in base -2 (negative two).
     *
     * The returned string must have no leading zeroes, unless the string is "0".
     *
     *
     *
     * Example 1:
     *
     * Input: 2
     * Output: "110"
     * Explantion: (-2) ^ 2 + (-2) ^ 1 = 2
     * Example 2:
     *
     * Input: 3
     * Output: "111"
     * Explantion: (-2) ^ 2 + (-2) ^ 1 + (-2) ^ 0 = 3
     * Example 3:
     *
     * Input: 4
     * Output: "100"
     * Explantion: (-2) ^ 2 = 4
     *
     *
     * Note:
     *
     * 0 <= N <= 10^9
     */

    /*
    Solution:

    Still do similar to binary based on 2,
    diff is when mod value is < 0, that means current N is < 0, then we need a positive mod value append to binary string
    when i < 0, i must be -1, convert to 1, then N need N + 1

    Time: O(logN)
    Space: O(1)
     */

    public String baseNeg2(int N) {
        StringBuilder builder = new StringBuilder();
        while (N != 0) {
            int i = N % -2;
            N /= -2;
            if (i < 0) {
                i += 2;
                N++;
            }
            builder.append(i);
        }
        return builder.length() == 0 ? "0" : builder.reverse().toString();
    }

}
