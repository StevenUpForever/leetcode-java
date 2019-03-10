package breadth_first_search;

import java.util.*;
import public_class.TreeNode;

public class Q102BinaryTreeLevelOrderTraversal {

    //TAG: LinkedIn
    //TAG: Tree
    //TAG: Breadth first search
    //Difficulty: Medium

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
     * Solution 1:
     * Use a FIFO structure (queue) to push and pop numbers, the push order will be left -> right, then pop order is left -> right too
     * for every time, record a current length of current queue (which is the length of nodes on current level), add them to a list and begin next loop
     *
     * Time: O(n)
     * Space: O(n)
     */

    public List<List<Integer>> levelOrderS1(TreeNode root) {
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

    /**
     * Solution 2:
     * recursion, think about we have a dynamic list which contains lists, and the index of list is the current recursion level
     * Base case: when current node is null, stop the recursion
     * recursion rule:
     *      recursion from left to right, for each node and current recursion index
     *          1. if the list is not existed, initialize one and add the current node
     *          2. otherwise the recursion level is the index of the list which the node should append to
     *
     * Time: O(n)
     * Space: O(n) recursion levels if not balanced + O(n) = O(n)
     */

    public List<List<Integer>> levelOrderS2(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        levelOrderHelper(res, root, 0);
        return res;
    }

    private void levelOrderHelper(List<List<Integer>> res, TreeNode root, int level) {
        if (root == null) return;
        //If this the first node of current level, add new list to res
        if (level >= res.size()) res.add(new ArrayList<>());
        res.get(level).add(root.val);
        levelOrderHelper(res, root.left, level + 1);
        levelOrderHelper(res, root.right, level + 1);
    }

}
