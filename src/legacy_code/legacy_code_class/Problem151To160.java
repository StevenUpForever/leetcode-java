package legacy_code.legacy_code_class;

import java.util.ArrayList;

/**
 * Created by ChengzhiJia on 6/18/16.
 */
public class Problem151To160 {

    /*
    151. Reverse Words in a String
    Given an input string, reverse the string word by word.

For example,
Given s = "the sky is blue",
return "blue is sky the".

Update (2015-02-12):
For C programmers: Try to solve it in-place in O(1) space.
     */
    public String reverseWords(String s) {
        String[] words = s.trim().split(" +");
        for (int i = 0; i < words.length/2; i++) {
            String w = words[i];
            words[i] = words[words.length - i - 1];
            words[words.length - i - 1] = w;
        }
        return String.join(" ", words);
    }

    /*
    155. Min stack
    Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

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
Subscribe to see which companies asked this question
     */
    public class MinStack {

        ListNode start = new ListNode(0);
        ArrayList<ListNode> minArray = new ArrayList<>();
        /** initialize your data structure here. */
        public MinStack() {
        }

        public void push(int x) {
            ListNode newNode = new ListNode(x);
            newNode.next = start.next;
            start.next = newNode;
            if (minArray.isEmpty() || minArray.get(minArray.size() - 1).val >= newNode.val) minArray.add(newNode);
        }

        public void pop() {
            if (start.next != null) {
                ListNode next = start.next;
                start.next = next.next;
                if (!minArray.isEmpty() && minArray.get(minArray.size() - 1).val == next.val) minArray.remove(minArray.size() - 1);
                next = null;
            }
        }

        public int top() {
            return start.next == null ? 0 : start.next.val;
        }

        public int getMin() {
            if (minArray.size() > 0) return minArray.get(minArray.size() - 1).val;
            else return 0;
        }
    }

    /*
    157. Read N Characters Given Read4
    The API: int read4(char *buf) reads 4 characters at a time from a file.

The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.

By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.

Note:
The read function will only be called once for each test case.
     */
//    public int read(char[] buf, int n) {
//
//    }

    /*
    158. Read N Characters Given Read4 II - Call multiple times
    The API: int read4(char *buf) reads 4 characters at a time from a file.

The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.

By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.

Note:
The read function may be called multiple times.
     */
//    public int read(char[] buf, int n) {
//
//    }

}
