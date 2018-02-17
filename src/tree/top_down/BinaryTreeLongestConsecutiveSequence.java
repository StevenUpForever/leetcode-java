package tree.top_down;

import public_class.TreeNode;

public class BinaryTreeLongestConsecutiveSequence {

    //TAG: Google
    //TAG: DFS
    //TAG: tree
    //TAG: top down
    //Difficulty: Medium

    /**
     * 298. Binary Tree Longest Consecutive Sequence
     * Given a binary tree, find the length of the longest consecutive sequence path.

     The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The longest consecutive path need to be from parent to child (cannot be the reverse).

     For example,
     1
     \
     3
     / \
     2   4
     \
     5
     Longest consecutive sequence path is 3-4-5, so return 3.
     2
     \
     3
     /
     2
     /
     1
     Longest consecutive sequence path is 2-3,not3-2-1, so return 2.

     */

    /**
     * Solution:
     * DFS, when left or right is root + 1, increase cur length and update global max,
     * otherwise reset cur to 1 (one node length)
     *
     * Time: O(n)
     * Space: O(1)
     */

    public int longestConsecutive(TreeNode root) {
        if (root == null) return 0;
        int[] max = new int[]{1};
        longestConsecutiveHelper(root, max, 1);
        return max[0];
    }

    private void longestConsecutiveHelper(TreeNode root, int[] max, int cur) {
        if (root == null) return;
        if (root.left != null) {
            if (root.left.val == root.val + 1) {
                max[0] = Math.max(max[0], cur + 1);
                longestConsecutiveHelper(root.left, max, cur + 1);
            } else longestConsecutiveHelper(root.left, max, 1);
        }
        if (root.right != null) {
            if (root.right.val == root.val + 1) {
                max[0] = Math.max(max[0], cur + 1);
                longestConsecutiveHelper(root.right, max, cur + 1);
            } else longestConsecutiveHelper(root.right, max, 1);
        }
    }

    private void longestConsecutiveHelper2(TreeNode root, int[] max, int cur, int target) {
        if (root == null) return;
        cur = root.val == target ? cur + 1 : 1;
        max[0] = Math.max(max[0], cur);
        longestConsecutiveHelper2(root.left, max, cur, root.val + 1);
        longestConsecutiveHelper2(root.right, max, cur, root.val + 1);
    }




}
