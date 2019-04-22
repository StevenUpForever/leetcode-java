package data_structure.trie_tree;

import java.util.LinkedList;
import java.util.Queue;

public class Q1032StreamOfCharacters {

    //Difficulty: hard
    //TAG: data structure
    //TAG: trie

    /**
     * 1032. Stream of Characters
     * Implement the StreamChecker class as follows:
     *
     * StreamChecker(words): Constructor, init the data structure with the given words.
     * query(letter): returns true if and only if for some k >= 1, the last k characters queried (in order from oldest to newest, including this letter just queried) spell one of the words in the given list.
     *
     *
     * Example:
     *
     * StreamChecker streamChecker = new StreamChecker(["cd","f","kl"]); // init the dictionary.
     * streamChecker.query('a');          // return false
     * streamChecker.query('b');          // return false
     * streamChecker.query('c');          // return false
     * streamChecker.query('d');          // return true, because 'cd' is in the wordlist
     * streamChecker.query('e');          // return false
     * streamChecker.query('f');          // return true, because 'f' is in the wordlist
     * streamChecker.query('g');          // return false
     * streamChecker.query('h');          // return false
     * streamChecker.query('i');          // return false
     * streamChecker.query('j');          // return false
     * streamChecker.query('k');          // return false
     * streamChecker.query('l');          // return true, because 'kl' is in the wordlist
     *
     *
     * Note:
     *
     * 1 <= words.length <= 2000
     * 1 <= words[i].length <= 2000
     * Words will only consist of lowercase English letters.
     * Queries will only consist of lowercase English letters.
     * The number of queries is at most 40000.
     */

    /*
    Solution:

    Trie tree

    every query add root as we find from start, then loop current size of queue of all nodes, that could go to next node
    we can find to next node, and if isWord return true
     */

    class StreamChecker {

        private TrieNode root;
        private Queue<TrieNode> queue;

        public StreamChecker(String[] words) {
            root = new TrieNode();
            for (String str: words) {
                TrieNode cur = root;
                for (int i = 0; i < str.length(); i++) {
                    int index = str.charAt(i) - 'a';
                    if (cur.children[index] == null) cur.children[index] = new TrieNode();
                    cur = cur.children[index];
                }
                cur.isWord = true;
            }
            queue = new LinkedList<>();
        }

        public boolean query(char letter) {
            int index = letter - 'a';
            boolean res = false;
            TrieNode cur = root;
            queue.offer(cur);
            int size = queue.size();
            while (size-- > 0) {
                TrieNode node = queue.poll();
                if (node.children[index] != null) {
                    node = node.children[index];
                    if (node.isWord) res = true;
                    queue.offer(node);
                }
            }
            return res;
        }
    }

    class TrieNode {
        boolean isWord;
        TrieNode[] children;
        TrieNode() {
            isWord = false;
            children = new TrieNode[26];
        }
    }

}
