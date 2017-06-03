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
    public List<Integer> inorderTraversalS1(TreeNode root) {
        if (root == null) return list;
        inorderTraversalS1(root.left);
        list.add(root.val);
        inorderTraversalS1(root.right);
        return list;
    }

    /**
     * Solution 2: iterative
     * Use stack, keep a while loop until root == null && stack.isEmpty()
     * 1. push all left nodes
     * 2. when no more left nodes, add pop's val to list, and push next one right node
     * 3. repeat step 1 until exist the loop
     */

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            //Step 1, push all left nodes
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            //If no more left nodes, pop one, add the val, and try push one right node
            root = stack.pop();
            res.add(root.val);
            root = root.right;
        }
        return res;
    }

}
