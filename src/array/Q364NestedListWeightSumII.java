package array;

import java.util.*;

public class Q364NestedListWeightSumII {

    //Difficulty: Medium
    //TAG: LinkedIn
    //TAG: array

    /**
     * 364. Nested List Weight Sum II
     * Given a nested list of integers, return the sum of all integers in the list weighted by their depth.

     Each element is either an integer, or a list -- whose elements may also be integers or other lists.

     Different from the previous question where weight is increasing from root to leaf, now the weight is defined from bottom up. i.e., the leaf level integers have weight 1, and the root level integers have the largest weight.

     Example 1:
     Given the list [[1,1],2,[1,1]], return 8. (four 1's at depth 1, one 2 at depth 2)

     Example 2:
     Given the list [1,[4,[6]]], return 17. (one 1 at depth 3, one 4 at depth 2, and one 6 at depth 1; 1*3 + 4*2 + 6*1 = 17)
     */

    // This is the interface that allows for creating nested lists.
    // You should not implement it, or speculate about its implementation
    public interface NestedInteger {
//        // Constructor initializes an empty nested list.
//        public NestedInteger();
//        // Constructor initializes a single integer.
//        public NestedInteger(int value);
        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger();
        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger();
        // Set this NestedInteger to hold a single integer.
        public void setInteger(int value);
        // Set this NestedInteger to hold a nested list and adds a nested integer to it.
        public void add(NestedInteger ni);
        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return null if this NestedInteger holds a single integer
        public List<NestedInteger> getList();
    }

    /*
     * Solution 1: inspired from legacy_code
     * Similar idea as binary tree level order traversal
     * as if res = 6 * 3 + 3 * 2 + 2, it also equals res = (6) level1 + (6 + 3) level2 + (6 + 3 + 2) level3
     * Add every appeared level sum to total sum, until last level sum add once, in this case, level is the same from bottom to up
     *
     * Time: O(n) visit every node once
     * Space: O(n) if some list contains all integers
     */

    public int depthSumInverseS1(List<NestedInteger> nestedList) {
        //LevelSum represent total levelSum of all current visited levels (each integer add once)
        int totalSum = 0, levelSum = 0;
        //nestedList itself is the queue as level order traversal
        while (!nestedList.isEmpty()) {
            //New list to store next level NestedInteger, and set to nestedList to represent go to next level
            List<NestedInteger> list = new ArrayList<>();
            for (NestedInteger integer: nestedList) {
                if (integer.isInteger()) levelSum += integer.getInteger();
                else list.addAll(integer.getList());
            }
            //Add all total current levelSums to totalSum to represent add multiple times (levels)
            totalSum += levelSum;
            //Set to next level list for next while loop
            nestedList = list;
        }
        return totalSum;
    }

    /**
     * Solution 2: easier to understand but more real time and space, same time/space complexity
     * Use a standard queue to represent level order traversal
     * Use a linkedList, insert every level sum to first, represent all sums level + 1, at last, the index of number represent the correct index, add sum1 * level1 + ... + sum_n * level_n
     *
     * Time: O(n) traversal + O(n) if every level contains only one integer = O(n)
     * Space: O(n) queue (all integer on same level) or LinkedList (all integers on diff levels)
     */

    public int depthSumInverseS2(List<NestedInteger> nestedList) {
        Queue<NestedInteger> queue = new LinkedList<>();
        queue.addAll(nestedList);
        List<Integer> indexList = new LinkedList<>();
        while (!queue.isEmpty()) {
            //Level sum is sum of integers of current level
            int size = queue.size(), levelSum = 0;
            for (int i = 0; i < size; i++) {
                NestedInteger cur = queue.poll();
                if (cur.isInteger()) levelSum += cur.getInteger();
                else queue.addAll(cur.getList());
            }
            indexList.add(0, levelSum);
        }
        int totalSum = 0;
        for (int i = 0; i < indexList.size(); i++) totalSum += indexList.get(i) * (i + 1);
        return totalSum;
    }

}
