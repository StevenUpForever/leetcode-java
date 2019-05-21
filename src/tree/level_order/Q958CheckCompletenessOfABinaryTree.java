package tree.level_order;

import public_class.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class Q958CheckCompletenessOfABinaryTree {

    //Difficulty: medium
    //TAG: Facebook
    //TAG: tree
    //TAG: level order

    /**
     * 958. Check Completeness of a Binary Tree
     * Given a binary tree, determine if it is a complete binary tree.
     *
     * Definition of a complete binary tree from Wikipedia:
     * In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.
     *
     *
     *
     * Example 1:
     *
     *
     *
     * Input: [1,2,3,4,5,6]
     * Output: true
     * Explanation: Every level before the last is full (ie. levels with node-values {1} and {2, 3}), and all nodes in the last level ({4, 5, 6}) are as far left as possible.
     * Example 2:
     *
     *
     *
     * Input: [1,2,3,4,5,null,7]
     * Output: false
     * Explanation: The node with value 7 isn't as far left as possible.
     */

    /*
    Solution:

    level order traversal, when find left or right == null, set flag = true, when find any valid node but flag == true
    then return false
    finally return true

    Time: O(n)
    Space: O(n)
     */

    public boolean isCompleteTree(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean find = false;
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            if (poll.left != null) {
                if (find) return false;
                queue.offer(poll.left);
            } else find = true;
            if (poll.right != null) {
                if (find) return false;
                queue.offer(poll.right);
            } else find = true;
        }
        return true;
    }

}
