package tree.reconstruct;

import public_class.TreeNode;

public class Q106ConstructBinaryTreeFromInorderAndPostorderTraversal {

    //Difficulty: medium
    //TAG: tree
    //TAG: reconstruct

    /**
     * 106. Construct Binary Tree from Inorder and Postorder Traversal
     * Given inorder and postorder traversal of a tree, construct the binary tree.
     *
     * Note:
     * You may assume that duplicates do not exist in the tree.
     *
     * For example, given
     *
     * inorder = [9,3,15,20,7]
     * postorder = [9,15,7,20,3]
     * Return the following binary tree:
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     */

    /*
    Solution:
    for post order traversal, read from right to left, it's preorder start from right branch

    then build the tree recursively

    Time: O(n)
    Space: O(n)
     */

    private int index = 0;
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        index = postorder.length - 1;
        return build(inorder, postorder, 0, inorder.length - 1);
    }

    private TreeNode build(int[] inorder, int[] postorder, int left, int right) {
        if (left > right || index < 0) return null;
        int val = postorder[index--];
        TreeNode node = new TreeNode(val);
        int i = left;
        while (i <= right && inorder[i] != val) i++;
        if (i <= right) {
            node.right = build(inorder, postorder, i + 1, right);
            node.left = build(inorder, postorder, left, i - 1);
        }
        return node;
    }

}

