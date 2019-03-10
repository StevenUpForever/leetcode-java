package array;

public class Q1007MinimumDominoRotationsForEqualRow {

    //Difficulty: medium
    //TAG: array

    /**
     * 1007. Minimum Domino Rotations For Equal Row
     * In a row of dominoes, A[i] and B[i] represent the top and bottom halves of the i-th domino.  (A domino is a tile with two numbers from 1 to 6 - one on each half of the tile.)
     *
     * We may rotate the i-th domino, so that A[i] and B[i] swap values.
     *
     * Return the minimum number of rotations so that all the values in A are the same, or all the values in B are the same.
     *
     * If it cannot be done, return -1.
     *
     *
     *
     * Example 1:
     *
     *
     *
     * Input: A = [2,1,2,4,2,2], B = [5,2,6,2,3,2]
     * Output: 2
     * Explanation:
     * The first figure represents the dominoes as given by A and B: before we do any rotations.
     * If we rotate the second and fourth dominoes, we can make every value in the top row equal to 2, as indicated by the second figure.
     * Example 2:
     *
     * Input: A = [3,5,1,2,3], B = [3,6,3,3,4]
     * Output: -1
     * Explanation:
     * In this case, it is not possible to rotate the dominoes to make one row of values equal.
     *
     *
     * Note:
     *
     * 1 <= A[i], B[i] <= 6
     * 2 <= A.length == B.length <= 20000
     */

    /*
    Solution:
    The possible equal value must be in A[0] or B[0] or neither, loop A and B and find the certain possible value(s)
    count number of possible values and return min of the possible value(s)

    Time: O(n)
    Space: O(1)
     */

    public int minDominoRotations(int[] A, int[] B) {
        /*
        We need make sure that possible1 and possible2 are different, e.g. [1, 2], [1, 1],
        so we'll go into the right if condition
         */
        int possible1 = A[0], possible2 = B[0] == A[0] ? -1 : B[0], count1 = 0, count2 = 0;
        //Find two possible equal values
        for (int i = 0; i < A.length; i++) {
            int num1 = A[i], num2 = B[i];
            if (possible1 != num1 && possible1 != num2) possible1 = -1;
            if (possible2 != num1 && possible2 != num2) possible2 = -1;
        }
        //calculate min count in A and B of the possible value, and return this min value
        if (possible1 == -1 && possible2 == -1) return -1;
        else if (possible1 == -1) {
            for (int i = 0; i < A.length; i++) {
                if (A[i] == B[i]) continue;
                if (A[i] == possible2) count1++;
                if (B[i] == possible2) count2++;
            }
        } else if (possible2 == -1) {
            for (int i = 0; i < A.length; i++) {
                if (A[i] == B[i]) continue;
                if (A[i] == possible1) count1++;
                if (B[i] == possible1) count2++;
            }
        } else {
            /*
            If there's two possible values, e.g. [1,2,2,2,1] [2,1,1,1,2]
            then find in any ONE array, find min count of 1 or 2 in above example
             */
            for (int i = 0; i < A.length; i++) {
                if (A[i] == B[i]) continue;
                if (A[i] == possible1) count1++;
                if (A[i] == possible2) count2++;
            }
        }
        return Math.min(count1, count2);
    }

}
