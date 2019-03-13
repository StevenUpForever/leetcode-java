package tree.post_order;

import public_class.TreeNode;

public class MinimumDepthOfBinaryTree {

    //TAG: tree
    //TAG: up down
    //TAG bottom up
    //difficulty: easy

    /**
     * 111. Minimum Depth of Binary Tree
     * Given a binary tree, find its minimum depth.
     *
     * The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
     *
     * Note: A leaf is a node with no children.
     *
     * Example:
     *
     * Given binary tree [3,9,20,null,null,15,7],
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * return its minimum depth = 2.
     */

    /*
    Solution 1: bottom up
    corner case 1: when root == null return 0
    case 2: if any subtree is null, means left == null || right == null, cannot compare non 0 value (from other not null
    subtree) with 0 value, due to null subtree not valid, just return non 0 value + 1
    otherwise if both left & right tree not null, return min(left, right) + 1

    Time: O(n)
    Space: O(n)
     */

    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        if (left != 0 && right != 0) return Math.min(left, right) + 1;
        return left == 0 ? right + 1 : left + 1;
    }

    /*
    Solution 2:
    up to down dfs, need a global variable to save min value only if met leaf node (left == null && right == null)
    otherwise, only dfs to valid subtree

    Time: O(n)
    Space: O(n)
     */

    private int minHeight = Integer.MAX_VALUE;
    public int minDepth2(TreeNode root) {
        if (root == null) return 0;
        minDepth2Helper(root, 0);
        return minHeight;
    }

    private void minDepth2Helper(TreeNode root, int height) {
        if (root.left == null && root.right == null) {
            minHeight = Math.min(minHeight, height + 1);
            return;
        }
        if (root.left != null) minDepth2Helper(root.left, height + 1);
        if (root.right != null) minDepth2Helper(root.right, height + 1);
    }


}
