package Problem511To520;

import PublicClass.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Find_Largest_Value_in_Each_Tree_Row {

    /**
     * 515. Find Largest Value in Each Tree Row
     * You need to find the largest value in each row of a binary tree.

     Example:
     Input:

     1
     / \
     3   2
     / \   \
     5   3   9

     Output: [1, 3, 9]
     */

    /**
     * Solution:
     * Same as tree level order traversal, in each loop keep update a local min and after this loop add to final list
     *
     * Time: O(n)
     * Space: O(n) not balanced + O(n) not balanced list = O(n)
     */

    public List<Integer> largestValues(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int max = Integer.MIN_VALUE;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                max = Math.max(max, cur.val);
                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
            }
            list.add(max);
        }
        return list;
    }

}
