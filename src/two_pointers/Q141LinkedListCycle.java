package two_pointers;

import public_class.ListNode;

import java.util.HashSet;
import java.util.Set;

public class Q141LinkedListCycle {

    //Difficulty: Easy
    //TAG: Apple
    //TAG: two pointers
    //TAG: Set

    /**
     * 141. Linked List Cycle
     * Given a linked list, determine if it has a cycle in it.
     *
     * To represent a cycle in the given linked list, we use an integer pos which represents the position (0-indexed) in the linked list where tail connects to. If pos is -1, then there is no cycle in the linked list.
     *
     *
     *
     * Example 1:
     *
     * Input: head = [3,2,0,-4], pos = 1
     * Output: true
     * Explanation: There is a cycle in the linked list, where tail connects to the second node.
     *
     *
     * Example 2:
     *
     * Input: head = [1,2], pos = 0
     * Output: true
     * Explanation: There is a cycle in the linked list, where tail connects to the first node.
     *
     *
     * Example 3:
     *
     * Input: head = [1], pos = -1
     * Output: false
     * Explanation: There is no cycle in the linked list.
     *
     *
     *
     *
     * Follow up:
     *
     * Can you solve it using O(1) (i.e. constant) memory?
     */

    /*
    Solution 1:
    two pointers, slow and fast pointer, slow move 1 step, fast move 2 steps each time, when fast could reach to end
    (fast == null) means no cycle, else if sometime slow == fast, means it's a cycle
     */

    public boolean hasCycle(ListNode head) {
        if (head == null) return false;
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null && slow != fast) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return fast != null && fast.next != null;
    }

    /*
    Solution 2:
    Use Set filter all nodes that visited, if the pointer could reach end node == null, then no cycle, otherwise
    when reach a node that contains in set, it's a cycle

    Time: O(n)
    Space: O(n)
     */

    public boolean hasCycle2(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        while (head != null) {
            if (set.add(head)) head = head.next;
            else return true;
        }
        return false;
    }

}
