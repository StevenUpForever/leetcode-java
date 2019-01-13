package array.pre_sum;

import java.util.HashMap;
import java.util.Map;

public class SubarraySumsDivisibleByK {

    //Difficulty: medium
    //TAG: array
    //TAG: two sum

    /**
     * 974. Subarray Sums Divisible by K
     * Given an array A of integers, return the number of (contiguous, non-empty) subarrays that have a sum divisible by K.
     *
     *
     *
     * Example 1:
     *
     * Input: A = [4,5,0,-2,-3,1], K = 5
     * Output: 7
     * Explanation: There are 7 subarrays with a sum divisible by K = 5:
     * [4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]
     *
     *
     * Note:
     *
     * 1 <= A.length <= 30000
     * -10000 <= A[i] <= 10000
     * 2 <= K <= 10000
     */

    /*
    Solution:
    The problem is regarding subarray sum, so pre-sum array is a good solution
    *** subarray sum div by k means presum[0, i] presum [0, j] has same mode value,
     * so if pre[0, i] % K == n && pre[0, j] % K == n then pre[i, j] % K == n - n == 0 ***
    So it became a two sum problem that the key is the mod value that mod = sum % K

    Time: O(n)
    Space: O(K) max mode value will from 0 - K
     */

    public int subarraysDivByK(int[] A, int K) {
        if (A == null || A.length == 0 || K == 0) return 0;
        int sum = 0, count = 0;
        Map<Integer, Integer> map = new HashMap<>();
        /*
        Initially mod 0 will make count++, in-order to avoid the wrong value from
        count += map.getOrDefault(sum, 0)
         */
        map.put(0, 1);
        for (int i = 0; i < A.length; i++) {
            sum = (sum + A[i]) % K;
            /*
            due to mod need to be a at least smallest pos number, which should not be abs(mod) but mod + K
            in that case, when a larger number mod2, could make mod == mod2, e.g. [-2, 7], K = 5
             */
            if (sum < 0) sum += K;
            count += map.getOrDefault(sum, 0);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }

}
