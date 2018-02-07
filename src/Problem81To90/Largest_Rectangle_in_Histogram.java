package Problem81To90;

import java.util.*;

public class Largest_Rectangle_in_Histogram {

    /**
     * 84. Largest Rectangle in Histogram
     * Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.


     Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].


     The largest rectangle is shown in the shaded area, which has area = 10 unit.

     For example,
     Given heights = [2,1,5,6,2,3],
     return 10.

     images:
     https://leetcode.com/problems/largest-rectangle-in-histogram/#/description
     */

    /**
     * Solution 1: Brute force
     * for each index i in heights, expands to left and right, keep a min height as the rectangle height, multiple by the current length, and update the global min area of rectangle
     *
     * Time: O(n^2)
     * Space: O(1)
     */

    /**
     * Solution 2:
     * Depends on solution 1, every index when expand to left and right, will cover parts of previous index expand areas, so try to find a way iterative every index once or twice to finish this
     *
     * For a index i in heights, the max rectangle the i could combine is the area between left first smaller than i (not include) to right first smaller than i (not include), so in this area i is the smallest value
     * For this point, we need to look back to find the first smaller number, which use stack is a good choice
     * For every index i in heights
     *      Store values in ascending order as much as possible
     *      When find a value smaller than stack.peek() (current largest value in stack)
     *           Begin to calculate the stack.peek() combine rectangle, pop this value cur
     *                  1. if cur is the only value in stack, area = cur * i
     *                  2. if not the only one, bottom is i - 1 - stack.peek() (peek is currently the first smaller one of cur) (exclusive the smaller ones) i is index so bottom = i + 1
     *                  3. reset i to previous index to start current i compare to cur - 1 again
     *
     * Time: O(2n) stack push once and pop once = O(n)
     * Space: O(n)
     */

    public int largestRectangleArea(int[] heights) {
        //stack used to store all indices in heights
        Stack<Integer> stack = new Stack<>();
        int i = 0, area = 0;
        //Due to at last when loop is done, we need to consider about last index rectangle, so we add a dummy index which value is 0 to help calculate the last rectangle
        while (i <= heights.length) {
            int curHeight = i == heights.length ? 0 : heights[i];
            //If current value is larger than peek, in a ascending order, add to stack
            if (stack.isEmpty() || heights[stack.peek()] < curHeight) {
                stack.push(i);
                i++;
            } else {
                int pop = stack.pop();
                //Due to i is index, so although is is cur.index + 1, but length of cur is i, (when no numbers in stack anymore, means pop is previously the only one number in stack)
                //Otherwise i - 1 is exclusive the ith and cur - 1 number (left first smaller one), and i - 1 - stack.peek(), only the area middle of cur
                area = Math.max(area, heights[pop] * (stack.isEmpty() ? i : i - 1 - stack.peek()));
            }
        }
        return area;
    }

}
