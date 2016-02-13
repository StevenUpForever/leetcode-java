import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.ArrayList;
import java.util.List;

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

        if(str.length() == max*2){
            list.add(str);
            return;
        }
        if(open < max)
            backtrack(list, str + "(", open + 1, close, max);
        if(close < open)
            backtrack(list, str+")", open, close+1, max);
    }
}
