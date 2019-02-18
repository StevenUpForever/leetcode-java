package array;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Q349IntersectionOfTwoArrays {

    //Difficulty: Easy
    //TAG: LinkedIn
    //TAG: array

    /**
     * 349. Intersection of Two Arrays
     * Given two arrays, write a function to compute their intersection.
     *
     * Example 1:
     *
     * Input: nums1 = [1,2,2,1], nums2 = [2,2]
     * Output: [2]
     * Example 2:
     *
     * Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
     * Output: [9,4]
     * Note:
     *
     * Each element in the result must be unique.
     * The result can be in any order.
     */

    /*
    Solution:
    Use set filter one set number in another
    remember remove the number after filter it, Or use set as final container

    Time: O(m + n)
    Space: O(math.min(m, n)) for the list
     */

    public int[] intersection(int[] nums1, int[] nums2) {
        List<Integer> list = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        for (int num: nums1) set.add(num);
        for (int num: nums2) {
            if (set.contains(num)) {
                list.add(num);
                /*
                example:
                if set add 1, 2, 3
                another nums2, is 1, 2, 1
                if we don't remove contained number, we will add 1 twice in the set
                so we need to remove it
                 */
                set.remove(num);
            }
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) res[i] = list.get(i);
        return res;
    }

    /*
    Solution:
    sort both array, and two pointers from both start to end

    Time: O(mlogm + nlogn + math.max(m, n))
    Space: O(math.min(m, n))
     */

}
