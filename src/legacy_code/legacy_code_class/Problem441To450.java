package legacy_code.legacy_code_class;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ChengzhiJia on 3/14/17.
 */
public class Problem441To450 {
    /*
    445. Add Two Numbers II
    You are given two non-empty linked lists representing two non-negative integers. The most significant digit comes first and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Follow up:
What if you cannot modify the input lists? In other words, reversing the lists is not allowed.

Example:

Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 8 -> 0 -> 7
Show Company Tags
Show Tags
Show Similar Problems
     */

    /*
    Approach: first calculate the length and cal the len diff, which used to make the beginning of add
    then add number, and if over 10 add back to previous node stored in array
    finally return newHead or next depends on if result is 1 digits more than any of l1 and l2, be aware of due to need create new node then move next, so make sure l1 and l2 next are not null then create new node
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
        temp1 = l1;
        temp2 = l2;
        ListNode newHead = new ListNode(0);
        newHead.next = new ListNode(0);
        ListNode begin = newHead.next;
        List<ListNode> list = new ArrayList<>();
        list.add(newHead);
        if (len1 < len2) {
            for (int i = 0; i < len2 - len1; i++) {
                begin.val = temp2.val;
                list.add(begin);
                temp2 = temp2.next;
                if (temp2 != null) {
                    begin.next = new ListNode(0);
                    begin = begin.next;
                }
            }
        } else {
            for (int i = 0; i < len1 - len2; i++) {
                begin.val = temp1.val;
                list.add(begin);
                temp1 = temp1.next;
                if (temp1 != null) {
                    begin.next = new ListNode(0);
                    begin = begin.next;
                }
            }
        }
        while (temp1 != null && temp2 != null) {
            int res = temp1.val + temp2.val;
            if (res >= 10) {
                for (int i = list.size() - 1; i >= 0; i--) {
                    int val = list.get(i).val;
                    list.get(i).val = (val + 1)%10;
                    if (val + 1 < 10) {
                        break;
                    }
                }
            }
            begin.val = res % 10;
            list.add(begin);
            temp1 = temp1.next;
            temp2 = temp2.next;
            if (temp1 != null && temp2 != null) {
                begin.next = new ListNode(0);
                begin = begin.next;
            }
        }
        return newHead.val == 0 ? newHead.next : newHead;
    }
}
