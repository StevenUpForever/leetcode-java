package tree.post_order;

import public_class.TreeNode;

public class LongestUnivaluePath {

    //TAG: Google
    //TAG: Tree
    //TAG: bottom up
    //Difficulty: Easy

    /**
     * 687. Longest Univalue Path
     * Given a binary tree, find the length of the longest path where each node in the path has the same value. This path may or may not pass through the root.

     Note: The length of path between two nodes is represented by the number of edges between them.

     Example 1:

     Input:

     5
     / \
     4   5
     / \   \
     1   1   5
     Output:

     2
     Example 2:

     Input:

     1
     / \
     4   5
     / \   \
     4   4   5
     Output:

     2
     Note: The given binary tree has not more than 10000 nodes. The height of the tree is not more than 1000.
     */

    /**
     * Solution:
     * DFS Tree bottom up
     * pass current root.val to next levels, so that when track back, could compare current node with parent root val
     *
     * update global max every time, since non same value path will return 0
     * if any sub node value is not equal to root, then return 0, start current longest path from beginning
     * else return max(left, right) + 1
     *
     * Time: O(n)
     * Space: (1)
     */

    public int longestUnivaluePath(TreeNode root) {
        if (root == null) return 0;
        int[] max = new int[]{0};
        longestUnivaluePathHelper(root, root.val, max);
        return max[0];
    }

    private int longestUnivaluePathHelper(TreeNode root, int val, int[] max) {
        if (root == null) return 0;
        int left = longestUnivaluePathHelper(root.left, root.val, max);
        int right = longestUnivaluePathHelper(root.right, root.val, max);
        max[0] = Math.max(max[0], left + right);
        if (val == root.val)  return Math.max(left, right) + 1;
        return 0;
    }

}
