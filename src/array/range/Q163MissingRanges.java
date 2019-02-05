package array.range;

import java.util.ArrayList;
import java.util.List;

public class Q163MissingRanges {

    //TAG: Google
    //TAG: array
    //Difficulty: Medium

    /**
     * 163. Missing Ranges
     * Given a sorted integer array where the range of elements are in the inclusive range [lower, upper], return its missing ranges.

     For example, given [0, 1, 3, 50, 75], lower = 0 and upper = 99, return ["2", "4->49", "51->74", "76->99"].
     */

    /**
     * Consider about all corner cases is the key point
     *
     * Time: O(n)
     * Space: O(1)
     */

    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> list = new ArrayList<>();
        int cur = lower;
        for (int i = 0; i < nums.length && cur <= upper; i++) {
            //Skip numbers smaller than lower or cur, if equals to cur, need cur++
            if (nums[i] <= cur) {
                if (nums[i] == cur) cur++;
                continue;
            }
            /*
            Use min number of real right (nums[i] - 1) or upper as right
            add range cur->right and move right to right + 2
             */
            int end = Math.min(nums[i] - 1, upper);
            list.add(range(cur, end));
            cur = nums[i] + 1;
        }
        //In case if array is end and haven't met upper
        if (cur <= upper) list.add(range(cur, upper));
        return list;
    }

    private String range(int v1, int v2) {
        return v1 == v2 ? String.valueOf(v1) : String.format("%d->%d", v1, v2);
    }

    /*
    From LeetCode accepted answer, nums [Int.max] lower = Int.max, upper = Int.max, answer is ["Int.min->Int.max"]
    make no sense
     */
    public List<String> findMissingRanges2(int[] nums, int lower, int upper) {
        List<String> res = new ArrayList<>();
        for (int n : nums) {
            if (n == Integer.MIN_VALUE) {
                lower = n + 1;
                continue;
            }
            if (lower == n - 1) res.add("" + lower);
            else if (lower < n - 1)   res.add(lower + "->" + (n - 1));
            if (n == Integer.MAX_VALUE)     return res; // added
            lower = n + 1;
        }
        // if (lower == Integer.MIN_VALUE) return res;
        if (lower == upper) res.add("" + lower);
        else if (lower < upper)   res.add(lower + "->" + upper);
        return res;
    }

}
