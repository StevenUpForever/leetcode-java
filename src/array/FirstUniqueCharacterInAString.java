package array;

public class FirstUniqueCharacterInAString {

    //TAG: Google
    //TAG: Microsoft
    //TAG: Amazon
    //TAG: array
    //Difficulty: Easy

    /**
     * 387. First Unique Character in a String
     * Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.

     Examples:

     s = "leetcode"
     return 0.

     s = "loveleetcode",
     return 2.
     Note: You may assume the string contain only lowercase letters.
     */

    /**
     * Solution:
     * due to all lowercase letters, loop once to count frequency of char
     * loop 2nd time, if any frequency is 1, it is the first one, just return
     * if loop not return anything, return -1, no unique letter
     *
     * Time: O(2n) = O(n)
     * Space: O(1)
     */

    public int firstUniqChar(String s) {
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < s.length(); i++) {
            if (count[s.charAt(i) - 'a'] == 1) return i;
        }
        return -1;
    }

}
