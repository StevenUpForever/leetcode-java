package LeetCode;

/**
 * Created by ChengzhiJia on 6/18/16.
 */
public class Problem151To160 {

    /*
    155. Min Stack
    Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
getMin() -- Retrieve the minimum element in the stack.
Example:
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin();   --> Returns -3.
minStack.pop();
minStack.top();      --> Returns 0.
minStack.getMin();   --> Returns -2.
Subscribe to see which companies asked this question
     */
    public class MinStack {

        private class ListNode {
            int val;
            ListNode next;
            ListNode(int val) {
                this.val = val;
            }
        }

        ListNode start = new ListNode(0);
        /** initialize your data structure here. */
        public MinStack() {
        }

        public void push(int x) {
            ListNode newNode = new ListNode(x);
            newNode.next = start.next;
            start.next = newNode;
        }

        public void pop() {
            if (start.next != null) {
                ListNode next = start.next;
                start.next = next.next;
                next = null;
            }
        }

        public int top() {
            return start.next == null ? 0 : start.next.val;
        }

        public int getMin() {
            ListNode node = start.next;
            int minVal = node.val;
            while (node != null) {
                minVal = Math.min(minVal, node.val);
                node = node.next;
            }
            return minVal;
        }
    }

}
