package legacy_code.Problem61To70;

public class Plus_One {

    /**
     * 66. Plus One
     * Given a non-negative integer represented as a non-empty array of digits, plus one to the integer.

     You may assume the integer do not contain any leading zero, except the number 0 itself.

     The digits are stored such that the most significant digit is at the head of the list.
     */

    /**
     * Solution:
     * Due to add only one integer 1, so unless current digit could go up to 10, otherwise could add 1 to current digit and immediately return
     * If current digit add to 10, then set to 0, and post step to combine a 100... array
     *
     * Time: O(n)
     * Space: O(n)
     */

    public int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++;
                //Add 1 is end at this digit when < 9, then could return
                return digits;
            }
            //If not < 9, set to 0, no need to record a carry, due to if < 9 could return and cannot goes here
            digits[i] = 0;
        }
        //Post step only occur when all origin digits are set to 0 (no one if (digits[i] < 9) appeared)
        //A new array initially are all 0s same as current digits[]
        int[] res = new int[digits.length + 1];
        //Add one prefix 1 and return
        res[0] = 1;
        return res;
    }

}
