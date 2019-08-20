package tree.level_order;

import public_class.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class Q1161MaximumLevelSumOfABinaryTree {

    //Difficulty: medium
    //TAG: Tree
    //TAG: level order

    /**
     * 1161. Maximum Level Sum of a Binary Tree
     * Given the root of a binary tree, the level of its root is 1, the level of its children is 2, and so on.
     *
     * Return the smallest level X such that the sum of all the values of nodes at level X is maximal.
     *
     *
     *
     * Example 1:
     *
     *
     *
     * Input: [1,7,0,7,-8,null,null]
     * Output: 2
     * Explanation:
     * Level 1 sum = 1.
     * Level 2 sum = 7 + 0 = 7.
     * Level 3 sum = 7 + -8 = -1.
     * So we return the level with the maximum sum which is level 2.
     *
     *
     * Note:
     *
     * The number of nodes in the given tree is between 1 and 10^4.
     * -10^5 <= node.val <= 10^5
     */

    /*
    Solution:

    use level order traversal, then calculate sum of each level, only if find larger sum will update the global sum and
    level

    finally the level is the min level with max sum

    Time: O(n)
    Space: O(n)
     */

    public int maxLevelSum(TreeNode root) {
        if (root == null) return 0;
        int max = 0, resLevel = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int level = 1;
        while (!queue.isEmpty()) {
            int cur = 0, size = queue.size();
            while (size-- > 0) {
                TreeNode poll = queue.poll();
                cur += poll.val;
                if (poll.left != null) queue.offer(poll.left);
                if (poll.right != null) queue.offer(poll.right);
            }
            if (cur > max) {
                max = cur;
                resLevel = level;
            }
            level++;
        }
        return resLevel;
    }

}
