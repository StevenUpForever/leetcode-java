package legacy_code.Problem71To80;

public class Sort_Colors {

    /**
     * 75. Sort Colors
     * Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent, with the colors in the order red, white and blue.

     Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

     Note:
     You are not suppose to use the library's sort function for this problem.

     click to show follow up.

     Follow up:
     A rather straight forward solution is a two-pass algorithm using counting sort.
     First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then 1's and followed by 2's.

     Could you come up with an one-pass algorithm using only constant space?
     */

    /**
     * Solution:
     * Rainbow sort, due to 3 colors, we need to have two index left and right, which when find 0, put at left of left (not include left), when met 2, put right of right (not include right), middle part is unknown
     * Until left met right, the sort is done
     *
     * Time: O(n)
     * Space: O(1)
     */

    public void sortColors(int[] nums) {
        //Left and right is the tag to separate 0 and 1, index is the loop index
        int left = 0, right = nums.length - 1, index = 0;
        //be aware of that when index == right it should do one more loop
        while (index <= right) {
            //Left cannot over the index otherwise index will be sure equals to 0 until left met right
            if (nums[index] == 0 && left < index) swap(nums, left++, index);
            //Should have index <= right but already in while loop
            else if (nums[index] == 2) swap(nums, right--, index);
            //Keep index at current place until replacement done when nums[index] == 1
            else index++;
        }
    }

    private void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

}
