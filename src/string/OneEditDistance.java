package string;

public class OneEditDistance {

    //TAG: Facebook
    //TAG: Uber
    //TAG: String
    //Difficulty: Medium

    /**
     * 161. One Edit Distance
     * Given two strings S and T, determine if they are both one edit distance apart.
     * Note:
     *
     * There are 3 possiblities to satisify one edit distance apart:
     *
     * Insert a character into s to get t
     * Delete a character from s to get t
     * Replace a character of s to get t
     * Example 1:
     *
     * Input: s = "ab", t = "acb"
     * Output: true
     * Explanation: We can insert 'c' into s to get t.
     * Example 2:
     *
     * Input: s = "cab", t = "ad"
     * Output: false
     * Explanation: We cannot get t from s by only one step.
     * Example 3:
     *
     * Input: s = "1203", t = "1213"
     * Output: true
     * Explanation: We can replace '0' with '1' to get t.
     */

    /*
     * Solution:
     * Just check if could edit once, so loop the string, when find 1 char different:
     *      if length is the same, that means current diff char need be replaced,
     *      compare s.substring(i + 1) and t.substring(i + 1)
     *      if length is different, check small.substring(i) with large.substring(i + 1)
     *
     * Time: O(min(s, t))
     * Space: O(1)
     */

    public boolean isOneEditDistance(String s, String t) {
        int lens = s.length(), lent = t.length();
        for (int i = 0; i < Math.min(lens, lent); i++) {
            if (s.charAt(i) != t.charAt(i)) {
                //Length is same, could only replace
                if (lens == lent) return s.substring(i + 1).equals(t.substring(i + 1));
                //Otherwise could only add 1 char to shorter string or delete 1 char to longer string
                else return lens < lent ? s.substring(i).equals(t.substring(i + 1)) :
                        s.substring(i + 1).equals(t.substring(i));
            }
        }
        /*
        If goes here, that means only last char of longer string left, check if diff length is 1, totally same string
        should return false
         */
        return Math.abs(lens - lent) == 1;
    }

}
