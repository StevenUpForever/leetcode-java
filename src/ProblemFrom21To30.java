import com.sun.org.apache.bcel.internal.generic.LSUB;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.*;

/**
 * Created by ChengzhiJia on 16/2/12.
 */
/*
Problem 21 Merge Two Sorted Lists:
Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.
 */
public class ProblemFrom21To30 {
    /*
Problem 21 Merge Two Sorted Lists:
Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.
 */
    private class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode newList = new ListNode(0);
        ListNode result = newList;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                newList.next = new ListNode(l1.val);
                l1 = l1.next;
                newList = newList.next;
            }
            else {
                newList.next = new ListNode(l2.val);
                l2 = l2.next;
                newList = newList.next;
            }
        }
        if (l1 != null) {
            while (l1 != null) {
                newList.next = new ListNode(l1.val);
                l1 = l1.next;
                newList = newList.next;
            }
        }
        else if (l2 != null ) {
            while (l2 != null) {
                newList.next = new ListNode(l2.val);
                l2 = l2.next;
                newList = newList.next;
            }
        }
        return result.next;
    }

    /*
    Problem 22 Generate Parentheses:
    Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

For example, given n = 3, a solution set is:

"((()))", "(()())", "(())()", "()(())", "()()()"
     */
    public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<String>();
        backtrack(list, "", 0, 0, n);
        return list;
    }

    private void backtrack(List<String> list, String str, int open, int close, int max){
        System.out.println("backtrack(" + list + ", \"" + str + "\", " +
                open + ", " + close + ", " + max + ")");
        if(str.length() == max*2){
            list.add(str);
            return;
        }
        if(open < max)
            backtrack(list, str + "(", open + 1, close, max);
        if(close < open)
            backtrack(list, str+")", open, close+1, max);
    }

    /*
    23. Merge k Sorted Lists
    Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        PriorityQueue<ListNode> priQueue = new PriorityQueue<>(lists.length, new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });
        for (ListNode node : lists) {
            if (node != null) priQueue.add(node);
        }
        ListNode temp = new ListNode(0);
        ListNode result = temp;
        while (!priQueue.isEmpty()) {
            temp.next = priQueue.poll();
            temp = temp.next;
            if (temp.next != null) priQueue.add(temp.next);
        }
        return result.next;
    }

        /*
        24. Swap Nodes in Pairs
        Given a linked list, swap every two adjacent nodes and return its head.

For example,
Given 1->2->3->4, you should return the list as 2->1->4->3.

Your algorithm should use only constant space. You may not modify the values in the list, only nodes itself can be changed.
         */
        public ListNode swapPairs(ListNode head) {
            if (head == null) return null;
            ListNode result = new ListNode(0);
            result.next = head;
            ListNode temp = result;
            while (temp.next != null && temp.next.next != null) {
                ListNode one = temp.next;
                ListNode two = temp.next.next;
                one.next = two.next;
                temp.next = two;
                temp.next.next = one;
                temp = temp.next.next;
            }
            return result.next;
        }
    /*
    25. Reverse Nodes in k-Group
    Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.

You may not alter the values in the nodes, only nodes itself may be changed.

Only constant memory is allowed.

For example,
Given this linked list: 1->2->3->4->5

For k = 2, you should return: 2->1->4->3->5

For k = 3, you should return: 3->2->1->4->5
     */
    public ListNode reverseKGroup(ListNode head, int k) {
            ListNode curr = head;
            int count = 0;
            while (curr != null && count != k) { // find the k+1 node
                curr = curr.next;
                count++;
            }
            if (count == k) { // if k+1 node is found
                curr = reverseKGroup(curr, k); // reverse list with k+1 node as head
                // head - head-pointer to direct part,
                // curr - head-pointer to reversed part;
                while (count-- > 0) { // reverse current k-group:
                    ListNode tmp = head.next; // tmp - next head in direct part
                    head.next = curr; // preappending "direct" head to the reversed list
                    curr = head; // move head of reversed part to a new node
                    head = tmp; // move "direct" head to the next node in direct part
                }
                head = curr;
            }
            return head;
    }


}
