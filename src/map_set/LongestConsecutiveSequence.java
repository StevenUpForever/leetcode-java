package map_set;

import java.util.Arrays;
import java.util.HashMap;

public class LongestConsecutiveSequence {

    //TAG: Google
    //TAG: Facebook
    //TAG: map_set
    //Difficulty: Hard

    /**
     * 128. Longest Consecutive Sequence
     * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

     For example,
     Given [100, 4, 200, 1, 3, 2],
     The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.

     Your algorithm should run in O(n) complexity.
     */

    /**
     * non O(n) Solution:
     * Sort the array, and loop the array find the Longest Consecutive subarray
     *
     * Time: O(nlogn + n)
     * Space: O(1)
     */

    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        Arrays.sort(nums);
        int cur = 1, max = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] + 1 == nums[i]) {
                cur++;
                //Another situation is that nums[i - 1] == nums[i], e.g. 0,1,1,2 when at second 1, don't reset cur
            } else if (nums[i - 1] < nums[i]) {
                max = Math.max(max, cur);
                cur = 1;
            }
        }
        return Math.max(max, cur);
    }

    /**
     * O(n) solution:
     * The key point is, if use O(n), need some extra space,
     * Use HashMap or set to check if last value n - 1 or next value n + 1 is there, similar to two sum, to check if
     * current value could connect adjacent values to a sequence, due to need to keep a current max length,
     * use map to record value, length pair
     *
     * loop the nums, only when map not contains current value, do following, due to should not calculate same number
     * multiple times
     * Key point is each time when update the max length (also update global max length), need update left and right
     * bound of current Consecutive Sequence, so when next time a new value is inserted to connect two
     * Consecutive Sequence, it could use latest value of these two Consecutive Sequence from n - 1 and n + 1
     *
     * Time: O(n)
     * Space: O(n)
     */

    public int longestConsecutive2(int[] nums) {
        int res = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            /*
            Only evaluate number which not appeared in the map, due to all numbers in the map is already evaluated,
            should not evaluate same number multiple times
             */
            if (!map.containsKey(n)) {
                int left = map.containsKey(n - 1) ? map.get(n - 1) : 0;
                int right = map.containsKey(n + 1) ? map.get(n + 1) : 0;
                int sum = left + right + 1;
                map.put(n, sum);
                //Update res with max so that needn't to loop keys finally
                res = Math.max(res, sum);
                /*
                Key point: update left and right bound, so that when could insert next number, it will connect other
                scope left or right bound, which is the newest length
                 */
                map.put(n - left, sum);
                map.put(n + right, sum);
            }
        }
        return res;
    }

}
