package sampling;

import java.util.*;

public class RandomPickIndex {

    //Difficulty: medium
    //TAG: Uber
    //TAG: sampling

    /**
     * 398. Random Pick Index
     * Given an array of integers with possible duplicates, randomly output the index of a given target number. You can assume that the given target number must exist in the array.
     *
     * Note:
     * The array size can be very large. Solution that uses too much extra space will not pass the judge.
     *
     * Example:
     *
     * int[] nums = new int[] {1,2,3,3,3};
     * Solution solution = new Solution(nums);
     *
     * // pick(3) should return either index 2, 3, or 4 randomly. Each index should have equal probability of returning.
     * solution.pick(3);
     *
     * // pick(1) should return 0. Since in the array only nums[0] is equal to 1.
     * solution.pick(1);
     */

    /*
    Solution:
    Use a map, add number, indices as key value pair

    when pick a number, check if there's a related list to target, if not return -1
    randomly pick up an index this list, return list.get(index)
     */

    class Solution {

        private Map<Integer, List<Integer>> map;
        private Random random;

        public Solution(int[] nums) {
            random = new Random();
            map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                map.putIfAbsent(nums[i], new ArrayList<>());
                map.get(nums[i]).add(i);
            }
        }

        public int pick(int target) {
            List<Integer> list = map.get(target);
            if (list == null || list.size() == 0) return -1;
            return list.get(random.nextInt(list.size()));
        }
    }

    /*
    Solution 2:
    Reservoir Sampling
    https://leetcode.com/problems/random-pick-index/discuss/88072/Simple-Reservoir-Sampling-solution
     */

}
