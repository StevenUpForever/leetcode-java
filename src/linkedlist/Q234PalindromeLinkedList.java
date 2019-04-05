package linkedlist;

import public_class.ListNode;

import java.util.Stack;

public class Q234PalindromeLinkedList {

    //Difficulty: easy
    //TAG: LinkedList
    //TAG: recursive
    //TAG: stack

    /**
     * 234. Palindrome Linked List
     * Given a singly linked list, determine if it is a palindrome.
     *
     * Example 1:
     *
     * Input: 1->2
     * Output: false
     * Example 2:
     *
     * Input: 1->2->2->1
     * Output: true
     * Follow up:
     * Could you do it in O(n) time and O(1) space?
     */

    /*
    Solution 1:

    recursion, compare node reversely with and a global node in pos direction
    if value not match, setup a global boolean to false

    return global boolean

    Time: O(n)
    Space: O(n)
     */

    private ListNode temp;
    private boolean res;
    public boolean isPalindrome1(ListNode head) {
        temp = head;
        res = true;
        helper(head);
        return res;
    }

    private void helper(ListNode head) {
        if (head == null) return;
        helper(head.next);
        if (head.val != temp.val) {
            res = false;
        }
        temp = temp.next;
    }

    /*
    Solution 2:
    use stack save all nodes, and compare nodes in pos direction with stack.pop

    Time: O(n)
    Space: O(n)
     */

    public boolean isPalindrome2(ListNode head) {
        ListNode temp = head;
        Stack<Integer> stack = new Stack<>();
        while (temp != null) {
            stack.push(temp.val);
            temp = temp.next;
        }
        temp = head;
        while (temp != null && !stack.isEmpty() && stack.peek() == temp.val) {
            temp = temp.next;
            stack.pop();
        }
        return stack.isEmpty();
    }

    /*
    Solution 3:

    1. find mid of the list
    2. reverse the second part of list
    3. compare with first part and second part

    Time: O(n)
    Space: O(1)
     */

    public boolean isPalindrome3(ListNode head) {
        if (head == null) return true;
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode pre = null;
        while (slow != null) {
            ListNode next = slow.next;
            slow.next = pre;
            pre = slow;
            slow = next;
        }
        //at this time, the list like 1 -> 2 -> 3 <- 2 <- 1
        fast = head;
        while (fast != pre && fast.val == pre.val) {
            //stop if they are adjacent
            if (fast.next == pre) break;
            fast = fast.next;
            pre = pre.next;
        }
        //If they're adjacent, need check value again, e.g. list [1,2]
        return fast == pre || (fast.next == pre && fast.val == pre.val);
    }

}
