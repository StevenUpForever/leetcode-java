package tree.bottom_up;

import public_class.TreeNode;

public class HouseRobberIII {

    //TAG: Uber
    //TAG: Tree
    //TAG: bottom up
    //Difficulty: Medium

    /**
     * 337. House Robber III
     * The thief has found himself a new place for his thievery again. There is only one entrance to this area, called the "root." Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that "all houses in this place forms a binary tree". It will automatically contact the police if two directly-linked houses were broken into on the same night.

     Determine the maximum amount of money the thief can rob tonight without alerting the police.

     Example 1:
     3
     / \
     2   3
     \   \
     3   1
     Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
     Example 2:
     3
     / \
     4   5
     / \   \
     1   3   1
     Maximum amount of money the thief can rob = 4 + 5 = 9.
     Credits:
     Special thanks to @dietpepsi for adding this problem and creating all test cases.
     */

    /**
     * Solution:
     * Similar to Find max from any node to any node
     * The differences are
     *  1. have two situation when report to previous node:
     *      one is max left + max right, the other is max left + right + root, we don't the pre is the best node in final
     *      solution, so even we don't have left and right value added, but it may not the best to add current root
     *  2. when report to upper node, need report left + right, not left OR right, which means it's not path from two nodes
     *  but may many nodes
     *
     *  Time: O(n)
     *  Space: O(n)
     */
    public int rob(TreeNode root) {
        TwoValNode res = robHelper(root);
        return Math.max(res.valWithoutRoot, res.valWithRoot);
    }

    private TwoValNode robHelper(TreeNode root) {
        if (root == null) return new TwoValNode(0, 0);
        TwoValNode left = robHelper(root.left);
        TwoValNode right = robHelper(root.right);
        int valWithoutRoot = Math.max(left.valWithoutRoot, left.valWithRoot) +
                Math.max(right.valWithoutRoot, right.valWithRoot);
        //We can only add left val and right val without left/right themselves when want to add current root
        int valWithRoot = root.val + left.valWithoutRoot + right.valWithoutRoot;
        return new TwoValNode(valWithoutRoot, valWithRoot);
    }

    class TwoValNode {
        int valWithoutRoot;
        int valWithRoot;
        public TwoValNode(int valWithoutRoot, int valWithRoot) {
            this.valWithoutRoot = valWithoutRoot;
            this.valWithRoot = valWithRoot;
        }
    }

}
