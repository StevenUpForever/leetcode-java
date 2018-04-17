package dynamic_programming.one_d_dp;

import java.util.*;

public class FlipGameII {

    //TAG: Google
    //TAG: DP
    //Difficulty: Medium

    /**
     * 294. Flip Game II
     * You are playing the following Flip Game with your friend: Given a string that contains only these two characters: + and -, you and your friend take turns to flip two consecutive "++" into "--". The game ends when a person can no longer make a move and therefore the other person will be the winner.

     Write a function to determine if the starting player can guarantee a win.

     For example, given s = "++++", return true. The starting player can guarantee a win by flipping the middle "++" to become "+--+".

     Follow up:
     Derive your algorithm's runtime complexity.
     */

    /**
     * Solution 1: depth_first_search
     * Base case: if recursion step (the other player) could win, it's false, otherwise we win
     * recursion rule: for loop current s from 0 to len - 1, set i and i + 1 to '-' (skip the appeared '-')
     * recursion the next modified string s, (means steps for the other player)
     *
     * Time: O(n) * O(n - 2) * O(n - 4) *... *O(1) = O(n!!)
     * Space: O(n)
     */

    public boolean canWinS1(String s) {
        if (s == null || s.length() == 0) return false;
        char[] chars = s.toCharArray();
        return canWinHelperS1(chars);
    }

    private boolean canWinHelperS1(char[] chars) {
        for (int i = 0; i < chars.length - 1; i++) {
            //Skip existed '-'
            if (chars[i] == '+' && chars[i + 1] == '+') {
                chars[i] = chars[i + 1] = '-';
                //If the other player could not win
                boolean otherWin = !canWinHelperS1(chars);
                //Reset backTrack status
                chars[i] = chars[i + 1] = '+';
                //If other player couldn't win, we win
                if (otherWin) return true;
            }
        }
        return false;
    }

    /**
     * Solution 2: DP
     * Depends on solution 1, the situation after
     *      1. string ++--++++, modify first two +
     *      2. string --++++++, modify second two +
     * is the same, so we calculate many duplicated results
     * As this time, the larger problem may not related to smaller problem immediately, we'd better not to use an array to record dp values, we could use a hashMap to record the already modified string value
     * Base case: if same helper could not win, we win, or if find the result from hashMap, return the result from hashMap
     * Induction rule: same as solution 1 helper function
     *
     * Time: O(n^2)
     * Space: O(n) hashMap + O(n) recursion levels = O(n)
     */

    public boolean canWinS2(String s) {
        if (s == null || s.length() == 0) return false;
        return canWinHelperS2(s, new HashMap<>());
    }

    private boolean canWinHelperS2(String s, HashMap<String, Boolean> map) {
        if (map.containsKey(s)) return map.get(s);
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.startsWith("++", i)) {
                String modifiedStr = s.substring(0, i) + "--" + s.substring(i+2);
                //Don't need reset status due to new string not changed s
                if (!canWinHelperS2(modifiedStr, map)) {
                    //Need to set origin string s as key not the modified string
                    map.put(s, true);
                    return true;
                }
            }
        }
        map.put(s, false);
        return false;
    }

}
