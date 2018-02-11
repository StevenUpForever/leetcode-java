package legacy_code;
import java.util.*;

/**
 * Created by ChengzhiJia on 3/25/17.
 */
public class Problem541To550 {
    /*
    545. Boundary of Binary Tree
    Given a binary tree, return the values of its boundary in anti-clockwise direction starting from root. Boundary includes left boundary, leaves, and right boundary in order without duplicate nodes.

Left boundary is defined as the path from root to the left-most node. Right boundary is defined as the path from root to the right-most node. If the root doesn't have left subtree or right subtree, then the root itself is left boundary or right boundary. Note this definition only applies to the input binary tree, and not applies to any subtrees.

The left-most node is defined as a leaf node you could reach when you always firstly travel to the left subtree if exists. If not, travel to the right subtree. Repeat until you reach a leaf node.

The right-most node is also defined by the same way with left and right exchanged.

Example 1
Input:
  1
   \
    2
   / \
  3   4

Ouput:
[1, 3, 4, 2]

Explanation:
The root doesn't have left subtree, so the root itself is left boundary.
The leaves are node 3 and 4.
The right boundary are node 1,2,4. Note the anti-clockwise direction means you should output reversed right boundary.
So order them in anti-clockwise without duplicates and we have [1,3,4,2].
Example 2
Input:
    ____1_____
   /          \
  2            3
 / \          /
4   5        6
   / \      / \
  7   8    9  10

Ouput:
[1,2,4,7,8,9,10,6,3]

Explanation:
The left boundary are node 1,2,4. (4 is the left-most node according to definition)
The leaves are node 4,7,8,9,10.
The right boundary are node 1,3,6,10. (10 is the right-most node).
So order them in anti-clockwise without duplicate nodes we have [1,2,4,7,8,9,10,6,3].
     */
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        List<Integer> list1 = new ArrayList<>();
        if (root == null) return list1;
        HashSet<TreeNode> set = new HashSet<>();
        list1.add(root.val);
        set.add(root);
        TreeNode temp = root.left;
        while (temp != null) {
            if (!set.contains(temp)) list1.add(temp.val);
            set.add(temp);
            if (temp.left == null) temp = temp.right;
            else temp = temp.left;
        }
        List<Integer> list2 = new ArrayList<>();
        addLeafNodes(root, list2, set);
        list1.addAll(list2);
        temp = root.right;
        List<Integer> list3 = new ArrayList<>();
        while (temp != null) {
            if (!set.contains(temp)) list3.add(temp.val);
            if (temp.right == null) temp = temp.left;
            else temp = temp.right;
        }
        for (int i = list3.size() - 1; i >= 0; i--) {
            list1.add(list3.get(i));
        }
        return list1;
    }

    private void addLeafNodes(TreeNode node, List<Integer> list, HashSet<TreeNode> set) {
        if (node.left == null && node.right == null) {
            if (!set.contains(node)) list.add(node.val);
            set.add(node);
            return;
        }
        if (node.left != null) addLeafNodes(node.left, list, set);
        if (node.right != null) addLeafNodes(node.right, list, set);
    }

    /*
    546. Remove Boxes
    Given several boxes with different colors represented by different positive numbers.
You may experience several rounds to remove boxes until there is no box left. Each time you can choose some continuous boxes with the same color (composed of k boxes, k >= 1), remove them and get k*k points.
Find the maximum points you can get.

Example 1:
Input:

[1, 3, 2, 2, 2, 3, 4, 3, 1]
Output:
23
Explanation:
[1, 3, 2, 2, 2, 3, 4, 3, 1]
----> [1, 3, 3, 4, 3, 1] (3*3=9 points)
----> [1, 3, 3, 3, 1] (1*1=1 points)
----> [1, 1] (3*3=9 points)
----> [] (2*2=4 points)
Note: The number of boxes n would not exceed 100.
     */
//    public int removeBoxes(int[] boxes) {
//
//    }

}
