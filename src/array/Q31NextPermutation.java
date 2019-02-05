package array;

public class Q31NextPermutation {

    //TAG: Google
    //TAG: array
    //Difficulty: Medium

    /**
     * 31. Next Permutation
     * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

     If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).

     The replacement must be in-place, do not allocate extra memory.

     Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
     1,2,3 → 1,3,2
     3,2,1 → 1,2,3
     1,1,5 → 1,5,1
     */

    /**
     * Solution:
     * 1. loop from end of nums, find the first number which is in decedent order, means this place could be replaced by a larger number
     * 2. look back of right of this number, find the first number which is larger than it, and exchange them, this number is also the smallest larger number because in step 1, all numbers right of it is in decedent order
     * 3. sort right of current place number in ascendant order, make right numbers as smallest order
     *
     * Time:
     *      1. right -> left traversal O(n) 15432
     *      2. left -> right traversal O(n)
     *      3. sort right part O(n/2) because sort from decedent order (even after exchange from step 2) to ascendant order, just need to exchange left and right number and move left/right to middle
     *      O(2.5n) = O(n)
     *      Space: O(1)
     */

    public void nextPermutation(int[] nums) {
        int index = nums.length - 1;
        while (index > 0 && nums[index - 1] >= nums[index]) index--;
        if (index == 0) {
            /*
            Helper function is faster than O(nlogn) in this case
            Arrays.sort(nums, index, nums.length);
             */
            reverseArray(nums, 0, nums.length - 1);
            return;
        }
        for (int temp = index; temp < nums.length; temp++) {
            if (nums[temp] > nums[index - 1] && (temp == nums.length - 1 || nums[temp + 1] <= nums[index - 1])) {
                swap(nums, temp, index - 1);
                break;
            }
        }
        /*
        Helper function is faster than O(nlogn) in this case
        Arrays.sort(nums, index, nums.length);
         */
        reverseArray(nums, index, nums.length - 1);
    }

    private void reverseArray(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }

    private void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

}
