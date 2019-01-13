package array;

import java.util.Arrays;

public class LargestPerimeterTriangle {

    //Difficulty: easy
    //TAG: array

    /**
     * 976. Largest Perimeter Triangle
     * Given an array A of positive lengths, return the largest perimeter of a triangle with non-zero area, formed from 3 of these lengths.
     *
     * If it is impossible to form any triangle of non-zero area, return 0.
     *
     *
     *
     * Example 1:
     *
     * Input: [2,1,2]
     * Output: 5
     * Example 2:
     *
     * Input: [1,2,1]
     * Output: 0
     * Example 3:
     *
     * Input: [3,2,3,4]
     * Output: 10
     * Example 4:
     *
     * Input: [3,6,2,3]
     * Output: 8
     *
     *
     * Note:
     *
     * 3 <= A.length <= 10000
     * 1 <= A[i] <= 10^6
     */

    /*
    Solution:
    Max perimeter, so we can sort the array and start from the end
    triangle side rule is c < a + b, don't be over engineering

    Time: O(n)
    Space: O(1)
     */

    public int largestPerimeter(int[] A) {
        if (A == null || A.length == 0) return 0;
        Arrays.sort(A);
        for (int i = A.length - 1; i >= 2; i--) {
            /*
            1. If A[i] > A[i - 1] + A[i - 2] then must larger than A[i - 1] + A[i - 3] etc. so don't need inner loop
            2.
             */
            if (A[i] < A[i - 1] + A[i - 2]) {
                return A[i] + A[i - 1] + A[i - 2];
            }
        }
        return 0;
    }

}
