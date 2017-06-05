package Problem101To110;

import java.util.*;
import PublicClass.TreeNode;

public class Binary_Tree_Zigzag_Level_Order_Traversal {

    /**
     * 103. Binary Tree Zigzag Level Order Traversal
     * Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).

     For example:
     Given binary tree [3,9,20,null,null,15,7],
     3
     / \
     9  20
     /  \
     15   7
     return its zigzag level order traversal as:
     [
     [3],
     [20,9],
     [15,7]
     ]
     */

    /**
     * Solution:
     * Similar as Binary tree level order traversal in Problem101To110, difference is every time the push and poll direction is not same, so we use a structure which could push/poll at two sides (deque)
     *      1. if the level index is even number
     *          poll from deque left
     *          push left -> right from deque right
     *      2. if the level index is odd number
     *          poll from right
     *          push right -> left from deque left
     *
     * Time: O(n)
     * Space: O(n)
     */

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Deque<TreeNode> deque = new LinkedList<>();
        deque.push(root);
        while (!deque.isEmpty()) {
            int size = deque.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                //res size is the same as current tree level index
                if (res.size()%2 == 0) {
                    TreeNode cur = deque.pollFirst();
                    list.add(cur.val);
                    if (cur.left != null) deque.offerLast(cur.left);
                    if (cur.right != null) deque.offerLast(cur.right);
                } else {
                    TreeNode cur = deque.pollLast();
                    list.add(cur.val);
                    if (cur.right != null) deque.offerFirst(cur.right);
                    if (cur.left != null) deque.offerFirst(cur.left);
                }
            }
            res.add(list);
        }
        return res;
    }

}
