package stack;

import java.util.Stack;

public class MinStack {

    //TAG: Uber
    //TAG: Stack
    //Difficulty: Easy

    /**
     * 155. Min Stack
     * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

     push(x) -- Push element x onto stack.
     pop() -- Removes the element on top of the stack.
     top() -- Get the top element.
     getMin() -- Retrieve the minimum element in the stack.
     Example:
     stack.MinStack minStack = new stack.MinStack();
     minStack.push(-2);
     minStack.push(0);
     minStack.push(-3);
     minStack.getMin();   --> Returns -3.
     minStack.pop();
     minStack.top();      --> Returns 0.
     minStack.getMin();   --> Returns -2.
     */

    /**
     * Solution:
     * Use two stacks, one stack with regular push and pop, the other stack, when current push value is smaller than
     * stack2.peek(), push, when stack1 pop and it's the value of stack2.peek() too, pop from stack2, so that stack2
     * will keep have the min value of current stack1
     *
     * Time: O(n)
     * Space: O(n) extra stack2
     */

    private Stack<Integer> stack1, stack2;

    /** initialize your data structure here. */
    public MinStack() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    public void push(int x) {
        stack1.push(x);
        //Be aware that if x == stack2.peek() need to push to stack2, same min value need to push
        if (stack2.isEmpty() || stack2.peek() >= x)
            stack2.push(x);
    }

    public void pop() {
        if (!stack1.isEmpty()) {
            Integer pop = stack1.pop();
            if (pop.equals(stack2.peek())) stack2.pop();
        }
    }

    public int top() {
        return stack1.isEmpty() ? 0 : stack1.peek();
    }

    public int getMin() {
        return stack2.isEmpty() ? 0 : stack2.peek();
    }

/**
 * Your stack.MinStack object will be instantiated and called as such:
 * stack.MinStack obj = new stack.MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */


}
