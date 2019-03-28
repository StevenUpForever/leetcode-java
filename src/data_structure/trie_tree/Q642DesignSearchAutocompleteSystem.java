package data_structure.trie_tree;

import java.util.*;

public class Q642DesignSearchAutocompleteSystem {

    //Difficulty: Hard
    //TAG: data structure

    /**
     * 642. Design Search Autocomplete System
     * Design a search autocomplete system for a search engine. Users may input a sentence (at least one word and end with a special character '#'). For each character they type except '#', you need to return the top 3 historical hot sentences that have prefix the same as the part of sentence already typed. Here are the specific rules:
     *
     * The hot degree for a sentence is defined as the number of times a user typed the exactly same sentence before.
     * The returned top 3 hot sentences should be sorted by hot degree (The first is the hottest one). If several sentences have the same degree of hot, you need to use ASCII-code order (smaller one appears first).
     * If less than 3 hot sentences exist, then just return as many as you can.
     * When the input is a special character, it means the sentence ends, and in this case, you need to return an empty list.
     * Your job is to implement the following functions:
     *
     * The constructor function:
     *
     * AutocompleteSystem(String[] sentences, int[] times): This is the constructor. The input is historical data. Sentences is a string array consists of previously typed sentences. Times is the corresponding times a sentence has been typed. Your system should record these historical data.
     *
     * Now, the user wants to input a new sentence. The following function will provide the next character the user types:
     *
     * List<String> input(char c): The input c is the next character typed by the user. The character will only be lower-case letters ('a' to 'z'), blank space (' ') or a special character ('#'). Also, the previously typed sentence should be recorded in your system. The output will be the top 3 historical hot sentences that have prefix the same as the part of sentence already typed.
     *
     *
     * Example:
     * Operation: AutocompleteSystem(["i love you", "island","ironman", "i love leetcode"], [5,3,2,2])
     * The system have already tracked down the following sentences and their corresponding times:
     * "i love you" : 5 times
     * "island" : 3 times
     * "ironman" : 2 times
     * "i love leetcode" : 2 times
     * Now, the user begins another search:
     *
     * Operation: input('i')
     * Output: ["i love you", "island","i love leetcode"]
     * Explanation:
     * There are four sentences that have prefix "i". Among them, "ironman" and "i love leetcode" have same hot degree. Since ' ' has ASCII code 32 and 'r' has ASCII code 114, "i love leetcode" should be in front of "ironman". Also we only need to output top 3 hot sentences, so "ironman" will be ignored.
     *
     * Operation: input(' ')
     * Output: ["i love you","i love leetcode"]
     * Explanation:
     * There are only two sentences that have prefix "i ".
     *
     * Operation: input('a')
     * Output: []
     * Explanation:
     * There are no sentences that have prefix "i a".
     *
     * Operation: input('#')
     * Output: []
     * Explanation:
     * The user finished the input, the sentence "i a" should be saved as a historical sentence in system. And the following input will be counted as a new search.
     *
     *
     * Note:
     *
     * The input sentence will always start with a letter and end with '#', and only one blank space will exist between two words.
     * The number of complete sentences that to be searched won't exceed 100. The length of each sentence including those in the historical data won't exceed 100.
     * Please use double-quote instead of single-quote when you write test cases even for a character input.
     * Please remember to RESET your class variables declared in class AutocompleteSystem, as static/class variables are persisted across multiple test cases. Please see here for more details.
     */

    /*
    Solution:

    Due to continuously compare current string with string in dictionary, we need use trie tree
    For speed up find all same prefix strings, we add a hashMap in each trieNode, when add a new string to trie tree
    we add this string with counts in every map on its trie node path

    hold a currentNode for the position of current prefix in trie tree

    when find current hottest strings with same prefix by input():
    1. if input if #, add prefix to trie tree, reset prefix string, currentNode, return empty list
    2. if prefix not exist in trie tree, keep append input to prefix, but keep curNode == null, return empty list
    3. otherwise, use priorityQueue, add all strings in curNode.map, if count same, sort by smaller order, otherwise
    sort by larger count in curNode.map
    add top 3 or pq.size() elements in list and return
     */

    class AutocompleteSystem {

        private TrieNode root, curNode;
        private String prefix;

        public AutocompleteSystem(String[] sentences, int[] times) {
            root = new TrieNode(""); curNode = root;
            prefix = "";
            for (int i = 0; i < sentences.length; i++) {
                add(sentences[i], times[i]);
            }
        }

        public List<String> input(char c) {
            if (c == '#') {
                add(prefix, 1);
                curNode = root;
                prefix = "";
                return new ArrayList<>();
            }
            //Append prefix first anyway if not #
            prefix += c;
            int index = c == ' ' ? 0 : c - 'a' + 1;
            //be aware curNode is already null, then do not set
            if (curNode != null) curNode = curNode.children[index];
            if (curNode == null) return new ArrayList<>();
            PriorityQueue<String> pq = new PriorityQueue<>(new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    if (!curNode.map.get(o1).equals(curNode.map.get(o2))) {
                        return curNode.map.get(o2) - curNode.map.get(o1);
                    } else return o1.compareTo(o2);
                }
            });
            for (String key: curNode.map.keySet()) {
                pq.offer(key);
            }
            List<String> list = new ArrayList<>();
            int count = 0;
            while (!pq.isEmpty() && count < 3) {
                list.add(pq.poll());
                count++;
            }
            return list;
        }

        private void add(String str, int count) {
            TrieNode node = root;
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                int index = c == ' ' ? 0 : c - 'a' + 1;
                if (node.children[index] == null) {
                    node.children[index] = new TrieNode("" + c);
                }
                node = node.children[index];
                node.map.put(str, node.map.getOrDefault(str, 0) + count);
            }
        }
    }

    class TrieNode {
        String val;
        TrieNode[] children;
        Map<String, Integer> map;
        TrieNode(String val) {
            this.val = val;
            this.children = new TrieNode[27];
            this.map = new HashMap<>();
        }
    }

}
