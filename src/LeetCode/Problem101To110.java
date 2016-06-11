package LeetCode;

/**
 * Created by ChengzhiJia on 6/10/16.
 */
public class Problem101To110 {

    /*
    108. Convert Sorted Array to Binary Search Tree
    Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
     */

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums.length == 0) return null;
        return createBST(0, nums.length - 1, nums);
    }

    private TreeNode createBST(int start, int end, int[] nums) {
        if (start <= end) {
            int middle = (start + end)/2;
            TreeNode node = new TreeNode(nums[middle]);
            node.left = createBST(start, middle - 1, nums);
            node.right = createBST(middle + 1, end, nums);
            return node;
        } else return null;
    }

}
