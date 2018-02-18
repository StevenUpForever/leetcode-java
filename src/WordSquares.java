import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordSquares {

    //TAG: Google
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
     * Solution:
     *
     */

    public List<List<String>> wordSquares(String[] words) {
        List<List<String>> res = new ArrayList<>();
        if (words == null || words.length == 0) return res;
        wordSquaresHelper(res, new ArrayList<>(), words);
        return res;
    }

    private void wordSquaresHelper(List<List<String>> res, List<String> list, String[] words) {
        if (list.size() == words[0].length()) {
            res.add(list);
            return;
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < words.length; i++) {
            if (set.add(i) && passTheCheck(words[i], list)) {
                list.add(words[i]);
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

}
