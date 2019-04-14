package tree.reconstruct;

import public_class.TreeNode;

public class Q1028RecoverATreeFromPreorderTraversal {

    //Difficulty: hard
    //TAG: Tree
    //TAG: reconstruct tree

    /**
     * 1028. Recover a Tree From Preorder Traversal
     * We run a preorder depth first search on the root of a binary tree.
     *
     * At each node in this traversal, we output D dashes (where D is the depth of this node), then we output the value of this node.  (If the depth of a node is D, the depth of its immediate child is D+1.  The depth of the root node is 0.)
     *
     * If a node has only one child, that child is guaranteed to be the left child.
     *
     * Given the output S of this traversal, recover the tree and return its root.
     *
     *
     *
     * Example 1:
     *
     *
     *
     * Input: "1-2--3--4-5--6--7"
     * Output: [1,2,5,3,4,6,7]
     * Example 2:
     *
     *
     *
     * Input: "1-2--3---4-5--6---7"
     * Output: [1,2,5,3,null,6,null,4,null,7]
     *
     *
     * Example 3:
     *
     *
     *
     * Input: "1-401--349---90--88"
     * Output: [1,401,null,349,88,90]
     *
     *
     * Note:
     *
     * The number of nodes in the original tree is between 1 and 1000.
     * Each node will have a value between 1 and 10^9.
     */

    /*
    Solution:

    build the tree by global iterate S by index and a level which current value should be

    Time: O(n)
    Space: O(n)
     */

    //index: global index iterate S, count: global level that current value should be
    private int index = 0, count = 0;
    public TreeNode recoverFromPreorder(String S) {
        return dfs(S, 0);
    }

    private TreeNode dfs(String S, int level) {
        if (index >= S.length() || count != level) return null;
        int num = 0;
        while (index < S.length() && Character.isDigit(S.charAt(index))) {
            num = num * 10 + (S.charAt(index++) - '0');
        }
        TreeNode node = new TreeNode(num);
        count = 0;
        while (index < S.length() && S.charAt(index) == '-') {
            index++;
            count++;
        }
        node.left = dfs(S, level + 1);
        node.right = dfs(S, level + 1);
        return node;
    }

}
