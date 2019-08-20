package string.frequency;

import java.util.Arrays;

public class Q1160FindWordsThatCanBeFormedByCharacters {

    //Difficulty: Easy
    //TAG: String

    /**
     * 1160. Find Words That Can Be Formed by Characters
     * You are given an array of strings words and a string chars.
     *
     * A string is good if it can be formed by characters from chars (each character can only be used once).
     *
     * Return the sum of lengths of all good strings in words.
     *
     *
     *
     * Example 1:
     *
     * Input: words = ["cat","bt","hat","tree"], chars = "atach"
     * Output: 6
     * Explanation:
     * The strings that can be formed are "cat" and "hat" so the answer is 3 + 3 = 6.
     * Example 2:
     *
     * Input: words = ["hello","world","leetcode"], chars = "welldonehoneyr"
     * Output: 10
     * Explanation:
     * The strings that can be formed are "hello" and "world" so the answer is 5 + 5 = 10.
     *
     *
     * Note:
     *
     * 1 <= words.length <= 1000
     * 1 <= words[i].length, chars.length <= 100
     * All strings contain lowercase English letters only.
     */

    /*
    Solution:

    Make chars as frequency array, then compare with each word, the one all within these chars are valid and add
    length to total, finally return total

    Time: O(mn + n)
    Space: O(n)
     */

    public int countCharacters(String[] words, String chars) {
        int sum = 0;
        int[] match = new int[26];
        for (int i = 0; i < chars.length(); i++) {
            char c = chars.charAt(i);
            match[c - 'a']++;
        }
        for (String word: words) {
            int[] copy = Arrays.copyOf(match, match.length);
            int wordIndex = 0;
            while (wordIndex < word.length()) {
                int arrayIndex = word.charAt(wordIndex) - 'a';
                if (copy[arrayIndex] <= 0) break;
                else {
                    copy[arrayIndex]--;
                    wordIndex++;
                }
            }
            if (wordIndex == word.length()) sum += wordIndex;
        }
        return sum;
    }

}
