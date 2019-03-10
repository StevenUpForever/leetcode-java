package tree.reconstruct;

import public_class.TreeNode;

public class Q1008ConstructBinarySearchTreeFromPreorderTraversal {

    //Difficulty: medium
    //TAG: tree
    //TAG: bst

    /**
     * 1008. Construct Binary Search Tree from Preorder Traversal
     * Return the root node of a binary search tree that matches the given preorder traversal.
     *
     * (Recall that a binary search tree is a binary tree where for every node, any descendant of node.left has a value < node.val, and any descendant of node.right has a value > node.val.  Also recall that a preorder traversal displays the value of the node first, then traverses node.left, then traverses node.right.)
     *
     *
     *
     * Example 1:
     *
     * Input: [8,5,1,7,10,12]
     * Output: [8,5,10,1,7,null,12]
     *
     *
     *
     * Note:
     *
     * 1 <= preorder.length <= 100
     * The values of preorder are distinct.
     */

    /*
    Solution:
    build the tree by the pre-order value > min and < max
    if current value is valid, then we move index++
    we set left and right node of current node with no validation, due to if the sub node is invalid, then will return
    null and index will not + 1
    Time: O(n)
    Space: O(n)
     */

    private int index = 0;
    public TreeNode bstFromPreorder(int[] preorder) {
        return bstFromPreorderHelper(preorder, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    private TreeNode bstFromPreorderHelper(int[] pre, int min, int max) {
        if (index == pre.length || pre[index] <= min || pre[index] >= max) return null;
        int val = pre[index];
        TreeNode node = new TreeNode(val);
        //Only when create a valid node, we'll move the index
        index++;
        node.left = bstFromPreorderHelper(pre, min, val);
        node.right = bstFromPreorderHelper(pre, val, max);
        return node;
    }

}
