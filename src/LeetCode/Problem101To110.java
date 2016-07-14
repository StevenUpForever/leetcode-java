package LeetCode;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by ChengzhiJia on 6/10/16.
 */
public class Problem101To110 {

    /*
    102. Binary Tree Level Order Traversal
    Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

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
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        addNewNode(result, root, 0);
        return result;
    }

    private void addNewNode(List<List<Integer>> result, TreeNode node, int currentHeight) {
        if (node == null) return;
        if (currentHeight >= result.size()) result.add(new ArrayList<Integer>());
        result.get(currentHeight).add(node.val);
        addNewNode(result, node.left, currentHeight + 1);
        addNewNode(result, node.right, currentHeight + 1);
    }

    /*
    108. Convert Sorted Array to Binary Search Tree
    Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
     */

    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums.length == 0) return null;
        return createBST(0, nums.length - 1, nums);
    }

    private TreeNode createBST(int start, int end, int[] nums) {
        if (start <= end) {
            int middle = (start + end)/2;
            TreeNode node = new TreeNode(nums[middle]);
            node.left = createBST(start, middle - 1, nums);
            node.right = createBST(middle + 1, end, nums);
            return node;
        } else return null;
    }

}
