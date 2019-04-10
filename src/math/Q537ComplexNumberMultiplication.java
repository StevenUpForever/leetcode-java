package math;

public class Q537ComplexNumberMultiplication {

    //Difficulty: medium
    //TAG: string
    //TAG: math

    /**
     * 537. Complex Number Multiplication
     * Given two strings representing two complex numbers.
     *
     * You need to return a string representing their multiplication. Note i2 = -1 according to the definition.
     *
     * Example 1:
     * Input: "1+1i", "1+1i"
     * Output: "0+2i"
     * Explanation: (1 + i) * (1 + i) = 1 + i2 + 2 * i = 2i, and you need convert it to the form of 0+2i.
     * Example 2:
     * Input: "1+-1i", "1+-1i"
     * Output: "0+-2i"
     * Explanation: (1 - i) * (1 - i) = 1 + i2 - 2 * i = -2i, and you need convert it to the form of 0+-2i.
     * Note:
     *
     * The input strings will not have extra blank.
     * The input strings will be given in the form of a+bi, where the integer a and b will both belong to the range of [-100, 100]. And the output should be also in this form.
     */

    /*
    Solution:

    Time: O(a + b)
    Space: O(1)
     */

    public String complexNumberMultiply(String a, String b) {
        int[] arr1 = buildArr(a), arr2 = buildArr(b);
        int i = arr1[0] * arr2[0], j = arr1[0] * arr2[1] + arr1[1] * arr2[0], k = arr1[1] * arr2[1];
        return "" + (i - k) + "+" + j + "i";
    }

    private int[] buildArr(String str) {
        String[] strs = str.split("\\+");
        return new int[]{Integer.parseInt(strs[0]),
                        Integer.parseInt(strs[1].substring(0, strs[1].length() - 1))};
    }

}
