package Problem101To110;

import public_class.TreeNode;

public class Convert_Sorted_Array_to_BST {

    /**
     * 108. Convert Sorted Array to Binary Search Tree
     * Given an array where elements are sorted in ascending order, convert it to a height balanced bst.
     */

    /**
     * Solution:
     * As the property of bst, the middle number of bst is the root value, which the left of root is the middle of left part, right of root is the middle of right part
     *
     * Time: O(n)
     * Space: O(n)
     */

    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums.length == 0) return null;
        return sortedArrayToBSTHelper(nums, 0, nums.length - 1);
    }

    private TreeNode sortedArrayToBSTHelper(int[] nums, int left, int right) {
        if (left > right) return null;
        int mid = left + (right - left)/2;
        TreeNode cur = new TreeNode(nums[mid]);
        cur.left = sortedArrayToBSTHelper(nums, left, mid - 1);
        cur.right = sortedArrayToBSTHelper(nums, mid + 1, right);
        return cur;
    }

}
