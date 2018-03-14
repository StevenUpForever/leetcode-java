package tree.bottom_up;

import public_class.TreeNode;

import java.util.*;

public class BinaryTreeVerticalOrderTraversal {

    //TAG: Google
    //TAG: Facebook
    //TAG: Tree
    //TAG: Up down
    //Difficulty: Medium

    /**
     * 314. Binary Tree Vertical Order Traversal
     * Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by column).

     If two nodes are in the same row and column, the order should be from left to right.

     Examples:

     Given binary tree [3,9,20,null,null,15,7],
     3
     /\
     /  \
     9  20
     /\
     /  \
     15   7
     return its vertical order traversal as:
     [
     [9],
     [3,15],
     [20],
     [7]
     ]
     Given binary tree [3,9,8,4,0,1,7],
     3
     /\
     /  \
     9   8
     /\  /\
     /  \/  \
     4  01   7
     return its vertical order traversal as:
     [
     [4],
     [9],
     [3,0,1],
     [8],
     [7]
     ]
     Given binary tree [3,9,8,4,0,1,7,null,null,null,2,5] (0's right child is 2 and 1's left child is 5),
     3
     /\
     /  \
     9   8
     /\  /\
     /  \/  \
     4  01   7
     /\
     /  \
     5   2
     return its vertical order traversal as:
     [
     [4],
     [9,5],
     [3,0,1],
     [8,2],
     [7]
     ]
     */

    /**
     * Solution 1:
     * BFS, don't need record level, due to level from low to high, just record index
     *
     * Time: O(n)
     * Space: O(1)
     */

    public List<List<Integer>> verticalOrder1(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        HashMap<Integer, List<TreeNodeCell>> map = new HashMap<>();
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        verticalOrderHelper(map, queue, new TreeNodeCell(root, 0, 0));
        while (!queue.isEmpty()) {
            List<TreeNodeCell> list = map.get(queue.poll());
            Collections.sort(list, new Comparator<TreeNodeCell>() {
                @Override
                public int compare(TreeNodeCell o1, TreeNodeCell o2) {
                    return o1.level - o2.level;
                }
            });
            List<Integer> newList = new ArrayList<>();
            for (TreeNodeCell cell: list) {
                newList.add(cell.node.val);
            }
            res.add(newList);
        }
        return res;
    }

    private void verticalOrderHelper(HashMap<Integer, List<TreeNodeCell>> map, PriorityQueue<Integer> queue,
                                     TreeNodeCell root) {
        if (root.node == null) return;
        if (map.get(root.index) == null) {
            List<TreeNodeCell> list = new ArrayList<>();
            list.add(root);
            map.put(root.index, list);
            queue.offer(root.index);
        } else {
            map.get(root.index).add(root);
        }
        verticalOrderHelper(map, queue, new TreeNodeCell(root.node.left, root.level + 1, root.index - 1));
        verticalOrderHelper(map, queue, new TreeNodeCell(root.node.right, root.level + 1, root.index + 1));
    }

    private class TreeNodeCell {
        TreeNode node;
        int level;
        int index;
        TreeNodeCell(TreeNode node, int level, int index) {
            this.node = node;
            this.level = level;
            this.index = index;
        }
    }

    /**
     * Solution 2:
     * DFS
     * change index, add number to related index array, due to not know the root index in array, tag root index == 0
     * when find left index--, even neg numbers, keep min value
     * when find right index++, keep max number
     * due to index are continuous numbers, loop from min to max, add lists to final res and return
     *
     * *** solution is not complete, due to need put right subtree node prior than left subtree node at the same index,
     * but when right subtree node is higher than the other one, need to record level either to sort each index array ***
     *
     * Time: O(n)
     * Space: O(1)
     */

    public List<List<Integer>> verticalOrder2(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        Map<Integer, List<Integer>> map = new HashMap<>();
        int[] min = new int[]{0}, max = new int[]{0};
        verticalOrderHelper(map, root, 0, min, max);
        for (int i = min[0]; i <= max[0]; i++) {
            res.add(map.get(i));
        }
        return res;
    }

    private void verticalOrderHelper(Map<Integer, List<Integer>> map, TreeNode root, int index, int[] min, int[] max) {
        if (root == null) return;
        if (!map.containsKey(index)) map.put(index, new ArrayList<>());
        map.get(index).add(root.val);
        min[0] = Math.min(min[0], index);
        max[0] = Math.max(max[0], index);
        verticalOrderHelper(map, root.left, index - 1, min, max);
        verticalOrderHelper(map, root.right, index + 1, min, max);
    }


}
