package array.n_sum;

import java.util.Arrays;

public class Q611ValidTriangleNumber {

    //Difficulty: Medium
    //TAG: LinkedIn
    //TAG: array

    /**
     * 611. Valid Triangle Number
     * Given an array consists of non-negative integers, your task is to count the number of triplets chosen from the array that can make triangles if we take them as side lengths of a triangle.
     * Example 1:
     * Input: [2,2,3,4]
     * Output: 3
     * Explanation:
     * Valid combinations are:
     * 2,3,4 (using the first 2)
     * 2,3,4 (using the second 2)
     * 2,2,3
     * Note:
     * The length of the given array won't exceed 1000.
     * The integers in the given array are in the range of [0, 1000].
     */

    /*
    Solution 1:
    for i = 0 for j = i + 1 for k = len - 1 loop
    third loop until nums[k] < nums[i] + nums[j] then all numbers are valid combinations, add k - j to res

    Time: O(nlogn + n^3)
    Space: O(1)
     */

    public int triangleNumber(int[] nums) {
        if (nums == null) return 0;
        Arrays.sort(nums);
        int count = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                int k = nums.length - 1;
                while (k > j && nums[k] >= nums[i] + nums[j]) k--;
                count += k - j;
            }
        }
        return count;
    }

    /*
    Solution 2:
    Make the i loop as largest number, i to be the pivot, loop left right from 0 and i - 1 to mid
    nums[l] + nums[r] > nums[i] means valid combinations, res += r - l, and we move r--, due to all after l will be
    in these valid combinations (if l + r > i, then l + 1 + r > i) try smaller r
    otherwise if nums[l] + nums[r] <= nums[i] not valid, move l++, (if l + r <= i then l + r - 1 <= i)

    Time: O(n^2)
    Space: O(1)
     */

    public int triangleNumber2(int[] nums) {
        Arrays.sort(nums);
        int result = 0;
        for (int i = 2; i < nums.length; i++) {
            int left = 0, right = i - 1;
            while (left < right) {
                if (nums[left] + nums[right] > nums[i]) {
                    result += right - left;
                    right--;
                }
                else left++;
            }
        }
        return result;
    }

}
