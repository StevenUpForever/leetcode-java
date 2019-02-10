package math;

public class Q991BrokenCalculator {

    //Difficulty: medium
    //TAG: math

    /**
     * 991. Broken Calculator
     * On a broken calculator that has a number showing on its display, we can perform two operations:
     *
     * Double: Multiply the number on the display by 2, or;
     * Decrement: Subtract 1 from the number on the display.
     * Initially, the calculator is displaying the number X.
     *
     * Return the minimum number of operations needed to display the number Y.
     *
     *
     *
     * Example 1:
     *
     * Input: X = 2, Y = 3
     * Output: 2
     * Explanation: Use double operation and then decrement operation {2 -> 4 -> 3}.
     * Example 2:
     *
     * Input: X = 5, Y = 8
     * Output: 2
     * Explanation: Use decrement and then double {5 -> 4 -> 8}.
     * Example 3:
     *
     * Input: X = 3, Y = 10
     * Output: 3
     * Explanation:  Use double, decrement and double {3 -> 6 -> 5 -> 10}.
     * Example 4:
     *
     * Input: X = 1024, Y = 1
     * Output: 1023
     * Explanation: Use decrement operations 1023 times.
     *
     *
     * Note:
     *
     * 1 <= X <= 10^9
     * 1 <= Y <= 10^9
     */

    /*
    Solution:
    dfs and bfs works but will take long time

    we need to fast approach Y, which similar to binary search
    we need to check Y is odd or even, when odd, need one more step to became even
    otherwise shrink to half
    why need make sure it's even number, e.g.: 19, we approach from 10 * 2, and finally need -1 became exactly 19

    Time: O(log(Y-X))
    Space: O(1)
     */

    public int brokenCalc(int X, int Y) {
        int res = 0;
        while (Y > X) {
            Y = Y % 2 > 0 ? Y + 1 : Y / 2;
            res++;
        }
        return res + X - Y;
    }

}
