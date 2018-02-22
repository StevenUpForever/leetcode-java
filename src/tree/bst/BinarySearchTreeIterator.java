package tree.bst;

import public_class.TreeNode;

import java.util.Stack;

public class BinarySearchTreeIterator {

    //TAG: LinkedIn
    //TAG: tree
    //TAG: bst
    //Difficulty: Medium

    /**
     * 173. Binary Search Tree Iterator
     * Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.

     Calling next() will return the next smallest number in the BST.

     Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.

     Credits:
     Special thanks to @ts for adding this problem and creating all test cases.
     */

    /**
     * Solution:
     * It's a iteration solution of in-order traversal of BST, so try to push all left to stack
     * then keep popping from stack and try to push all left first right second order nodes from right node to stack
     *
     * Time: O(n)
     * Space: O(n)
     */

    public class BSTIterator {

        private Stack<TreeNode> stack;
        public BSTIterator(TreeNode root) {
            stack = new Stack<>();
            pushLeftNodes(root);
        }

        /** @return whether we have a next smallest number */
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        /** @return the next smallest number */
        public int next() {
            TreeNode pop = stack.pop();
            pushLeftNodes(pop.right);
            return pop.val;
        }

        private void pushLeftNodes(TreeNode node) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }
    }

/**
 * Your BSTIterator will be called like this:
 * BSTIterator i = new BSTIterator(root);
 * while (i.hasNext()) v[f()] = i.next();
 */

}
