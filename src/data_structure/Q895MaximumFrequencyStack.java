package data_structure;

import java.util.*;

public class Q895MaximumFrequencyStack {

    //Difficulty: hard
    //TAG: Amazon
    //TAG: data structure

    /**
     * 895. Maximum Frequency Stack
     * Implement FreqStack, a class which simulates the operation of a stack-like data structure.
     *
     * FreqStack has two functions:
     *
     * push(int x), which pushes an integer x onto the stack.
     * pop(), which removes and returns the most frequent element in the stack.
     * If there is a tie for most frequent element, the element closest to the top of the stack is removed and returned.
     *
     *
     * Example 1:
     *
     * Input:
     * ["FreqStack","push","push","push","push","push","push","pop","pop","pop","pop"],
     * [[],[5],[7],[5],[7],[4],[5],[],[],[],[]]
     * Output: [null,null,null,null,null,null,null,5,7,5,4]
     * Explanation:
     * After making six .push operations, the stack is [5,7,5,7,4,5] from bottom to top.  Then:
     *
     * pop() -> returns 5, as 5 is the most frequent.
     * The stack becomes [5,7,5,7,4].
     *
     * pop() -> returns 7, as 5 and 7 is the most frequent, but 7 is closest to the top.
     * The stack becomes [5,7,5,4].
     *
     * pop() -> returns 5.
     * The stack becomes [5,7,4].
     *
     * pop() -> returns 4.
     * The stack becomes [5,7].
     *
     *
     * Note:
     *
     * Calls to FreqStack.push(int x) will be such that 0 <= x <= 10^9.
     * It is guaranteed that FreqStack.pop() won't be called if the stack has zero elements.
     * The total number of FreqStack.push calls will not exceed 10000 in a single test case.
     * The total number of FreqStack.pop calls will not exceed 10000 in a single test case.
     * The total number of FreqStack.push and FreqStack.pop calls will not exceed 150000 across all test cases.
     */

    /*
    Solution:

    Use List of stacks represent actual stack, with appearance of current x as index in list
    use map count appearance of x
     */

    class FreqStack {

        private List<Stack<Integer>> list;
        private Map<Integer, Integer> map;

        public FreqStack() {
            list = new ArrayList<>();
            map = new HashMap<>();
        }

        /*
        when push, add x in stack in list that index is the count of x in map
         */
        public void push(int x) {
            int val = map.getOrDefault(x, 0) + 1;
            map.put(x, val);
            if (val > list.size()) list.add(new Stack<>());
            list.get(val - 1).push(x);
        }

        /*
        when pop, choose top of the last stack as current pop, update appearance in map
        remove last stack is stack isEmpty, next pop will keep the last stack as the first valid stack
         */
        public int pop() {
            int i = list.size() - 1;
            if (i == -1) return -1;
            int pop = list.get(i).pop();
            if (list.get(i).isEmpty()) list.remove(i);
            map.put(pop, map.get(pop) - 1);
            return pop;
        }
    }

}
