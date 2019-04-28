package array;

public class Q1033MovingStonesUntilConsecutive {

    //Difficulty: easy
    //TAG: array

    /**
     * 1033. Moving Stones Until Consecutive
     * Three stones are on a number line at positions a, b, and c.
     *
     * Each turn, let's say the stones are currently at positions x, y, z with x < y < z.  You pick up the stone at either position x or position z, and move that stone to an integer position k, with x < k < z and k != y.
     *
     * The game ends when you cannot make any more moves, ie. the stones are in consecutive positions.
     *
     * When the game ends, what is the minimum and maximum number of moves that you could have made?  Return the answer as an length 2 array: answer = [minimum_moves, maximum_moves]
     *
     *
     *
     * Example 1:
     *
     * Input: a = 1, b = 2, c = 5
     * Output: [1, 2]
     * Explanation: Move stone from 5 to 4 then to 3, or we can move it directly to 3.
     * Example 2:
     *
     * Input: a = 4, b = 3, c = 2
     * Output: [0, 0]
     * Explanation: We cannot make any moves.
     *
     *
     * Note:
     *
     * 1 <= a <= 100
     * 1 <= b <= 100
     * 1 <= c <= 100
     * a != b, b != c, c != a
     */

    /*
    Solution:

    for max steps, can always conform to one rule, step one by one from left to mid and right to mid
    so max = mid - left - 1 + right - mid - 1

    for min steps, few cases:
    1. if mid == left + 1, right == mid + 1, like 5,6,7, then min step = 0
    2. if any left to mid or mid to right is 1 or smaller:
        2.1 5,6,...9, then move 9 to 7, 5,6,7, min = 1 right side same e.g. 2...6,7
        2.2 any two has 1 distance, e.g. 4..6,...9, move 9 to 5, no matter where right is, same to right side, min = 1
    3. other cases min = 2, e.g. 5...10...15, 5->9, 15->11, 9,10,11 min = 2

    Time: O(1)
    Space: O(1)
     */

    public int[] numMovesStones(int a, int b, int c) {
        int max = Math.max(a, b); max = Math.max(max, c);
        int min = Math.min(a, b); min = Math.min(min, c);
        int mid = a;
        if (b != max && b != min) mid = b;
        else if (c != max && c != min) mid = c;
        int minStep = 2;
        if (mid - min == 1 && max - mid == 1) minStep = 0;
        else if (mid - min <= 2 || max - mid <= 2) minStep = 1;
        return new int[]{minStep,
                max - min - 2};
    }

}
