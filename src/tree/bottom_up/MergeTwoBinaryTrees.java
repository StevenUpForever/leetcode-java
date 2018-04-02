package tree.bottom_up;

import public_class.TreeNode;

public class MergeTwoBinaryTrees {

    //TAG: Amazon
    //TAG: tree
    //TAG: bottom up
    //Difficulty: Easy

    /**
     * 617. Merge Two Binary Trees
     * Given two binary trees and imagine that when you put one of them to cover the other, some nodes of the two trees are overlapped while the others are not.

     You need to merge them into a new binary tree. The merge rule is that if two nodes overlap, then sum node values up as the new value of the merged node. Otherwise, the NOT null node will be used as the node of new tree.

     Example 1:
     Input:
     Tree 1                     Tree 2
     1                         2
     / \                       / \
     3   2                     1   3
     /                           \   \
     5                             4   7
     Output:
     Merged tree:
     3
     / \
     4   5
     / \   \
     5   4   7
     Note: The merging process must start from the root nodes of both trees.
     */

    /**
     * Solution:
     * Always try to use recursion for tree problem,
     * at current level, check if t1 and t2 are valid,
     *      if all null, just return null, it's the end of the merged tree
     *      if any is valid, try add both values (if null, value is 0), build current node, and recursion
     *      to build left and right sub tree
     *      finally return this node as return value
     *
     * Time: O(max(m, n))
     * Space: O(max(m, n))
     */

    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return null;
        int val1 = t1 == null ? 0 : t1.val;
        int val2 = t2 == null ? 0 : t2.val;
        TreeNode node = new TreeNode(val1 + val2);
        node.left = mergeTrees(t1 == null ? null : t1.left, t2 == null ? null : t2.left);
        node.right = mergeTrees(t1 == null ? null : t1.right, t2 == null ? null : t2.right);
        return node;
    }

}
