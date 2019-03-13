package tree.post_order;

import public_class.TreeNode;

public class SecondMinimumNodeInABinaryTree {

    //TAG: LinkedIn
    //TAG: tree
    //TAG: top down
    //TAG: bottom up
    //Difficulty: Easy

    /**
     * 671. Second Minimum Node In a Binary Tree
     * Given a non-empty special binary tree consisting of nodes with the non-negative value, where each node in this tree has exactly two or zero sub-node. If the node has two sub-nodes, then this node's value is the smaller value among its two sub-nodes.

     Given such a binary tree, you need to output the second minimum value in the set made of all the nodes' value in the whole tree.

     If no such second minimum value exists, output -1 instead.

     Example 1:
     Input:
     2
     / \
     2   5
     / \
     5   7

     Output: 5
     Explanation: The smallest value is 2, the second smallest value is 5.
     Example 2:
     Input:
     2
     / \
     2   2

     Output: -1
     Explanation: The smallest value is 2, but there isn't any second smallest value.
     */

    /**
     * Solution:
     * Top down, DFS, like pre-order binary tree, keep track min diff and update global min
     * Bottom up, similar to LCA, if root == null return -1, when track back, check left value
     * right value, if any is not -1 return min(left, right), otherwise return non -1 value or -1 if all are -1
     *
     * Time: O(n)
     * Space: O(n)
     */

    int res = -1, diff = Integer.MAX_VALUE;
    public int findSecondMinimumValue(TreeNode root) {
        if (root == null) return -1;
        int min = root.val;
        findSecondMinimumValueHelper(root, min);
        return res;
    }

    public void findSecondMinimumValueHelper(TreeNode root, int min) {
        if (root == null) return;
        if (root.val - min < diff && root.val > min) {
            res = root.val;
            diff = root.val - min;
        }
        findSecondMinimumValueHelper(root.left, min);
        findSecondMinimumValueHelper(root.right, min);
    }

    public int findSecondMinimumValueHelper2(TreeNode root, int min) {
        if (root == null) return -1;
        int left = findSecondMinimumValueHelper2(root.left, min);
        int right = findSecondMinimumValueHelper2(root.right, min);
        if (left != -1 && right != -1) return Math.min(left, right);
        return left == -1 ? right : left;
    }

}
