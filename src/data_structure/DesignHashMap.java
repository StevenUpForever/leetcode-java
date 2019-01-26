package data_structure;

import java.util.HashSet;
import java.util.List;

public class DesignHashMap {

    //Difficulty: Easy
    //TAG: Apple
    //TAG: data structure

    /**
     * 706. Design HashMap
     * Design a HashMap without using any built-in hash table libraries.
     *
     * To be specific, your design should include these functions:
     *
     * put(key, value) : Insert a (key, value) pair into the HashMap. If the value already exists in the HashMap, update the value.
     * get(key): Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key.
     * remove(key) : Remove the mapping for the value key if this map contains the mapping for the key.
     *
     * Example:
     *
     * MyHashMap hashMap = new MyHashMap();
     * hashMap.put(1, 1);
     * hashMap.put(2, 2);
     * hashMap.get(1);            // returns 1
     * hashMap.get(3);            // returns -1 (not found)
     * hashMap.put(2, 1);          // update the existing value
     * hashMap.get(2);            // returns 1
     * hashMap.remove(2);          // remove the mapping for 2
     * hashMap.get(2);            // returns -1 (not found)
     *
     * Note:
     *
     * All keys and values will be in the range of [0, 1000000].
     * The number of operations will be in the range of [1, 10000].
     * Please do not use the built-in HashMap library.
     */

    /*
    Solution:
    Use int index = Integer.hashCode(key) % nodes.length; as hash function
     */

    class MyHashMap {

        private ListNode[] nodes;

        /** Initialize your data structure here. */
        public MyHashMap() {
            nodes = new ListNode[10000];
        }

        /** value will always be non-negative. */
        public void put(int key, int value) {
            int index = Integer.hashCode(key) % nodes.length;
            if (nodes[index] == null) nodes[index] = new ListNode(key, value);
            else {
                ListNode node = nodes[index];
                while (node != null && node.next != null) {
                    if (node.key == key) {
                        node.val = value;
                        return;
                    }
                    node = node.next;
                }
                if (node.key == key) node.val = value;
                else node.next = new ListNode(key, value);
            }
        }

        /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
        public int get(int key) {
            int index = Integer.hashCode(key) % nodes.length;
            ListNode node = nodes[index];
            while (node != null) {
                if (node.key == key) return node.val;
                node = node.next;
            }
            return -1;
        }

        /** Removes the mapping of the specified value key if this map contains a mapping for the key */
        public void remove(int key) {
            int index = Integer.hashCode(key) % nodes.length;
            ListNode pre = null, node = nodes[index];
            while (node != null) {
                if (node.key == key) {
                    if (pre == null) nodes[index] = node.next;
                    else {
                        pre.next = node.next;
                    }
                }
                pre = node;
                node = node.next;
            }
        }
    }

    class ListNode {
        int key;
        int val;
        ListNode next;
        ListNode(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

}
