package tree.bottom_up;

import public_class.TreeNode;

public class BinaryTreeMaximumPathSum {

    //TAG: Microsoft
    //TAG: Baidu
    //TAG: Google
    //TAG: tree
    //TAG: bottom up
    //Difficulty: hard

    /**
     * 124. Binary Tree Maximum Path Sum
     * Given a non-empty binary tree, find the maximum path sum.
     *
     * For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path must contain at least one node and does not need to go through the root.
     *
     * Example 1:
     *
     * Input: [1,2,3]
     *
     *        1
     *       / \
     *      2   3
     *
     * Output: 6
     * Example 2:
     *
     * Input: [-10,9,20,null,null,15,7]
     *
     *    -10
     *    / \
     *   9  20
     *     /  \
     *    15   7
     *
     * Output: 42
     */

    /*
    Solution:
    Post order traversal binary tree, for each level:
    we know maxSum from left and right, and current value
    if left or right maxSum < 0, just use 0, which means use no node from that side
    update maxValue with left + right + cur, cannot ignore cur, because cur may < 0 but we need at least one node
    then report max left or right value + cur to upper value

    Time: O(n)
    Space: O(n)
     */

    private int maxValue = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        maxPathSumHelper(root);
        return maxValue;
    }

    private int maxPathSumHelper(TreeNode root) {
        if (root == null) return 0;
        int left = Math.max(0, maxPathSumHelper(root.left));
        int right = Math.max(0, maxPathSumHelper(root.right));
        maxValue = Math.max(maxValue, left + right + root.val);
        return Math.max(left, right) + root.val;
    }

}
