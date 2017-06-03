package Problem101To110;

import java.util.*;
import PublicClass.TreeNode;

public class Binary_Tree_Level_Order_Traversal {

    /**
     * 102. Binary Tree Level Order Traversal
     * Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

     For example:
     Given binary tree [3,9,20,null,null,15,7],
     3
     / \
     9  20
     /  \
     15   7
     return its level order traversal as:
     [
     [3],
     [9,20],
     [15,7]
     ]
     */

    /**
     * Solution:
     * Use a FIFO structure (queue) to push and pop numbers, the push order will be left -> right, then pop order is left -> right too
     * for every time, record a current length of current queue (which is the length of nodes on current level), add them to a list and begin next loop
     *
     * Time: O(n)
     * Space: O(n)
     */

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            //Keep a current size, which new sub node will push to queue, but cannot calculate into current list
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            //Poll all nodes (limited by fixed size) to the temp list, and push all valid sub nodes into queue for next loop
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                list.add(cur.val);
                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
            }
            res.add(list);
        }
        return res;
    }

}
