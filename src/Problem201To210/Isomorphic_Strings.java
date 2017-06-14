package Problem201To210;

public class Isomorphic_Strings {

    /**
     * 205. Isomorphic Strings
     * Given two strings s and t, determine if they are isomorphic.

     Two strings are isomorphic if the characters in s can be replaced to get t.

     All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character but a character may map to itself.

     For example,
     Given "egg", "add", return true.

     Given "foo", "bar", return false.

     Given "paper", "title", return true.

     Note:
     You may assume both s and t have the same length.
     */

    /**
     * Solution: Similar DP problem
     * main idea is check every character in s and t at the same time
     * when find the appeared character of s and t in HashMap/Array, need to verify all appearances are the same
     * DP idea is, just need to verify last appeared character index, if not matched, return false, because if could go through the loop, means last appeared index is same
     */

    public boolean isIsomorphic(String s, String t) {
        int[] verify1 = new int[256], verify2 = new int[256];
        //Due to s and t are the same length
        for (int i = 0; i < s.length(); i++) {
            int char1 = s.charAt(i), char2 = t.charAt(i);
            if (verify1[char1] != verify2[char2]) return false;
            /*
            Need to skip the default value 0 in array
            character not appeared and be same as first char are same as 0
             */
            verify1[char1] = verify2[char2] = i + 1;
        }
        return true;
    }

}
