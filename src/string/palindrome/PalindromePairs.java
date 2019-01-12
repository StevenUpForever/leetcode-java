package string.palindrome;

import java.util.*;

public class PalindromePairs {

    //Difficulty: Hard
    //TAG: String
    //TAG: palindrome
    //TAG: Airbnb

    /**
     * 336. Palindrome Pairs
     * Given a list of unique words, find all pairs of distinct indices (i, j) in the given list,
     * so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.
     *
     * Example 1:
     *
     * Input: ["abcd","dcba","lls","s","sssll"]
     * Output: [[0,1],[1,0],[3,2],[2,4]]
     * Explanation: The palindromes are ["dcbaabcd","abcddcba","slls","llssssll"]
     * Example 2:
     *
     * Input: ["bat","tab","cat"]
     * Output: [[0,1],[1,0]]
     * Explanation: The palindromes are ["battab","tabbat"]
     */

    /*
    Solution 1:
    brute force solution, for loop strings, check concat string is palindrome

    Time: O(n^2 * l*2) = O(ln^2)
    Space: O(l)
     */

    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> res = new ArrayList<>();
        if (words == null) return res;
        for (int i = 0; i < words.length - 1; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if (isPalindrome(words[i], words[j])) {
                    res.add(Arrays.asList(i, j));
                }
                if (isPalindrome(words[j], words[i])) {
                    res.add(Arrays.asList(j, i));
                }
            }
        }
        return res;
    }

    private boolean isPalindrome(String a, String b) {
        String str = a + b;
        int l = 0, r = str.length() - 1;
        while (l < r && str.charAt(l) == str.charAt(r)) {
            l++;
            r--;
        }
        return l >= r;
    }

    /*
    Solution 2:
    loop each string, sub1 = substring(0, i), sub2 = substring(i, len - 1)
    if sub1 is palindrome itself, then if words contains sub2.reverse(), they can concat a palindrome
    reverse + sub1 + sub2
    same as when sub2 is palindrome itself

    Time: O(nl^2)
    Space: O(n)
     */

    public List<List<Integer>> palindromePairs2(String[] words) {
        List<List<Integer>> res = new ArrayList<>();
        if (words == null || words.length < 2) return res;
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) map.put(words[i], i);
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j<= words[i].length(); j++) {
                String str1 = words[i].substring(0, j);
                String str2 = words[i].substring(j);
                if (isPalindrome(str1)) {
                    String str2rvs = new StringBuilder(str2).reverse().toString();
                    //could concat str2.reverse + str1 + str2
                    if (map.containsKey(str2rvs) && map.get(str2rvs) != i) {
                        res.add(Arrays.asList(map.get(str2rvs), i));
                    }
                }
                if (isPalindrome(str2)) {
                    String str1rvs = new StringBuilder(str1).reverse().toString();
                    //could concat str1 + str2 + str1.reverse
                    if (map.containsKey(str1rvs) && map.get(str1rvs) != i && str2.length()!=0) {
                        res.add(Arrays.asList(i, map.get(str1rvs)));
                    }
                }
            }
        }
        return res;
    }

    private boolean isPalindrome(String str) {
        int l = 0, r = str.length() - 1;
        while (l < r && str.charAt(l) == str.charAt(r)) {
            l++;
            r--;
        }
        return l >= r;
    }

}
