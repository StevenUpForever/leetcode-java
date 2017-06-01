package Problem91To100;

import java.util.*;
import PublicClass.TreeNode;

public class Binary_Tree_Inorder_Traversal {

    /**
     * 94. Binary Tree Inorder Traversal
     * Given a binary tree, return the inorder traversal of its nodes' values.

     For example:
     Given binary tree [1,null,2,3],
     1
     \
     2
     /
     3
     return [1,3,2].

     Note: Recursive solution is trivial, could you do it iteratively?
     */

    /**
     * Solution 1: recursion
     *
     * Time: O(n)
     * Space: O(logn) recursion tree levels
     */

    List<Integer> list = new ArrayList<>();
    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) return list;
        inorderTraversal(root.left);
        list.add(root.val);
        inorderTraversal(root.right);
        return list;
    }

}
