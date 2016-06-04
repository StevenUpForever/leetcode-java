package LeetCode;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ChengzhiJia on 5/29/16.
 */
public class Problem131To140 {

    private class RandomListNode {
        int label;
        RandomListNode next, random;
        RandomListNode(int x) { this.label = x; }
    };
    /*
    138. Copy List with Random Pointer
    A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.

Return a deep copy of the list.
     */
    public RandomListNode copyRandomList(RandomListNode head) {
        HashMap<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();
        RandomListNode temp = head;
        while (temp != null) {
            map.put(temp, new RandomListNode(temp.label));
            temp = temp.next;
        }
        temp = head;
        while (temp != null) {
            map.get(temp).next = map.get(temp.next);
            map.get(temp).random = map.get(temp.random);
            temp = temp.next;
        }
        return map.get(head);
    }

    public RandomListNode copyRandomList2(RandomListNode head) {
        RandomListNode result = new RandomListNode(0);
        RandomListNode temp = result;
        RandomListNode tempHead = head;
        ArrayList<RandomListNode> tempList = new ArrayList<>();
        ArrayList<RandomListNode> tempList2 = new ArrayList<>();
        while (tempHead != null) {
            temp.next = new RandomListNode(tempHead.label);
            temp = temp.next;
            tempList.add(temp);
            tempList2.add(tempHead);
            tempHead = tempHead.next;
        }
        temp = result.next;
        tempHead = head;
        while (tempHead != null) {
            if (tempHead.random != null) temp.random = tempList.get(tempList2.indexOf(tempHead.random));
            temp = temp.next;
            tempHead = tempHead.next;
        }
        return result.next;
    }

}
