package string.pattern;

import java.util.*;

public class FindAndReplacePattern {

    //Difficulty: Medium
    //TAG: Apple
    //TAG: String
    //TAG: pattern

    /**
     * 890. Find and Replace Pattern
     * You have a list of words and a pattern, and you want to know which words in words matches the pattern.
     *
     * A word matches the pattern if there exists a permutation of letters p so that after replacing every letter x in the pattern with p(x), we get the desired word.
     *
     * (Recall that a permutation of letters is a bijection from letters to letters: every letter maps to another letter, and no two letters map to the same letter.)
     *
     * Return a list of the words in words that match the given pattern.
     *
     * You may return the answer in any order.
     *
     *
     *
     * Example 1:
     *
     * Input: words = ["abc","deq","mee","aqq","dkd","ccc"], pattern = "abb"
     * Output: ["mee","aqq"]
     * Explanation: "mee" matches the pattern because there is a permutation {a -> m, b -> e, ...}.
     * "ccc" does not match the pattern because {a -> c, b -> c, ...} is not a permutation,
     * since a and b map to the same letter.
     *
     *
     * Note:
     *
     * 1 <= words.length <= 50
     * 1 <= pattern.length = words[i].length <= 20
     */

    /*
    Solution:
    corner case: if pattern and str length different, continue
    Pattern problem 3 cases:
    1. if there is pattern matching a -> b and str(i) is not b, continue
    2. if there is pattern matching * -> b (* means any but only str(i) has a pattern matching now, use set is enough)
    and current pattern char a doesn't has a mapping, continue, e.g. pattern: abb str: ccc
    3. if both doesn't has a mapping, put a -> in map

    Time: O(n)
    Space: O(n)
     */

    public List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> list = new ArrayList<>();
        if (words == null || pattern == null) return list;
        for (String str: words) {
            Map<Character, Character> patternToStr = new HashMap<>();
            Set<Character> strSet = new HashSet<>();
            boolean shouldAdd = true;
            for (int i = 0; i < str.length(); i++) {
                char compareChar = pattern.charAt(i);
                char curChar = str.charAt(i);
                if (!patternToStr.containsKey(compareChar)) {
                    if (strSet.add(curChar)) patternToStr.put(compareChar, curChar);
                    else {
                        shouldAdd = false;
                        break;
                    }
                } else if (patternToStr.get(compareChar) != curChar) {
                    shouldAdd = false;
                    break;
                }
            }
            if (shouldAdd) list.add(str);
        }
        return list;
    }

}
