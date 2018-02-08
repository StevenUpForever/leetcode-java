package Problem1To10;

import public_class.ListNode;

public class Add_Two_Numbers {

    /**
     * 2. Add Two Numbers
     * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
     You may assume the two numbers do not contain any leading zero, except the number 0 itself.

     Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
     Output: 7 -> 0 -> 8
     */

    /**
     * Solution:
     * The order of list represent the number from lower level to higher level, either choose add number one by one or parse lists to two numbers and add them are the same
     * add number by each bit
     * 1. keep a carry at the same time
     * 2. be aware of the post step of longer part of the longer list
     * 3. be aware of when addition is done and carray is still > 0 then need to add new listNode at the end of result list
     *
     * Time: O(m + n) m, n represent length of l1 and l2
     * Space: O(Math.max(m, n)) alloc a new list to save the addition result, which the length of result is the within [max(m, n), max(m, n) + 1]
     */

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //Avoid to modify original input
        ListNode temp1 = l1, temp2 = l2;
        ListNode res = new ListNode(0);
        ListNode temp = res;
        int carry = 0;
        while (temp1 != null || temp2 != null) {
            int num1 = temp1 != null ? temp1.val : 0;
            int num2 = temp2 != null ? temp2.val : 0;
            int curSum = num1 + num2 + carry;
            temp.next = new ListNode(curSum % 10);
            carry = curSum / 10;
            if (temp1 != null) temp1 = temp1.next;
            if (temp2 != null) temp2 = temp2.next;
            temp = temp.next;
        }
        if (carry > 0) temp.next = new ListNode(carry);
        return res.next;
    }

}
