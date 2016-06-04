package LeetCode;

import java.util.HashMap;
import java.util.Hashtable;

/**
 * Created by ChengzhiJia on 5/29/16.
 */
public class Problem141To150 {

    /*
    146. LRU Cache
    Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and set.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
set(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
     */

    class LRUCache {

        private class doubleListNode {
            int key;
            int value;
            doubleListNode pre;
            doubleListNode next;
            doubleListNode(int newKey, int newValue) {
                key = newKey;
                value = newValue;
            }
        }

        HashMap<Integer, doubleListNode> map = new HashMap<>();

        doubleListNode head, tail;

        int count, TempCapacity;

        public LRUCache(int capacity) {
            head = new doubleListNode(0, 0);
            tail = new doubleListNode(0, 0);
            head.pre = null;
            head.next = tail;
            tail.pre = head;
            tail.next = null;
            count = 0;
            TempCapacity = capacity;
        }

        public int get(int key) {
            doubleListNode result = map.get(key);
            if (result == null) return -1;
            deleteNode(result);
            insertNode(result);
            return result.value;
        }

        public void set(int key, int value) {
            doubleListNode node = map.get(key);
            if (node != null) {
                node.value = value;
                deleteNode(node);
                insertNode(node);
            } else {
                node = new doubleListNode(key, value);
                map.put(key, node);
                insertNode(node);
                count++;
                if (count > TempCapacity) {
                    map.remove(tail.pre.key);
                    deleteNode(tail.pre);
                    --count;
                }
            }
        }

        private void deleteNode(doubleListNode node) {
            node.pre.next = node.next;
            node.next.pre = node.pre;
        }

        private void insertNode(doubleListNode node) {
            node.pre = head;
            node.next = head.next;
            head.next.pre = node;
            head.next = node;
        }
    }

}
