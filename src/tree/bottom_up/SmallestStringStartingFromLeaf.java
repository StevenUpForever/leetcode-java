package tree.bottom_up;

import public_class.TreeNode;

public class SmallestStringStartingFromLeaf {

    //Difficulty: medium
    //TAG: Tree
    //TAG: bottom up

    /**
     * 988. Smallest String Starting From Leaf
     * Given the root of a binary tree, each node has a value from 0 to 25 representing the letters 'a' to 'z': a value of 0 represents 'a', a value of 1 represents 'b', and so on.
     *
     * Find the lexicographically smallest string that starts at a leaf of this tree and ends at the root.
     *
     * (As a reminder, any shorter prefix of a string is lexicographically smaller: for example, "ab" is lexicographically smaller than "aba".  A leaf of a node is a node that has no children.)
     *
     *
     *
     * Example 1:
     *
     *
     *
     * Input: [0,1,2,3,4,3,4]
     * Output: "dba"
     * Example 2:
     *
     *
     *
     * Input: [25,1,3,1,3,0,2]
     * Output: "adz"
     * Example 3:
     *
     *
     *
     * Input: [2,2,1,null,1,0,null,0]
     * Output: "abc"
     *
     *
     * Note:
     *
     * The number of nodes in the given tree will be between 1 and 1000.
     * Each node in the tree will have a value between 0 and 25.
     */

    /*
    Solution:
    post order traversal

    for each level, we know the smallest string from left and right

    1. if anyone is "" that not means it's the smallest, but no string from that side, return the other side
    2. otherwise compare two strings by characters, return smaller one + roo.val to char

    Time: O(n)
    Space: O(n)
     */

    public String smallestFromLeaf(TreeNode root) {
        if (root == null) return "";
        String left = smallestFromLeaf(root.left);
        String right = smallestFromLeaf(root.right);
        char newChar = (char)('a' + root.val);
        if (left.length() == 0) return right + newChar;
        if (right.length() == 0) return left + newChar;
        int i = 0, j = 0;
        while (i < left.length() && j < right.length()) {
            char a = left.charAt(i), b = right.charAt(j);
            if (a < b) return left + newChar;
            else if (a > b) return right + newChar;
            i++;
            j++;
        }
        //If left and right have same prefix, shorter one is smaller
        return i < left.length() ? right + newChar : left + newChar;
    }

}
