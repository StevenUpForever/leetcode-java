package string.bit_map;

import java.util.Arrays;
import java.util.Comparator;

public class Q318MaximumProductOfWordLengths {

    //Difficulty: medium
    //TAG: Google
    //TAG: string

    /**
     * 318. Maximum Product of Word Lengths
     * Given a string array words, find the maximum value of length(word[i]) * length(word[j]) where the two words do not share common letters. You may assume that each word will contain only lower case letters. If no such two words exist, return 0.
     *
     * Example 1:
     *
     * Input: ["abcw","baz","foo","bar","xtfn","abcdef"]
     * Output: 16
     * Explanation: The two words can be "abcw", "xtfn".
     * Example 2:
     *
     * Input: ["a","ab","abc","d","cd","bcd","abcd"]
     * Output: 4
     * Explanation: The two words can be "ab", "cd".
     * Example 3:
     *
     * Input: ["a","aa","aaa","aaaa"]
     * Output: 0
     * Explanation: No such pair of words.
     */

    /*
    Solution 1:
    sort array by length from max to min, for for loop the array and find max product
    skip current loop if words[i].length() * words[j].length() < max, due to whether rest of loop share common chars
    or not will not over max

    Time: O(nlogn + n^2)
    Space: O(1)
     */

    public int maxProduct(String[] words) {
        Arrays.sort(words, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.length() - o1.length();
            }
        });
        int max = 0;
        for (int i = 0; i < words.length - 1; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if (words[i].length() * words[j].length() < max) break;
                if (!shardCommonChars(words[i], words[j])) {
                    max = words[i].length() * words[j].length();
                }
            }
        }
        return max;
    }

    private boolean shardCommonChars(String a, String b) {
        int[] chars = new int[26];
        for (int i = 0; i < a.length(); i++) chars[a.charAt(i) - 'a']++;
        for (int i = 0; i < b.length(); i++) {
            if (chars[b.charAt(i) - 'a'] > 0) return true;
        }
        return false;
    }

    /*
    Solution 2:
    bit map, make chars in a binary, then binary & binary will easily and quickly find if share common chars
    Time: O(nlogn)
    Space: O(n)
     */
    public int maxProduct2(String[] words) {
        int max = 0;

        Arrays.sort(words, new Comparator<String>(){
            public int compare(String a, String b){
                return b.length() - a.length();
            }
        });

        int[] masks = new int[words.length]; // alphabet masks

        for(int i = 0; i < masks.length; i++){
            for(char c: words[i].toCharArray()){
                masks[i] |= 1 << (c - 'a');
            }
        }

        for(int i = 0; i < masks.length; i++){
            if(words[i].length() * words[i].length() <= max) break; //prunning
            for(int j = i + 1; j < masks.length; j++){
                if((masks[i] & masks[j]) == 0){
                    max = Math.max(max, words[i].length() * words[j].length());
                    break; //prunning
                }
            }
        }

        return max;
    }

}
