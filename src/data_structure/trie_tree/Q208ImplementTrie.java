package data_structure.trie_tree;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Q208ImplementTrie {

    //TAG: Uber
    //TAG: Data structure
    //Difficulty: Medium

    /**
     * 208. Implement Trie (Prefix Tree)
     * Implement a trie with insert, search, and startsWith methods.

     Note:
     You may assume that all inputs are consist of lowercase letters a-z.
     */

    /**
     * Solution:
     * Use set and trieNode map to insert word, set to check word, map recursively check prefix
     */

    class Trie {

        /** Initialize your data structure here. */

        private Map<Character, TrieNode> map;
        private Set<String> set;
        public Trie() {
            map = new HashMap<>();
            set = new HashSet<>();
        }

        /** Inserts a word into the trie. */
        public void insert(String word) {
            if (word == null || word.length() == 0) return;
            set.add(word);
            char start = word.charAt(0);
            if (!map.containsKey(start)) map.put(start, new TrieNode(start));
            insertWord(word, map.get(start), 1);
        }

        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            return set.contains(word);
        }

        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {
            return searchWord(prefix, 0, map);
        }

        private void insertWord(String word, TrieNode node, int index) {
            if (index >= word.length()) return;
            char cur = word.charAt(index);
            if (!node.map.containsKey(cur)) node.map.put(cur, new TrieNode(cur));
            insertWord(word, node.map.get(cur), index + 1);
        }

        private boolean searchWord(String word, int index, Map<Character, TrieNode> map) {
            if (index >= word.length()) return true;
            char cur = word.charAt(index);
            if (!map.containsKey(cur)) return false;
            return searchWord(word, index + 1, map.get(cur).map);
        }
    }

    class TrieNode {
        char c;
        Map<Character, TrieNode> map;
        public TrieNode(char c) {
            this.c = c;
            map = new HashMap<>();
        }
    }

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */



}
