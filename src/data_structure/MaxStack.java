package data_structure;

import java.util.Stack;

public class MaxStack {

    //TAG: LinkedIn
    //TAG: data structure
    //Difficulty: Hard

    /**
     * 716. Max Stack
     * Design a max stack that supports push, pop, top, peekMax and popMax.

     push(x) -- Push element x onto stack.
     pop() -- Remove the element on top of the stack and return it.
     top() -- Get the element on the top.
     peekMax() -- Retrieve the maximum element in the stack.
     popMax() -- Retrieve the maximum element in the stack, and remove it. If you find more than one maximum elements, only remove the top-most one.
     Example 1:
     data_structure.MaxStack stack = new data_structure.MaxStack();
     stack.push(5);
     stack.push(1);
     stack.push(5);
     stack.top(); -> 5
     stack.popMax(); -> 5
     stack.top(); -> 1
     stack.peekMax(); -> 5
     stack.pop(); -> 1
     stack.top(); -> 5
     Note:
     -1e7 <= x <= 1e7
     Number of operations won't exceed 10000.
     The last four operations won't be called when stack is empty.
     */

    /**
     * Solution:
     * *** Similar to Min stack ***
     * Use two stacks, stack for regular operations, maxStack only when new number larger or equal than maxStack.peek
     * then also push to maxStack
     *
     */

    /** initialize your data structure here. */

    private Stack<Integer> stack, maxStack;

    public MaxStack() {
        stack = new Stack<>();
        maxStack = new Stack<>();
    }

    public void push(int x) {
        stack.push(x);
        if (maxStack.isEmpty() || x >= maxStack.peek()) maxStack.push(x);
    }

    public int pop() {
        Integer pop = stack.isEmpty() ? 0 : stack.pop();
        if (!maxStack.isEmpty() && maxStack.peek().equals(pop)) maxStack.pop();
        return pop;
    }

    public int top() {
        return stack.isEmpty() ? 0 : stack.peek();
    }

    public int peekMax() {
        return maxStack.isEmpty() ? 0 : maxStack.peek();
    }

    public int popMax() {
        /*
        Use another temp stack store all numbers in stack before maxStack.pop, then use local push (the maxStack.push())
         to push back, because if maxStack is empty, need to push new max to maxStack
         */
        Integer pop = maxStack.isEmpty() ? 0 : maxStack.pop();
        Stack<Integer> temp = new Stack<>();
        while (!stack.isEmpty() && !stack.peek().equals(pop)) temp.push(stack.pop());
        if (!stack.isEmpty()) stack.pop();
        while (!temp.isEmpty()) push(temp.pop());
        return pop;
    }

    /**
     * Your data_structure.MaxStack object will be instantiated and called as such:
     * data_structure.MaxStack obj = new data_structure.MaxStack();
     * obj.push(x);
     * int param_2 = obj.pop();
     * int param_3 = obj.top();
     * int param_4 = obj.peekMax();
     * int param_5 = obj.popMax();
     */
}
