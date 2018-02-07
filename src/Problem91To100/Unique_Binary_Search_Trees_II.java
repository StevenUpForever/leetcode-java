package Problem91To100;

import java.util.*;

import PublicClass.ListNode;
import PublicClass.TreeNode;

public class Unique_Binary_Search_Trees_II {

    /**
     * 95. Unique Binary Search Trees II
     * Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1...n.

     For example,
     Given n = 3, your program should return all 5 unique BST's shown below.

     1         3     3      2      1
     \       /     /      / \      \
     3     2     1      1   3      2
     /     /       \                 \
     2     1         2                 3
     */

    /**
     * Solution: recursion
     * Think about a high level recursion, not build a BST recursion, but build all possible BST recursion
     * 1. Have a left min and right max to limit the scope of the value current node could have
     * 2. recursion to get the left node list and right node list which left/right nodes need to fetch from these lists
     * 3. iterative all possible value within min...max
     *      iterative all nodes from left list and right list, append left and right node to current new alloc node
     * 4. return the final recursion list
     */

    public List<TreeNode> generateTrees(int n) {
        if (n == 0) return new ArrayList<>();
        return generateTreesHelper(1, n);
    }

    private List<TreeNode> generateTreesHelper(int min, int max) {
        //List to store possible nodes on current level
        List<TreeNode> list = new ArrayList<>();
        //If met null node, need to add null, otherwise when go back to pre level, find no nodes to append to current level node, will not go below for for loop, means will not create the new node, final result will be empty list
        if (min > max) {
            list.add(null);
        }
        for (int index = min; index <= max; index++) {
            //Update value scope for next recursion list
            List<TreeNode> leftList = generateTreesHelper(min, index - 1);
            List<TreeNode> rightList = generateTreesHelper(index + 1, max);
            for (TreeNode left: leftList) {
                for (TreeNode right: rightList) {
                    //Try create new node with current value index
                    TreeNode node = new TreeNode(index);
                    node.left = left;
                    node.right = right;
                    list.add(node);
                }
            }
        }
        return list;
    }

}
