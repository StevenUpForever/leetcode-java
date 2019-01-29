package array.reverse;

public class RotateArray {

    //Difficulty: easy
    //TAG: Uber
    //TAG: array

    /**
     * 189. Rotate Array
     * Given an array, rotate the array to the right by k steps, where k is non-negative.
     *
     * Example 1:
     *
     * Input: [1,2,3,4,5,6,7] and k = 3
     * Output: [5,6,7,1,2,3,4]
     * Explanation:
     * rotate 1 steps to the right: [7,1,2,3,4,5,6]
     * rotate 2 steps to the right: [6,7,1,2,3,4,5]
     * rotate 3 steps to the right: [5,6,7,1,2,3,4]
     * Example 2:
     *
     * Input: [-1,-100,3,99] and k = 2
     * Output: [3,99,-1,-100]
     * Explanation:
     * rotate 1 steps to the right: [99,-1,-100,3]
     * rotate 2 steps to the right: [3,99,-1,-100]
     * Note:
     *
     * Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.
     * Could you do it in-place with O(1) extra space?
     */

    /*
    Solution:
    make k as pivot
    1. swap [0, k - 1]
    2. swap [k, len - 1]
    3. swap [0, len - 1]

    if first swap the whole array, then actually next need revert first k elements
    1. swap [0, len - 1]
    1. swap [0, k]
    2. swap [k + 1, len - 1]

    Time: O(n)
    Space: O(1)
     */

    public void rotate(int[] nums, int k) {
        if (nums == null || k <= 0) return;
        k = k % nums.length;
        swapArr(nums, 0, nums.length - 1 - k);
        swapArr(nums, nums.length - k, nums.length - 1);
        swapArr(nums, 0, nums.length - 1);
    }

    private void swapArr(int[] arr, int i, int j) {
        while (i < j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }
    }

}
