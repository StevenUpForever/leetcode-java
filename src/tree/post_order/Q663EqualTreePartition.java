package tree.post_order;

import public_class.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class Q663EqualTreePartition {

    //Difficulty: Medium
    //TAG: Snap
    //TAG: tree
    //TAG: post order

    /**
     * 663. Equal Tree Partition
     * Given a binary tree with n nodes, your task is to check if it's possible to partition the tree to two trees which have the equal sum of values after removing exactly one edge on the original tree.
     *
     * Example 1:
     * Input:
     *     5
     *    / \
     *   10 10
     *     /  \
     *    2   3
     *
     * Output: True
     * Explanation:
     *     5
     *    /
     *   10
     *
     * Sum: 15
     *
     *    10
     *   /  \
     *  2    3
     *
     * Sum: 15
     * Example 2:
     * Input:
     *     1
     *    / \
     *   2  10
     *     /  \
     *    2   20
     *
     * Output: False
     * Explanation: You can't split the tree into two trees with equal sum after removing exactly one edge on the tree.
     * Note:
     * The range of tree node value is in the range of [-100000, 100000].
     * 1 <= n <= 10000
     */

    /*
    Solution:
    Post order traversal the tree, get the sum, and record current subtree sum with count

    at last check if there's subtree sum existed with subsum = sum / 2

    Time: O(n)
    Space: O(n)
     */

    public boolean checkEqualTree(TreeNode root) {
        Map<Integer, Integer> map = new HashMap<>();
        int sum = getSum(root, map);
        //If sum == 0 at least we should have two subtrees have this sum so we can split
        if(sum == 0) return map.getOrDefault(sum, 0) > 1;
        return sum % 2 == 0 && map.containsKey(sum/2);
    }

    private int getSum(TreeNode root, Map<Integer, Integer> map ){
        if(root == null) return 0;
        int cur = root.val + getSum(root.left, map) + getSum(root.right, map);
        map.put(cur, map.getOrDefault(cur,0) + 1);
        return cur;
    }

}
