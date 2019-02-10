package data_structure;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class FlattenNestedListIterator {

    //TAG: Google
    //TAG: Facebook
    //TAG: Airbnb
    //TAG: data structure
    //Difficulty: medium

    /**
     * 341. Flatten Nested List Iterator
     * Given a nested list of integers, implement an iterator to flatten it.
     *
     * Each element is either an integer, or a list -- whose elements may also be integers or other lists.
     *
     * Example 1:
     * Given the list [[1,1],2,[1,1]],
     *
     * By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,1,2,1,1].
     *
     * Example 2:
     * Given the list [1,[4,[6]]],
     *
     * By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,4,6].
     */

    /*
     * Solution:
     * Use stack to save obj from input, stack only valid when it's integer
     * 1. when peek is integer, just return the integer and hasNext is true
     * 2. when is list, loop from end to start of list and add to stack, only nested value is integer will be true
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

    public class NestedIterator implements Iterator<Integer> {
        Stack<NestedInteger> stack = new Stack<>();
        public NestedIterator(List<NestedInteger> nestedList) {
            for(int i = nestedList.size() - 1; i >= 0; i--) {
                stack.push(nestedList.get(i));
            }
        }

        @Override
        public Integer next() {
            return stack.pop().getInteger();
        }

        @Override
        public boolean hasNext() {
            while(!stack.isEmpty()) {
                NestedInteger curr = stack.peek();
                if(curr.isInteger()) {
                    return true;
                }
                stack.pop();
                for(int i = curr.getList().size() - 1; i >= 0; i--) {
                    stack.push(curr.getList().get(i));
                }
            }
            return false;
        }
    }

}
