package depth_first_search;

import java.util.ArrayList;
import java.util.List;

public class Q212WordSearchII {

    //Difficulty: hard
    //TAG: Airbnb
    //TAG: dfs
    //TAG: trie tree

    /**
     * 212. Word Search II
     * Given a 2D board and a list of words from the dictionary, find all words in the board.
     *
     * Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.
     *
     * Example:
     *
     * Input:
     * words = ["oath","pea","eat","rain"] and board =
     * [
     *   ['o','a','a','n'],
     *   ['e','t','a','e'],
     *   ['i','h','k','r'],
     *   ['i','f','l','v']
     * ]
     *
     * Output: ["eat","oath"]
     * Note:
     * You may assume that all inputs are consist of lowercase letters a-z.
     */

    /*
    Solution:
    Build words into trie tree, the trie tree saved time and space loop the word repeatedly

    Then loop each cell in matrix, for each cell, try to iterative from the root of trie tree
    find if we could find a valid word at last
     */

    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        TrieNode root = buildTrieTree(words);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs (board, i, j, root, res);
            }
        }
        return res;
    }

    public void dfs(char[][] board, int i, int j, TrieNode p, List<String> res) {
        char c = board[i][j];
        if (c == '#' || p.next[c - 'a'] == null) return;
        p = p.next[c - 'a'];
        if (p.word != null) {
            res.add(p.word);
            //prevent add duplicated words
            p.word = null;
        }
        //set value to '#' temporarily represent visited
        board[i][j] = '#';
        if (i > 0) dfs(board, i - 1, j ,p, res);
        if (j > 0) dfs(board, i, j - 1, p, res);
        if (i < board.length - 1) dfs(board, i + 1, j, p, res);
        if (j < board[0].length - 1) dfs(board, i, j + 1, p, res);
        //reset to previous value
        board[i][j] = c;
    }

    private TrieNode buildTrieTree(String[] words) {
        TrieNode root = new TrieNode();
        for (String word: words) {
            TrieNode cur = root;
            for (char c: word.toCharArray()) {
                int index = c - 'a';
                /*
                only when node is not existed, but next node, otherwise if build next node anyway,
                it will reset the subtree by the new node
                 */
                if (cur.next[index] == null) cur.next[index] = new TrieNode();
                cur = cur.next[index];
            }
            cur.word = word;
        }
        return root;
    }

    class TrieNode {
        TrieNode[] next;
        String word;
        TrieNode() {
            next = new TrieNode[26];
        }
    }

}
