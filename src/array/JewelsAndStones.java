package array;

import java.util.HashSet;
import java.util.Set;

public class JewelsAndStones {

    //Difficulty: Easy
    //TAG: Apple
    //TAG: array

    /**
     * 771. Jewels and Stones
     * You're given strings J representing the types of stones that are jewels, and S representing the stones you have.  Each character in S is a type of stone you have.  You want to know how many of the stones you have are also jewels.
     *
     * The letters in J are guaranteed distinct, and all characters in J and S are letters. Letters are case sensitive, so "a" is considered a different type of stone from "A".
     *
     * Example 1:
     *
     * Input: J = "aA", S = "aAAbbbb"
     * Output: 3
     * Example 2:
     *
     * Input: J = "z", S = "ZZ"
     * Output: 0
     * Note:
     *
     * S and J will consist of letters and have length at most 50.
     * The characters in J are distinct.
     */

    /*
    Solution:
    Add chars in J to set and filter add count in chars of S

    Time: O(J + S)
    Space: O(J)
     */

    public int numJewelsInStones(String J, String S) {
        if (J == null || S == null) return 0;
        int count = 0;
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < J.length(); i++) set.add(J.charAt(i));
        for (int j = 0; j < S.length(); j++) {
            if (set.contains(S.charAt(j))) {
                count++;
            }
        }
        return count;
    }

}
