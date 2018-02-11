package legacy_code.legacy_code_class;

import java.util.HashMap;

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

    /*
    209. Minimum Size Subarray Sum
    Given an array of n positive integers and a positive integer s, find the minimal length of a contiguous subarray of which the sum ≥ s. If there isn't one, return 0 instead.

For example, given the array [2,3,1,2,4,3] and s = 7,
the subarray [4,3] has the minimal length under the problem constraint.

click to show more practice.

More practice:
If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log n).
     */
    /*
    Approach: Sliding window problem, due to positive number, sum will increase anyway by index keep going, so slide left and right to compare each window which sum larger or equal to s
     */
    public int minSubArrayLen(int s, int[] nums) {
        if (nums.length == 0) return 0;
        int x = 0, y = 0, cur = 0, res = Integer.MAX_VALUE;
        while (y < nums.length) {
            cur += nums[y++];
            while (cur >= s) {
                res = Math.min(y - x , res);
                cur -= nums[x++];
            }
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }

}
