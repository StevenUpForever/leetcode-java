package bit_operation;

public class TriplesWithBitwiseANDEqualToZero {

    //Difficulty: Hard
    //TAG: bit operation

    /**
     * 982. Triples with Bitwise AND Equal To Zero
     * Given an array of integers A, find the number of triples of indices (i, j, k) such that:
     *
     * 0 <= i < A.length
     * 0 <= j < A.length
     * 0 <= k < A.length
     * A[i] & A[j] & A[k] == 0, where & represents the bitwise-AND operator.
     *
     *
     * Example 1:
     *
     * Input: [2,1,3]
     * Output: 12
     * Explanation: We could choose the following i, j, k triples:
     * (i=0, j=0, k=1) : 2 & 2 & 1
     * (i=0, j=1, k=0) : 2 & 1 & 2
     * (i=0, j=1, k=1) : 2 & 1 & 1
     * (i=0, j=1, k=2) : 2 & 1 & 3
     * (i=0, j=2, k=1) : 2 & 3 & 1
     * (i=1, j=0, k=0) : 1 & 2 & 2
     * (i=1, j=0, k=1) : 1 & 2 & 1
     * (i=1, j=0, k=2) : 1 & 2 & 3
     * (i=1, j=1, k=0) : 1 & 1 & 2
     * (i=1, j=2, k=0) : 1 & 3 & 2
     * (i=2, j=0, k=1) : 3 & 2 & 1
     * (i=2, j=1, k=0) : 3 & 1 & 2
     *
     *
     * Note:
     *
     * 1 <= A.length <= 1000
     * 0 <= A[i] < 2^16
     */

    /*
    Solution 1:
    for for for loop find all triples, any triple & each other can be 0 will add related count of permutations

    due to only three numbers, we can easily know that:
    3 repeat numbers will have 1 permutations like 111
    2 repeat numbers will have 3 permutations like 121
    1 repeat numbers will have 6 permutations like 123

    Time: O(n^3)
    Space: O(1)
     */

    public int countTriplets(int[] A) {
        int count = 0;
        for (int i = 0; i < A.length; i++) {
            for (int j = i; j < A.length; j++) {
                for (int z = j; z < A.length; z++) {
                    if ((A[i] & A[j] & A[z]) == 0) {
                        int repeat = 1;
                        if (i == j && i == z) repeat = 3;
                        else if (i == j || j == z) repeat = 2;
                        if (repeat == 3) count++;
                        else if (repeat == 2) count += 3;
                        else count += 6;
                    }
                }
            }
        }
        return count;
    }

    /*
    Solution 2:
    dp, https://leetcode.com/problems/triples-with-bitwise-and-equal-to-zero/discuss/226721/Java-DP-O(3-*-216-*-n)-time-O(n)-space
     */

}
