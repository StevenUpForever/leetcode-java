package data_structure;

import java.util.Stack;

public class Q232ImplementQueueUsingStacks {

    //Difficulty: Easy
    //TAG: Apple
    //TAG: data structure

    /**
     * 232. Implement Queue using Stacks
     * Implement the following operations of a queue using stacks.
     *
     * push(x) -- Push element x to the back of queue.
     * pop() -- Removes the element from in front of queue.
     * peek() -- Get the front element.
     * empty() -- Return whether the queue is empty.
     * Example:
     *
     * MyQueue queue = new MyQueue();
     *
     * queue.push(1);
     * queue.push(2);
     * queue.peek();  // returns 1
     * queue.pop();   // returns 1
     * queue.empty(); // returns false
     * Notes:
     *
     * You must use only standard operations of a stack -- which means only push to top, peek/pop from top, size, and is empty operations are valid.
     * Depending on your language, stack may not be supported natively. You may simulate a stack by using a list or deque (double-ended queue), as long as you use only standard operations of a stack.
     * You may assume that all operations are valid (for example, no pop or peek operations will be called on an empty queue).
     */

    /*
    Solution:
    Use two stacks, stack1 represent push stack, stack2 represent the queue.poll() exit stack

    only when stack.isEmpty, push all from stack1 to stack2,
    in case that current top of the queue (means stack2.peak()) should not be covered by new pushed elements from stack1
     */

    class MyQueue {

        private Stack<Integer> stack1;
        private Stack<Integer> stack2;

        /** Initialize your data structure here. */
        public MyQueue() {
            this.stack1 = new Stack<>();
            this.stack2 = new Stack<>();
        }

        /** Push element x to the back of queue. */
        public void push(int x) {
            stack1.push(x);
        }

        /** Removes the element from in front of queue and returns that element. */
        public int pop() {
            if (stack2.isEmpty()) pushAllFromStack1ToStack2();
            return stack2.isEmpty() ? -1 : stack2.pop();
        }

        /** Get the front element. */
        public int peek() {
            if (stack2.isEmpty()) pushAllFromStack1ToStack2();
            return stack2.isEmpty() ? -1 : stack2.peek();
        }

        /** Returns whether the queue is empty. */
        public boolean empty() {
            return stack1.isEmpty() && stack2.isEmpty();
        }

        private void pushAllFromStack1ToStack2() {
            while (!stack1.isEmpty()) stack2.push(stack1.pop());
        }

    }

}
