package bit_operation;

public class TotalHammingDistance {

    //TAG: Facebook
    //TAG: bit operation
    //difficulty: medium

    /**
     * 477. Total Hamming Distance
     * The Hamming distance between two integers is the number of positions at which the corresponding bits are different.
     *
     * Now your job is to find the total Hamming distance between all pairs of the given numbers.
     *
     * Example:
     * Input: 4, 14, 2
     *
     * Output: 6
     *
     * Explanation: In binary representation, the 4 is 0100, 14 is 1110, and 2 is 0010 (just
     * showing the four bits relevant in this case). So the answer will be:
     * HammingDistance(4, 14) + HammingDistance(4, 2) + HammingDistance(14, 2) = 2 + 2 + 2 = 6.
     * Note:
     * Elements of the given array are in the range of 0 to 10^9
     * Length of the array will not exceed 10^4.
     */

    /*
    Solution 1:
    permutation all pairs, count 1s in a ^ b and add together

    Time: O(n^2 * lengthOfNum)
    Space: O(1)
     */

    /*
    Solution 2:
    due to
    Elements of the given array are in the range of 0 to 10^9
    Length of the array will not exceed 10^4.

     So solution 1 may over time limit,

     https://leetcode.com/problems/total-hamming-distance/solution/#approach-2-loop-over-the-bits-accepted

     https://leetcode.com/problems/total-hamming-distance/discuss/96226/Java-O(n)-time-O(1)-Space
     */

    public int totalHammingDistance(int[] nums) {
        int total = 0, n = nums.length;
        for (int j=0;j<32;j++) {
            int bitCount = 0;
            for (int i=0;i<n;i++)
                bitCount += (nums[i] >> j) & 1;
            total += bitCount*(n - bitCount);
        }
        return total;
    }

}
