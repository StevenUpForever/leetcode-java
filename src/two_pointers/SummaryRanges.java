package two_pointers;

import java.util.ArrayList;
import java.util.List;

public class SummaryRanges {

    //TAG: Google
    //TAG: array
    //TAG: two pointers
    //Difficulty: medium

    /**
     * 228. Summary Ranges
     * Given a sorted integer array without duplicates, return the summary of its ranges.

     Example 1:
     Input: [0,1,2,4,5,7]
     Output: ["0->2","4->5","7"]
     Example 2:
     Input: [0,2,3,4,6,8,9]
     Output: ["0","2->4","6","8->9"]
     */

    /**
     * Solution:
     * It's same as find all consecutive subarrays
     * Be aware if slow == fast cannot append ->
     * do a post step due to last number will not process
     *
     * Time: O(n)
     * Space: O(1)
     */

    public List<String> summaryRanges(int[] nums) {
        List<String> list = new ArrayList<>();
        if (nums == null || nums.length == 0) return list;
        int slow = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1] + 1) {
                list.add(slow == nums[i - 1] ? String.valueOf(slow) : slow + "->" + nums[i - 1]);
                slow = nums[i];
            }
        }
        list.add(slow == nums[nums.length - 1] ? String.valueOf(slow) : slow + "->" + nums[nums.length - 1]);
        return list;
    }

}
