package tree.pre_order;

import public_class.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class Q437PathSumIII {

    //Difficulty: Easy
    //TAG: Uber
    //TAG: tree
    //TAG: Top down

    /**
     * 437. Path Sum III
     * You are given a binary tree in which each node contains an integer value.
     *
     * Find the number of paths that sum to a given value.
     *
     * The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).
     *
     * The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.
     *
     * Example:
     *
     * root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
     *
     *       10
     *      /  \
     *     5   -3
     *    / \    \
     *   3   2   11
     *  / \   \
     * 3  -2   1
     *
     * Return 3. The paths that sum to 8 are:
     *
     * 1.  5 -> 3
     * 2.  5 -> 2 -> 1
     * 3. -3 -> 11
     */

    /*
    Solution:
    use presum array and two sum, dfs the tree

    Time: O(n)
    Space: O(n)
     */

    private int count = 0;

    public int pathSum(TreeNode root, int sum) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        pathSumHelper(root, 0, sum, map);
        return count;
    }

    private void pathSumHelper(TreeNode root, int curSum, int target, Map<Integer, Integer> map) {
        if (root == null) return;
        curSum += root.val;
        count += map.getOrDefault(curSum - target, 0);
        map.put(curSum, map.getOrDefault(curSum, 0) + 1);
        pathSumHelper(root.left, curSum, target, map);
        pathSumHelper(root.right, curSum, target, map);
        //Delete current presum when go back to upper level
        map.put(curSum, map.getOrDefault(curSum, 1) - 1);
    }

}
