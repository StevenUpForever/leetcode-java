package map_set;

public class RemoveDuplicateLetters {

    //TAG: Google
    //TAG: String
    //TAG: map_set
    //Difficulty: Hard

    /**
     * 316. Remove Duplicate Letters
     * Given a string which contains only lowercase letters, remove duplicate letters so that every letter appear once and only once. You must make sure your result is the smallest in lexicographical order among all possible results.

     Example:
     Given "bcabc"
     Return "abc"

     Given "cbacdcbc"
     Return "acdb"
     */

    /**
     * Solution:
     * The key point is need to put smallest char at first, but if there's only one current char, put it at front anyway
     */

    /**
     * Solution 1:
     * 1. Use map1 to save, character -> frequency pair
     * 2. loop the string,
     *      2.1 select min character if frequency > 0
     *      2.1 *** at the same time, key point: if cur char frequency is 1, break the loop ***
     *      In this case it will select the min char1 before the frequency 1 char2 (char1 char2 may the same)
     *      append this char to res, AND set frequency of this char to 0 (in case to not select this char again)
     * repeat step 2 from substring(char1 to end), until string is ended
     *
     * Time: O(n * 26) = O(n) Due to will skip this char after each loop
     * Space: O(n)
     */

    /**
     * Solution 2:
     * Same as solution 1, but greedy steps, which try the same step2, but recursion to next substring(char1 to end) and
     * replace all char1 with "" to delete them
     *
     * Time: O(n^2)
     * Space: O(n * 26) = O(n)
     */

    public String removeDuplicateLetters(String s) {
        int[] cnt = new int[26];
        int pos = 0; // the position for the smallest s[i]
        for (int i = 0; i < s.length(); i++) cnt[s.charAt(i) - 'a']++;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) < s.charAt(pos)) pos = i;
            if (--cnt[s.charAt(i) - 'a'] == 0) break;
        }
        return s.length() == 0 ? "" : s.charAt(pos) + removeDuplicateLetters(s.substring(pos + 1).replaceAll("" + s.charAt(pos), ""));
    }

}
