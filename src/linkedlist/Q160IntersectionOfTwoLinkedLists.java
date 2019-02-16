package linkedlist;

import public_class.ListNode;

import java.util.HashSet;
import java.util.Set;

public class Q160IntersectionOfTwoLinkedLists {

    //Difficulty: Easy
    //TAG: LinkedIn
    //TAG: LinkedList

    /**
     * 160. Intersection of Two Linked Lists
     * Write a program to find the node at which the intersection of two singly linked lists begins.
     *
     * For example, the following two linked lists:
     *
     *
     * begin to intersect at node c1.
     *
     *
     *
     * Example 1:
     *
     *
     * Input: intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
     * Output: Reference of the node with value = 8
     * Input Explanation: The intersected node's value is 8 (note that this must not be 0 if the two lists intersect). From the head of A, it reads as [4,1,8,4,5]. From the head of B, it reads as [5,0,1,8,4,5]. There are 2 nodes before the intersected node in A; There are 3 nodes before the intersected node in B.
     *
     *
     * Example 2:
     *
     *
     * Input: intersectVal = 2, listA = [0,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
     * Output: Reference of the node with value = 2
     * Input Explanation: The intersected node's value is 2 (note that this must not be 0 if the two lists intersect). From the head of A, it reads as [0,9,1,2,4]. From the head of B, it reads as [3,2,4]. There are 3 nodes before the intersected node in A; There are 1 node before the intersected node in B.
     *
     *
     * Example 3:
     *
     *
     * Input: intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
     * Output: null
     * Input Explanation: From the head of A, it reads as [2,6,4]. From the head of B, it reads as [1,5]. Since the two lists do not intersect, intersectVal must be 0, while skipA and skipB can be arbitrary values.
     * Explanation: The two lists do not intersect, so return null.
     *
     *
     * Notes:
     *
     * If the two linked lists have no intersection at all, return null.
     * The linked lists must retain their original structure after the function returns.
     * You may assume there are no cycles anywhere in the entire linked structure.
     * Your code should preferably run in O(n) time and use only O(1) memory.
     */

    /*
    Solution 1:
    Find the length different diff of headA and headB, move the longer node to diff distance,
    make sure headA and headB could finish at the same time, move both until met null or the same node

    Time: O(m + n)
    Space: O(1)
     */

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode temp1 = headA, temp2 = headB;
        int len1 = 0, len2 = 0;
        while (temp1 != null || temp2 != null) {
            if (temp1 != null) {
                len1++;
                temp1 = temp1.next;
            }
            if (temp2 != null) {
                len2++;
                temp2 = temp2.next;
            }
        }
        if (len1 > len2) {
            for (int i = 0; i < len1 - len2; i++) headA = headA.next;
        } else {
            for (int i = 0; i < len2 - len1; i++) headB = headB.next;
        }
        while (headA != null && headB != null && !headA.equals(headB)) {
            headA = headA.next;
            headB = headB.next;
        }
        return headA;
    }

    /*
    Solution 2:
    Use one set filter all nodes in both headA and headB, when any headA or headB met the set.contains(node)
    then the node is the first node met

    Time: O(max(m, n))
    Space: O(min(m, n))
     */

    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        Set<ListNode> set = new HashSet<>();
        while (headA != null || headB != null) {
            if (headA != null) {
                if (!set.add(headA)) return headA;
                headA = headA.next;
            }
            if (headB != null) {
                if (!set.add(headB)) return headB;
                headB = headB.next;
            }
        }
        return null;
    }

    /*
    Solution 3:
    not better, still
    Time: O(m + n)
    Space: O(1)
    but great way

    https://leetcode.com/problems/intersection-of-two-linked-lists/discuss/49785/Java-solution-without-knowing-the-difference-in-len!
     */

}
