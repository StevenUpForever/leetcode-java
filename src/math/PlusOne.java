package math;

public class PlusOne {

    //TAG: Google
    //TAG: math
    //Difficulty: Math

    /**
     * 66. Plus One
     * Given a non-negative integer represented as a non-empty array of digits, plus one to the integer.

     You may assume the integer do not contain any leading zero, except the number 0 itself.

     The digits are stored such that the most significant digit is at the head of the list.
     */

    /**
     * Solution:
     * Loop digits from end to start, if smaller than 9, add 1 at current bit and return
     * Otherwise, reset current number to 0
     * If the loop ends, means all bits are 9, and reset to 0, assign a new array with length = digits.length + 1
     * first bit set to 1 and return
     *
     * Time: O(n)
     * Space: O(n)
     */

    public int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }
        int[] res = new int[digits.length + 1];
        res[0] = 1;
        return res;
    }

}
