package legacy_code.Problem101To110;

import java.util.*;
import public_class.TreeNode;

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
     * same as Binary Tree level order traversal (legacy_code.legacy_code_class.Problem101To110)
     * Diff is insert the current list at index 0 (result list need be LinkedList not ArrayList)
     *
     * Time: O(n)
     * Space: O(n)
     */

    public List<List<Integer>> levelOrderBottomS1(TreeNode root) {
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

    /**
     * Solution 2:
     * Similar as solution 2 in binary_tree_level_order_traversal, diff is every recursion level is the reversed list index in res, means level 0 is the last list, level 1 is the last second list
     *
     * Time: O(n)
     * Space: O(n) + O(n) = O(n)
     */

    public List<List<Integer>> levelOrderBottomS2(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        levelOrderBottomHelper(res, 0, root);
        return res;
    }

    private void levelOrderBottomHelper(List<List<Integer>> res, int level, TreeNode root) {
        if (root == null) return;
        //level and list index is symmetrical of the middle of res
        if (level >= res.size()) res.add(0, new ArrayList<>());
        res.get(res.size() - 1 - level).add(root.val);
        levelOrderBottomHelper(res, level + 1, root.left);
        levelOrderBottomHelper(res, level + 1, root.right);
    }

}
