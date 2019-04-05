package string;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Q819MostCommonWord {

    //Difficulty: easy
    //TAG: Amazon
    //TAG: string
    //TAG: map

    /**
     * 819. Most Common Word
     * Given a paragraph and a list of banned words, return the most frequent word that is not in the list of banned words.  It is guaranteed there is at least one word that isn't banned, and that the answer is unique.
     *
     * Words in the list of banned words are given in lowercase, and free of punctuation.  Words in the paragraph are not case sensitive.  The answer is in lowercase.
     *
     *
     *
     * Example:
     *
     * Input:
     * paragraph = "Bob hit a ball, the hit BALL flew far after it was hit."
     * banned = ["hit"]
     * Output: "ball"
     * Explanation:
     * "hit" occurs 3 times, but it is a banned word.
     * "ball" occurs twice (and no other word does), so it is the most frequent non-banned word in the paragraph.
     * Note that words in the paragraph are not case sensitive,
     * that punctuation is ignored (even if adjacent to words, such as "ball,"),
     * and that "hit" isn't the answer even though it occurs more because it is banned.
     *
     *
     * Note:
     *
     * 1 <= paragraph.length <= 1000.
     * 0 <= banned.length <= 100.
     * 1 <= banned[i].length <= 10.
     * The answer is unique, and written in lowercase (even if its occurrences in paragraph may have uppercase symbols, and even if it is a proper noun.)
     * paragraph only consists of letters, spaces, or the punctuation symbols !?',;.
     * There are no hyphens or hyphenated words.
     * Words only consist of letters, never apostrophes or other punctuation symbols.
     */

    /*
    Solution:

    1. convert banned to set for fast search
    2. count words not in banned set in map, keep update max count and related word

    return final word

    Time: O(m + n)
    Space: O(m + n)
     */

    public String mostCommonWord(String paragraph, String[] banned) {
        Set<String> set = new HashSet<>();
        for (String str: banned) set.add(str);
        paragraph = paragraph.toLowerCase();
        Map<String, Integer> map = new HashMap<>();
        int start = -1, count = 0;
        String res = "";
        for (int i = 0; i < paragraph.length(); i++) {
            char c = paragraph.charAt(i);
            if (isLetter(c)) {
                if (start == -1) start = i;
                if (i == paragraph.length() - 1 || !isLetter(paragraph.charAt(i + 1))) {
                    String word = paragraph.substring(start, i + 1);
                    if (!set.contains(word)) {
                        map.put(word, map.getOrDefault(word, 0) + 1);
                        if (count < map.get(word)) {
                            count = map.get(word);
                            res = word;
                        }
                    }
                    start = -1;
                }
            }
        }
        return res;
    }

    private boolean isLetter(char c) {
        return c - 'a' >= 0 && c - 'a' < 26;
    }

}
