package Problem101To110;

import java.util.*;
import PublicClass.TreeNode;

public class Binary_Tree_Level_Order_Traversal_II {

    /**
     * 107. Binary Tree Level Order Traversal II
     * Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).

     For example:
     Given binary tree [3,9,20,null,null,15,7],
     3
     / \
     9  20
     /  \
     15   7
     return its bottom-up level order traversal as:
     [
     [15,7],
     [9,20],
     [3]
     ]
     */

    /**
     * Solution 1:
     * same as Binary Tree level order traversal (Problem101To110)
     * Diff is insert the current list at index 0 (result list need be LinkedList not ArrayList)
     *
     * Time: O(n)
     * Space: O(n)
     */

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                list.add(cur.val);
                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
            }
            res.add(0, list);
        }
        return res;
    }

}
