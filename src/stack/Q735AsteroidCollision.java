package stack;

import java.util.Stack;

public class Q735AsteroidCollision {

    //Difficulty: medium
    //TAG: stack

    /**
     * 735. Asteroid Collision
     * We are given an array asteroids of integers representing asteroids in a row.
     *
     * For each asteroid, the absolute value represents its size, and the sign represents its direction (positive meaning right, negative meaning left). Each asteroid moves at the same speed.
     *
     * Find out the state of the asteroids after all collisions. If two asteroids meet, the smaller one will explode. If both are the same size, both will explode. Two asteroids moving in the same direction will never meet.
     *
     * Example 1:
     * Input:
     * asteroids = [5, 10, -5]
     * Output: [5, 10]
     * Explanation:
     * The 10 and -5 collide resulting in 10.  The 5 and 10 never collide.
     * Example 2:
     * Input:
     * asteroids = [8, -8]
     * Output: []
     * Explanation:
     * The 8 and -8 collide exploding each other.
     * Example 3:
     * Input:
     * asteroids = [10, 2, -5]
     * Output: [10]
     * Explanation:
     * The 2 and -5 collide resulting in -5.  The 10 and -5 collide resulting in 10.
     * Example 4:
     * Input:
     * asteroids = [-2, -1, 1, 2]
     * Output: [-2, -1, 1, 2]
     * Explanation:
     * The -2 and -1 are moving left, while the 1 and 2 are moving right.
     * Asteroids moving the same direction never meet, so no asteroids will meet each other.
     * Note:
     *
     * The length of asteroids will be at most 10000.
     * Each asteroid will be a non-zero integer in the range [-1000, 1000]..
     */

    /*
    Solution:
    Use stack to compare current number with latest number

    Time: O(n)
    Space: O(n)
     */

    public int[] asteroidCollision(int[] asteroids) {
        if (asteroids == null) return new int[0];
        Stack<Integer> stack = new Stack<>();
        for (int num: asteroids) {
            /*
            three cases just add num to stack:
            1. stack is empty
            2. peek is towards left, then whether num is towards left or right, just add to stack
            3. current num is towards right, then whether peek is left or right, just add to stack

            2 & 3 means peek and num are reverse direction or same side
             */
            if (stack.isEmpty() || stack.peek() < 0 || num >= 0) stack.push(num);
            //else if peek and num are relative direction, means peek to right and num to left
            else {
                //first pop all that smaller than num
                while (!stack.isEmpty() && stack.peek() >= 0 && stack.peek() < -num) {
                    stack.pop();
                }
                /*
                here we have few cases:
                1. all in stack are wiped out or towards left direction, means num still exist, add to stack
                2. else means has peek value, either peek == -num or peek > -num
                2.1 if peek == -num need pop this kind of peek once, e.g. 6,6,-6 -> 6
                2.2 if peek > -num do nothing
                 */
                if (stack.isEmpty() || stack.peek() < 0) {
                    stack.push(num);
                } else if (stack.peek() == -num) stack.pop();
            }
        }
        int[] res = new int[stack.size()];
        int index = stack.size() - 1;
        while (!stack.isEmpty()) res[index--] = stack.pop();
        return res;
    }

}
