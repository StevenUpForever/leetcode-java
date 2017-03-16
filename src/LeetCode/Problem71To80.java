package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ChengzhiJia on 6/7/16.
 */
public class Problem71To80 {

    /*
    73. Set Matrix Zeroes
    Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.

click to show follow up.

Follow up:
Did you use extra space?
A straight forward solution using O(mn) space is probably a bad idea.
A simple improvement uses O(m + n) space, but still not the best solution.
Could you devise a constant space solution?
     */
    /*
    Approach 1: after set to 0, record in two sets represent the rows and cols had set to 0, to save set 0 time, Space: O(m + n)
    Approach 2:
     */
    /*
    Not clear question, not a good question
     */
    public void setZeroes(int[][] matrix) {
        boolean row = false, col = false;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    if (i == 0) row = true;
                    if (j == 0) col = true;
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        if (row) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[0][j] = 0;
            }
        }
        if (col) {
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][0] = 0;
            }
        }
    }

    /*
    75. Sort Colors
    Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent, with the colors in the order red, white and blue.

Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

Note:
You are not suppose to use the library's sort function for this problem.
Follow up:
A rather straight forward solution is a two-pass algorithm using counting sort.
First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then 1's and followed by 2's.

Could you come up with an one-pass algorithm using only constant space?
     */
    /*
    Approach: Rainbow sort, two index, which the element left than i not include i represent the 0s, right than j not include j represent 1s
    index between i and j not include i, to for loop the array every time
     */
    public void sortColors(int[] nums) {
        if (nums.length == 0) return;
        int i = 0, j = nums.length - 1, index = 0;
        while (i < j && index <= j) {
            //Make sure index is not equal to i, otherwise i will after index
            if (nums[index] == 0 && i != index) swap(nums, i++, index);
            else if (nums[index] == 2 && j != index) swap(nums, j--, index);
            else index++;
        }
    }

    private void swap(int[] nums, int a, int b) {
        int c = nums[a];
        nums[a] = nums[b];
        nums[b] = c;
    }

    /*
    78. Subsets
    Given a set of distinct integers, nums, return all possible subsets.

Note: The solution set must not contain duplicate subsets.

For example,
If nums = [1,2,3], a solution is:

[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
     */
    public List<List<Integer>> subsets(int[] nums) {
        Arrays.sort(nums);
        int len = 1 << nums.length;
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            ArrayList<Integer> list = new ArrayList<>();
            for (int j = 0; j < nums.length; j++) {
                if (((1<<j) & i) != 0) list.add(nums[j]);
            }
            result.add(list);
        }
        return result;
    }

}
