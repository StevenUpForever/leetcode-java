package tree.reconstruct;

import public_class.TreeNode;

public class Q654MaximumBinaryTree {

    //Difficulty: medium
    //TAG: tree
    //TAG; reconstruct

    /**
     * 654. Maximum Binary Tree
     * Given an integer array with no duplicates. A maximum tree building on this array is defined as follow:
     *
     * The root is the maximum number in the array.
     * The left subtree is the maximum tree constructed from left part subarray divided by the maximum number.
     * The right subtree is the maximum tree constructed from right part subarray divided by the maximum number.
     * Construct the maximum tree by the given array and output the root node of this tree.
     *
     * Example 1:
     * Input: [3,2,1,6,0,5]
     * Output: return the tree root node representing the following tree:
     *
     *       6
     *     /   \
     *    3     5
     *     \    /
     *      2  0
     *        \
     *         1
     * Note:
     * The size of the given array will be in the range [1,1000].
     */

    /*
    Solution:
    Build the tree by current node and recursively left and right by max value in current scope and
    left = dfs(left, maxIndex - 1), right = dfs(maxIndex + 1, right)

    Time: O(n^2)
    Space: O(n)
     */

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return construct(nums, 0, nums.length - 1);
    }

    private TreeNode construct(int[] nums, int l, int r) {
        if (l > r) return null;
        int maxIndex = l, maxValue = nums[l];
        for (int index = l; index <= r; index++) {
            if (nums[index] > maxValue) {
                maxIndex = index;
                maxValue = nums[index];
            }
        }
        TreeNode node = new TreeNode(maxValue);
        node.left = construct(nums, l, maxIndex - 1);
        node.right = construct(nums, maxIndex + 1, r);
        return node;
    }

}
