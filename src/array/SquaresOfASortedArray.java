package array;

public class SquaresOfASortedArray {

    //Difficulty: Easy
    //TAG: array

    /**
     * 977. Squares of a Sorted Array
     * Given an array of integers A sorted in non-decreasing order, return an array of the squares of each number, also in sorted non-decreasing order.
     *
     *
     *
     * Example 1:
     *
     * Input: [-4,-1,0,3,10]
     * Output: [0,1,9,16,100]
     * Example 2:
     *
     * Input: [-7,-3,2,3,11]
     * Output: [4,9,9,49,121]
     *
     *
     * Note:
     *
     * 1 <= A.length <= 10000
     * -10000 <= A[i] <= 10000
     * A is sorted in non-decreasing order.
     */

    /*
    Solution:
    this question is actually sort the Math.abs(A[i]), due to it is sorted array, which means for abs value of array
    it is ascending from 0 to two sides
    we could compare from largest abs values, means from left and right to middle, and insert into new array

    Time: O(n)
    Space: O(n)
     */

    public int[] sortedSquares(int[] A) {
        if (A == null) return new int[0];
        int[] res = new int[A.length];
        int l = 0, r = A.length - 1;
        int index = res.length - 1;
        while (l <= r) {
            if (Math.abs(A[l]) > Math.abs(A[r])) {
                res[index--] = A[l] * A[l];
                l++;
            } else {
                res[index--] = A[r] * A[r];
                r--;
            }
        }
        return res;
    }

}
