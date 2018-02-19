package depth_first_search;

import java.util.*;

public class Nested_List_Weight_Sum {

    //TAG: LinkedIn
    //TAG: Queue
    //TAG: DFS
    //Difficulty: Easy

    /**
     * 339. Nested List Weight Sum
     * Given a nested list of integers, return the sum of all integers in the list weighted by their depth.

     Each element is either an integer, or a list -- whose elements may also be integers or other lists.

     Example 1:
     Given the list [[1,1],2,[1,1]], return 10. (four 1's at depth 2, one 2 at depth 1)

     Example 2:
     Given the list [1,[4,[6]]], return 27. (one 1 at depth 1, one 4 at depth 2, and one 6 at depth 3; 1 + 4*2 + 6*3 = 27)
     */

     // This is the interface that allows for creating nested lists.
     // You should not implement it, or speculate about its implementation
     public interface NestedInteger {
         // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger();
        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger();
        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return null if this NestedInteger holds a single integer
        public List<NestedInteger> getList();
    }

    /**
     * Solution 1: recursion
     * Base case: if current nestedInteger is integer, add num * weight to res
     * recursion rule: current nestedInteger is not integer, it's a list, for loop elements in the list, recursion to next check helper method and increase weight by 1
     *
     * Time: O(n) n represent total integers in nestedList
     * Space: O(n) n represent if 1 integer nested by n lists
     */

    private int res = 0;
    public int depthSumS1(List<NestedInteger> nestedList) {
        for (NestedInteger integer: nestedList)
            depthSumHelper(integer, 1);
        return res;
    }

    private void depthSumHelper(NestedInteger list, int weight) {
        if (list.isInteger()) res += list.getInteger() * weight;
        else {
            for (NestedInteger integer: list.getList())
                depthSumHelper(integer, weight + 1);
        }
    }

    /**
     * Solution 2: iterative
     * Similar as binary_tree_level_order_traversal(legacy_code.legacy_code_class.Problem101To110), which current weight is the level in binary tree
     * when find the list, append to the end of the queue
     * when finish current process of all origin data, weight++
     *
     * Time: O(n)
     * Space: O(n) if finally all numbers in the most nested list, and appended to the queue
     */

    public int depthSumS2(List<NestedInteger> nestedList) {
        Queue<NestedInteger> queue = new LinkedList<>(nestedList);
        int res = 0, weight = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                NestedInteger cur = queue.poll();
                if (cur.isInteger()) res += cur.getInteger() * weight;
                else queue.addAll(cur.getList());
            }
            weight++;
        }
        return res;
    }

}
