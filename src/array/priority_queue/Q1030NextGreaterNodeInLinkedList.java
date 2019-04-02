package array.priority_queue;

import public_class.ListNode;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

public class Q1030NextGreaterNodeInLinkedList {

    //Difficulty: medium
    //TAG: priority queue

    /**
     * 1030. Next Greater Node In Linked List
     * We are given a linked list with head as the first node.  Let's number the nodes in the list: node_1, node_2, node_3, ... etc.
     *
     * Each node may have a next larger value: for node_i, next_larger(node_i) is the node_j.val such that j > i, node_j.val > node_i.val, and j is the smallest possible choice.  If such a j does not exist, the next larger value is 0.
     *
     * Return an array of integers answer, where answer[i] = next_larger(node_{i+1}).
     *
     * Note that in the example inputs (not outputs) below, arrays such as [2,1,5] represent the serialization of a linked list with a head node value of 2, second node value of 1, and third node value of 5.
     *
     *
     *
     * Example 1:
     *
     * Input: [2,1,5]
     * Output: [5,5,0]
     * Example 2:
     *
     * Input: [2,7,4,3,5]
     * Output: [7,0,5,5,0]
     * Example 3:
     *
     * Input: [1,7,5,1,9,2,5,1]
     * Output: [7,9,9,9,0,5,0,0]
     *
     *
     * Note:
     *
     * 1 <= node.val <= 10^9 for each node in the linked list.
     * The given list has length in the range [0, 10000].
     */

    /*
    Solution:

    This question is actually when loop list
    find all previous values that < cur and not set, set them to cur
    that is the first larger value
    We just need to know smaller values once, then priorityQueue is a good way

    Time: O(nlogn)
    Space: O(n)
     */

    public int[] nextLargerNodes(ListNode head) {
        List<Integer> list = new ArrayList<>();
        ListNode temp = head;
        while (temp != null) {
            list.add(temp.val);
            temp = temp.next;
        }
        int[] res = new int[list.size()];
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> list.get(o1) - list.get(o2));
        for (int i = 0; i < list.size(); i++) {
            while (!pq.isEmpty() && list.get(pq.peek()) < list.get(i)) {
                res[pq.poll()] = list.get(i);
            }
            pq.offer(i);
        }
        return res;
    }

    /*
    Solution 2:
    Similar but stack is enough, set indices that smaller than A[i] once and delete (by pop)

    Time: O(n)
    Space: O(n)
     */

    public int[] nextLargerNodes2(ListNode head) {
        ArrayList<Integer> A = new ArrayList<>();
        for (ListNode node = head; node != null; node = node.next)
            A.add(node.val);
        int[] res = new int[A.size()];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < A.size(); ++i) {
            while (!stack.isEmpty() && A.get(stack.peek()) < A.get(i))
                res[stack.pop()] = A.get(i);
            stack.push(i);
        }
        return res;
    }

}
