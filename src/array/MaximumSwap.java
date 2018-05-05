package array;

public class MaximumSwap {

    //TAG: Facebook
    //TAG: math
    //Difficulty: medium

    /**
     * 670. Maximum Swap
     * Given a non-negative integer, you could swap two digits at most once to get the maximum valued number. Return the maximum valued number you could get.
     *
     * Example 1:
     * Input: 2736
     * Output: 7236
     * Explanation: Swap the number 2 and the number 7.
     * Example 2:
     * Input: 9973
     * Output: 9973
     * Explanation: No swap.
     * Note:
     * The given number is in the range [0, 108]
     */

    /*
    Solution:
    Basically always put larger number at the front,
    which means keep a number which is current max, keep a index i which means from i now the cur max is the max number

    Solution from leetcode
    use buckets array to save latest appeared 0-9 digits
    loop the number char array, when any number larger than current digit in number can be found in bucket, then
    means this number could move to front, and exchange this number with current digit in number

    Time: O(n)
    Space: O(n)
     */

    public int maximumSwap(int num) {
        char[] digits = Integer.toString(num).toCharArray();
        int[] buckets = new int[10];
        for (int i = 0; i < digits.length; i++) {
            buckets[digits[i] - '0'] = i;
        }
        for (int i = 0; i < digits.length; i++) {
            //check any number k larger than current digit
            for (int k = 9; k > digits[i] - '0'; k--) {
                /*
                check if any latest appeared k can behind current digit, which means current digit can be replaced
                with a larger one
                 */
                if (buckets[k] > i) {
                    char tmp = digits[i];
                    digits[i] = digits[buckets[k]];
                    digits[buckets[k]] = tmp;
                    return Integer.valueOf(new String(digits));
                }
            }
        }
        return num;
    }

}
