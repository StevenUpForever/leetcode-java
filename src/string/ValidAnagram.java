package string;

public class ValidAnagram {

    //TAG: Uber
    //TAG: map_set
    //TAG: String

    /**
     * 242. Valid Anagram
     * Given two strings s and t, write a function to determine if t is an anagram of s.

     For example,
     s = "anagram", t = "nagaram", return true.
     s = "rat", t = "car", return false.

     Note:
     You may assume the string contains only lowercase alphabets.

     Follow up:
     What if the inputs contain unicode characters? How would you adapt your solution to such case?
     */

    /**
     * Solution:
     * Due to lowercase alphabets, use int[] represent char frequency of str s
     * loop str t, check if any char frequency is currently 0 and will be -1, if so, that char frequency will not match,
     * return false (need check s_length == t_length before)
     * if met the end, return true
     *
     * Time: O(n)
     * Space: O(1) int[26] is O(1)
     */

    public boolean isAnagram(String s, String t) {
        if (s.length() == 0 && t.length() == 0) return true;
        if (s.length() == 0 || t.length() == 0 || s.length() != t.length()) return false;
        int[] arr = new int[26];
        for (int i = 0; i < s.length(); i++) arr[s.charAt(i) - 'a']++;
        for (int i = 0; i < t.length(); i++) {
            int index = t.charAt(i) - 'a';
            if (arr[index] <= 0) return false;
            arr[index]--;
        }
        return true;
    }

    /**
     * Follow up: What if the inputs contain unicode characters? How would you adapt your solution to such case?
     * Same time, worse space, use HashMap record frequency, which Time: O(n) Space: O(n)
     * If same space, worse time, sort two strings and compare with equals(), time: O(nlogn) space: O(1)
     */

}
