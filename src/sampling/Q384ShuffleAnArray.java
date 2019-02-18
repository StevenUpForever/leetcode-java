package sampling;

import java.util.Arrays;

public class Q384ShuffleAnArray {

    //Difficulty: Medium
    //TAG: LinkedIn
    //TAG: Sampling
    //TAG: Shuffle

    /**
     * 384. Shuffle an Array
     * Shuffle a set of numbers without duplicates.
     *
     * Example:
     *
     * // Init an array with set 1, 2, and 3.
     * int[] nums = {1,2,3};
     * Solution solution = new Solution(nums);
     *
     * // Shuffle the array [1,2,3] and return its result. Any permutation of [1,2,3] must equally likely to be returned.
     * solution.shuffle();
     *
     * // Resets the array back to its original configuration [1,2,3].
     * solution.reset();
     *
     * // Returns the random shuffling of array [1,2,3].
     * solution.shuffle();
     */

    /*
    Solution:
    Keep instance arr as nums, don't change arr, and when reset() just return arr itself

    when shuffle, shuffle number one by one in a copy of arr
    exchange the shuffle number with random picked number in rest of area in copy arr
     */

    class Solution {

        private int[] arr;

        public Solution(int[] nums) {
            this.arr = nums;
        }

        /** Resets the array to its original configuration and return it. */
        public int[] reset() {
            return arr;
        }

        /** Returns a random shuffling of the array. */
        public int[] shuffle() {
            int[] shuffle = Arrays.copyOf(arr, arr.length);
            for (int i = shuffle.length; i > 0; i--) {
                int random = (int)(Math.random() * i);
                int temp = shuffle[i - 1];
                shuffle[i - 1] = shuffle[random];
                shuffle[random] = temp;
            }
            return shuffle;
        }
    }

}
