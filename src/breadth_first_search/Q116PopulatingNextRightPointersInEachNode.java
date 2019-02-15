package breadth_first_search;

import java.util.LinkedList;
import java.util.Queue;

public class Q116PopulatingNextRightPointersInEachNode {

    //Difficulty: Medium
    //TAG: Apple
    //TAG: tree
    //TAG: bfs

    /**
     * 116. Populating Next Right Pointers in Each Node
     * Given a binary tree
     *
     * struct TreeLinkNode {
     *   TreeLinkNode *left;
     *   TreeLinkNode *right;
     *   TreeLinkNode *next;
     * }
     * Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
     *
     * Initially, all next pointers are set to NULL.
     *
     * Note:
     *
     * You may only use constant extra space.
     * Recursive approach is fine, implicit stack space does not count as extra space for this problem.
     * You may assume that it is a perfect binary tree (ie, all leaves are at the same level, and every parent has two children).
     * Example:
     *
     * Given the following perfect binary tree,
     *
     *      1
     *    /  \
     *   2    3
     *  / \  / \
     * 4  5  6  7
     * After calling your function, the tree should look like:
     *
     *      1 -> NULL
     *    /  \
     *   2 -> 3 -> NULL
     *  / \  / \
     * 4->5->6->7 -> NULL
     * Accepted
     * 222,803
     * Submissions
     * 610,408
     */

    /*
    Solution:
    Level order traversal, append next node to poll if current size > 1

    Time: O(n)
    Space: O(1)
     */

    public void connect(TreeLinkNode root) {
        if (root == null) return;
        Queue<TreeLinkNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                TreeLinkNode poll = queue.poll();
                if (size > 1) {
                    poll.next = queue.peek();
                }
                if (poll.left != null) queue.offer(poll.left);
                if (poll.right != null) queue.offer(poll.right);
                size--;
            }
        }
    }

    /*
    Solution 2:
    travel at each level by node.next, but record the first node, after level iteration, move root = root.left
    Same as level order traversal, but append left and right nodes, also don't need a linkedList

    Time: O(n)
    Space: O(1)
     */

    public void connect2(TreeLinkNode root) {
        while(root != null){
            TreeLinkNode cur = root;
            while(cur != null){
                if(cur.left != null) cur.left.next = cur.right;
                if(cur.right != null && cur.next != null) cur.right.next = cur.next.left;
                cur=cur.next;
            }
            root = root.left;
        }
    }

}

class TreeLinkNode {
    int val;
    TreeLinkNode left, right, next;
    TreeLinkNode(int x) { val = x; }
}
