package linkedlist.recursion;

import public_class.ListNode;
import java.util.Stack;

public class AddTwoNumbersII {

    //TAG: Microsoft
    //TAG: linked list
    //Difficulty: Medium

    /**
     * 445. Add Two Numbers II
     * You are given two non-empty linked lists representing two non-negative integers. The most significant digit comes first and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

     You may assume the two numbers do not contain any leading zero, except the number 0 itself.

     Follow up:
     What if you cannot modify the input lists? In other words, reversing the lists is not allowed.

     Example:

     Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
     Output: 7 -> 8 -> 0 -> 7

     */

    /**
    Solution:
     *** similar to reverse linked list in pair ***
     1. calculate the length of two lists and length diff, which used to make the beginning of add
     Due to need to add number reversely and need to consider about the carry, use recursion call back to do the add
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode temp1 = l1, temp2 = l2;
        int len1 = 0, len2 = 0;
        while (temp1 != null) {
            temp1 = temp1.next;
            len1++;
        }
        while (temp2 != null) {
            temp2 = temp2.next;
            len2++;
        }
        ListNode res = new ListNode(0);
        //Ensure fist arg is smaller list and second is the larger list
        res.next = len1 < len2 ? addTwoNumbersHelper(l1, l2, len2 - len1) :
                addTwoNumbersHelper(l2, l1, len1 - len2);
        //At last the first node maybe over 9, then forward the carry and return res (new head node)
        if (res.next.val > 9) {
            res.val++;
            res.next.val %= 10;
            return res;
        }
        //Otherwise return recursion head as head node
        return res.next;
    }

    //Similar to reverse linked list in pair, put next to res of smaller problem
    private ListNode addTwoNumbersHelper(ListNode small, ListNode large, int k) {
        if (large == null) return null;
        ListNode sum = k == 0 ? new ListNode(small.val + large.val) : new ListNode(large.val);
        ListNode next = k == 0 ? addTwoNumbersHelper(small.next, large.next, 0) :
                addTwoNumbersHelper(small, large.next, k - 1);
        if (next != null && next.val > 9) {
            //Due to max value will be 9 + 9 = 18, so, will only have 1 carry to consider about at current bit
            sum.val++;
            next.val %= 10;
        }
        sum.next = next;
        return sum;
    }

    /**
     * Solution 2: Use stack
     * use stack could reverse the list so that it added in the same order as Add Two Numbers I
     *
     * Time: O(m + n)
     * Space: O(m + n)
     */

    public ListNode addTwoNumbersS2(ListNode l1, ListNode l2) {
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();

        while(l1 != null) {
            s1.push(l1.val);
            l1 = l1.next;
        };
        while(l2 != null) {
            s2.push(l2.val);
            l2 = l2.next;
        }
        int sum = 0;
        ListNode list = new ListNode(0);
        while (!s1.empty() || !s2.empty()) {
            if (!s1.empty()) sum += s1.pop();
            if (!s2.empty()) sum += s2.pop();
            list.val = sum % 10;
            ListNode head = new ListNode(sum / 10);
            head.next = list;
            list = head;
            sum /= 10;
        }
        return list.val == 0 ? list.next : list;
    }

}
