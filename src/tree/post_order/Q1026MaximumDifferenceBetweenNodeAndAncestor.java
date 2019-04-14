package tree.post_order;

import public_class.TreeNode;

public class Q1026MaximumDifferenceBetweenNodeAndAncestor {

    //Difficulty: medium
    //TAG: tree
    //TAG: post order

    /**
     * 1026. Maximum Difference Between Node and Ancestor
     * Given the root of a binary tree, find the maximum value V for which there exists different nodes A and B where V = |A.val - B.val| and A is an ancestor of B.
     *
     * (A node A is an ancestor of B if either: any child of A is equal to B, or any child of A is an ancestor of B.)
     *
     *
     *
     * Example 1:
     *
     *
     *
     * Input: [8,3,10,1,6,null,14,null,null,4,7,13]
     * Output: 7
     * Explanation:
     * We have various ancestor-node differences, some of which are given below :
     * |8 - 3| = 5
     * |3 - 7| = 4
     * |8 - 1| = 7
     * |10 - 13| = 3
     * Among all possible differences, the maximum value of 7 is obtained by |8 - 1| = 7.
     *
     *
     * Note:
     *
     * The number of nodes in the tree is between 2 and 5000.
     * Each node will have value between 0 and 100000.
     */

    /*
    Solution:

    Post order traversal tree,
    on each level, we got value from left and right subtree, by int[min, max]
    we update global max by max(abs(cur - min), abs(cur - max))

    then return new int[] array which min includes min(min, cur), max includes max(max, cur)

    Time: O(n)
    Space: O(1)
     */

    private int max = 0;
    public int maxAncestorDiff(TreeNode root) {
        helper(root);
        return max;
    }

    public int[] helper(TreeNode root) {
        if (root == null) return new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE};
        int[] left = helper(root.left), right = helper(root.right);
        int curMin = Integer.MAX_VALUE;
        curMin = Math.min(curMin, left[0]); curMin = Math.min(curMin, right[0]);
        int curMax = Integer.MIN_VALUE;
        curMax = Math.max(curMax, left[1]); curMax = Math.max(curMax, right[1]);
        //Only if left and right at least has valid value (not null)
        if (curMin < Integer.MAX_VALUE) max = Math.max(max, Math.abs(root.val - curMin));
        if (curMax > Integer.MIN_VALUE) max = Math.max(max, Math.abs(root.val - curMax));
        return new int[]{Math.min(curMin, root.val), Math.max(curMax, root.val)};
    }

}
