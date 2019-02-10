package array.sliding_window;

import java.util.HashMap;
import java.util.Map;

public class Q992SubarraysWithKDifferentIntegers {

    //Difficulty: Hard
    //TAG: array
    //TAG: sliding window

    /**
     * 992. Subarrays with K Different Integers
     * Given an array A of positive integers, call a (contiguous, not necessarily distinct) subarray of A good if the number of different integers in that subarray is exactly K.
     *
     * (For example, [1,2,3,1,2] has 3 different integers: 1, 2, and 3.)
     *
     * Return the number of good subarrays of A.
     *
     *
     *
     * Example 1:
     *
     * Input: A = [1,2,1,2,3], K = 2
     * Output: 7
     * Explanation: Subarrays formed with exactly 2 different integers: [1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2].
     * Example 2:
     *
     * Input: A = [1,2,1,3,4], K = 3
     * Output: 3
     * Explanation: Subarrays formed with exactly 3 different integers: [1,2,1,3], [2,1,3], [1,3,4].
     *
     *
     * Note:
     *
     * 1 <= A.length <= 20000
     * 1 <= A[i] <= A.length
     * 1 <= K <= A.length
     */

    /*
    Solution:
    Similar to pre sum array,  K distinct subarray = most K - most (K - 1)
    Time: O(n)
    Space: O(n)
     */

    public int subarraysWithKDistinct(int[] A, int K) {
        return mostK(A, K) - mostK(A, K - 1);
    }

    private int mostK(int[] A, int K) {
        int i = 0, count = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int index = 0; index < A.length; index++) {
            int num = A[index];
            map.put(num, map.getOrDefault(num, 0) + 1);
            if (map.get(num) == 1) K--;
            while (K < 0) {
                int pre = A[i++];
                map.put(pre, map.get(pre) - 1);
                if (map.get(pre) == 0) K++;
            }
            count += index - i + 1;
        }
        return count;
    }

}
