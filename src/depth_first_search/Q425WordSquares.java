package depth_first_search;

import java.util.*;

public class Q425WordSquares {

    //TAG: Google
    //TAG: DFS
    //TAG: Trie
    //TAG map_set
    //Difficulty: Hard

    /**
     * 425. Word Squares
     * Given a set of words (without duplicates), find all word squares you can build from them.

     A sequence of words forms a valid word square if the kth row and column read the exact same string, where 0 ≤ k < max(numRows, numColumns).

     For example, the word sequence ["ball","area","lead","lady"] forms a word square because each word reads the same both horizontally and vertically.

     b a l l
     a r e a
     l e a d
     l a d y
     Note:
     There are at least 1 and at most 1000 words.
     All words will have the exact same length.
     Word length is at least 1 and at most 5.
     Each word contains only lowercase English alphabet a-z.
     Example 1:

     Input:
     ["area","lead","wall","lady","ball"]

     Output:
     [
     [ "wall",
     "area",
     "lead",
     "lady"
     ],
     [ "ball",
     "area",
     "lead",
     "lady"
     ]
     ]

     Explanation:
     The output consists of two word squares. The order of output does not matter (just the order of words in each word square matters).
     Example 2:

     Input:
     ["abat","baba","atan","atal"]

     Output:
     [
     [ "baba",
     "abat",
     "baba",
     "atan"
     ],
     [ "baba",
     "abat",
     "baba",
     "atal"
     ]
     ]

     Explanation:
     The output consists of two word squares. The order of output does not matter (just the order of words in each word square matters).
     */

    /**
     * Solution 1:
     * DFS, similar to N Queen problem, try add strings from 0 to words.length from each level, use set to filter
     * added string and pass check by diagonal
     *
     * Time: O(n!)
     * Space: O(n)
     */

    public List<List<String>> wordSquares(String[] words) {
        List<List<String>> res = new ArrayList<>();
        if (words == null || words.length == 0) return res;
        wordSquaresHelper(res, new ArrayList<>(), words);
        return res;
    }

    private void wordSquaresHelper(List<List<String>> res, List<String> list, String[] words) {
        if (list.size() == words[0].length()) {
            res.add(new ArrayList<>(list));
            return;
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < words.length; i++) {
            if (set.add(i) && passTheCheck(words[i], list)) {
                list.add(words[i]);
                wordSquaresHelper(res, list, words);
                list.remove(list.size() - 1);
                set.remove(i);
            }
        }
    }

    private boolean passTheCheck(String str, List<String> list) {
        int len = list.size();
        for (int i = 0; i < len; i++) {
            if (str.charAt(i) != list.get(i).charAt(len)) return false;
        }
        return true;
    }

    /**
     * Solution 2:
     * The slow part of Solution 2 is on each level, it will be slow to scan all words array if words are very large,
     * since when add "wall" next string should start with "a" when added "wall" "area" next string should start with
     * "le" etc.
     * So use HashMap (which need to store "w" "wa" "wal" "wall" as separate keys) or trie tree
     *
     * Time: O(mn) mn represent total length of words, by create map, if map is mn, then recursion is O(n) length of
     * words, total: O(mn + n) = O(mn)
     * Space: O(mn) map
     */

    public List<List<String>> wordSquares2(String[] words) {
        List<List<String>> res = new ArrayList<>();
        if (words == null || words.length == 0) return res;
        Map<String, List<String>> map = new HashMap<>();
        //Put all words for key "" since in recursion need to check key first, when list is empty "" key will be used
        map.put("", Arrays.asList(words));
        for (String str: words) {
            for (int i = 0; i < str.length(); i++) {
                String key = str.substring(0, i + 1);
                if (!map.containsKey(key)) map.put(key, new ArrayList<>());
                map.get(key).add(str);
            }
        }
        wordSquaresHelper2(res, new ArrayList<>(), map, words);
        return res;
    }

    private void wordSquaresHelper2(List<List<String>> res, List<String> list, Map<String, List<String>> map, String[] words) {
        if (list.size() == words[0].length()) {
            res.add(new ArrayList<>(list));
            return;
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            builder.append(list.get(i).charAt(list.size()));
        }
        String key = builder.toString();
        if (map.containsKey(key)) {
            List<String> values = map.get(key);
            for (int i = 0; i < values.size(); i++) {
                list.add(values.get(i));
                wordSquaresHelper2(res, list, map, words);
                list.remove(list.size() - 1);
            }
        }
    }

}
