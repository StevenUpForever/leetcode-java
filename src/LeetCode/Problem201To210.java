package LeetCode;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ChengzhiJia on 6/5/16.
 */
public class Problem201To210 {

    /*
    206. Reverse Linked List
    Reverse a singly linked list.
     */
    public ListNode reverseList(ListNode head) {
        ListNode node = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = node;
            node = head;
            head = next;
        }
        return node;
    }

    /*
    208. Implement Trie (Prefix Tree)
    Implement a trie with insert, search, and startsWith methods.

Note:
You may assume that all inputs are consist of lowercase letters a-z.
     */
    class TrieNode {
        // Initialize your data structure here.
        public TrieNode() {

        }
    }

    public class Trie {
        private TrieNode root;
        HashMap<String, TrieNode> map = new HashMap<>();
        public Trie() {
            root = new TrieNode();
        }

        // Inserts a word into the trie.
        public void insert(String word) {
            map.put(word, new TrieNode());
        }

        // Returns if the word is in the trie.
        public boolean search(String word) {
            return map.get(word) != null;
        }

        // Returns if there is any word in the trie
        // that starts with the given prefix.
        public boolean startsWith(String prefix) {
            for (String str: map.keySet()) {
                if (str.length() >= prefix.length() && str.substring(0, prefix.length()).equals(prefix)) return true;
            }
            return false;
        }
    }

}
