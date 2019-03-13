package tree.post_order;

import public_class.TreeNode;

public class Q993CousinsInBinaryTree {

    //Difficulty: Easy
    //TAG: Tree
    //TAG: bottom up
    //TAG: BFS

    /**
     * 993. Cousins in Binary Tree
     * In a binary tree, the root node is at depth 0, and children of each depth k node are at depth k+1.
     *
     * Two nodes of a binary tree are cousins if they have the same depth, but have different parents.
     *
     * We are given the root of a binary tree with unique values, and the values x and y of two different nodes in the tree.
     *
     * Return true if and only if the nodes corresponding to the values x and y are cousins.
     *
     *
     *
     * Example 1:
     *
     *
     * Input: root = [1,2,3,4], x = 4, y = 3
     * Output: false
     * Example 2:
     *
     *
     * Input: root = [1,2,3,null,4,null,5], x = 5, y = 4
     * Output: true
     * Example 3:
     *
     *
     *
     * Input: root = [1,2,3,null,4], x = 2, y = 3
     * Output: false
     *
     *
     * Note:
     *
     * The number of nodes in the tree will be between 2 and 100.
     * Each node has a unique integer value from 1 to 100.
     */

    /*
    Solution:

    Mark different value guide what we should do:
    if -1 means, no x or y find from left or right tree or result is already false
    if left and right both > 0 means we find both from left and right tree
    check if they belongs to same node, if yes return -1 otherwise, we return 0 indicate result is true
    else when any value is 0, we just return 0, otherwise return num + 1 if num > 0 (calculating height) or keep -1
    finally only value 0 means true, num > 0 means we only find one x or y (one is in the subtree of another which will not be cousins)

    Time: O(n)
    Space: O(n)
     */

    public boolean isCousins(TreeNode root, int x, int y) {
        return isCousinsHelper(root, x, y) == 0;
    }

    private int isCousinsHelper(TreeNode root, int x, int y) {
        if (root == null) return -1;
        if (root.val == x || root.val == y) return 1;
        int left = isCousinsHelper(root.left, x, y);
        int right = isCousinsHelper(root.right, x, y);
        if (left > 0 && right > 0) {
            if (left == right) {
                return (root.left.val == x && root.right.val == y) ||
                        (root.right.val == x && root.left.val == y) ? -1 : 0;
            } else return -1;
        } else if (left >= 0) return left == 0 ? 0 : left + 1;
        else if (right >= 0) return right == 0 ? 0 : right + 1;
        else return -1;
    }

}
