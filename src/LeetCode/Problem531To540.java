package LeetCode;

/**
 * Created by ChengzhiJia on 3/25/17.
 */
public class Problem531To540 {
    /*
    537. Complex Number Multiplication
    Given two strings representing two complex numbers.

You need to return a string representing their multiplication. Note i2 = -1 according to the definition.

Example 1:
Input: "1+1i", "1+1i"
Output: "0+2i"
Explanation: (1 + i) * (1 + i) = 1 + i2 + 2 * i = 2i, and you need convert it to the form of 0+2i.
Example 2:
Input: "1+-1i", "1+-1i"
Output: "0+-2i"
Explanation: (1 - i) * (1 - i) = 1 + i2 - 2 * i = -2i, and you need convert it to the form of 0+-2i.
Note:

The input strings will not have extra blank.
The input strings will be given in the form of a+bi, where the integer a and b will both belong to the range of [-100, 100]. And the output should be also in this form.
     */
    int[] f(String a) {
        int pos = a.indexOf('+');
        return new int[]{Integer.parseInt(a.substring(0, pos)), Integer.parseInt(a.substring(pos + 1, a.length() - 1))};
    }

    public String complexNumberMultiply(String a, String b) {
        int[] cb = f(a);
        int[] wb = f(b);
        int real = cb[0] * wb[0] - cb[1] * wb[1];
        int img = cb[0] * wb[1] + cb[1] * wb[0];
        return String.format("%d+%di", real, img);
    }

}
