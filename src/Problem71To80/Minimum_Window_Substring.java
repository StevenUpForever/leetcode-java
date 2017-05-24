package Problem71To80;

import java.util.HashMap;

public class Minimum_Window_Substring {

    /**
     *
     *
     * Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

     For example,
     S = "ADOBECODEBANC"
     T = "ABC"
     Minimum window is "BANC".

     Note:
     If there is no such window in S that covers all characters in T, return the empty string "".

     If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.
     */

    /**
     * Solution: Sliding window problem
     * Keep a global min length
     * Keep a sliding window length of t, keep record the characters occurrence, increase right side of window, when all characters appeared in the window, keep update the global min length
     * Move left side while all characters appear at least once in the window, keep updating the global min
     *      if some characters out of window, move right until new same character in the window
     *
     * Time: O(2n) left + right = O(n)
     * Space: O()
     */

    public String minWindow(String s, String t) {
        if (s.length() == 0 || t.length() == 0 || s.length() < t.length()) return "";
        HashMap<Character, Integer> map = new HashMap<>();
        //Count represent how many chars currently fully matches
        //Min represent the min length of current valid substring
        //TempLeft is the left index which short the length as far as possible
        //Left and right represent the optimized left and right index of valid string
        int count = 0, min = Integer.MAX_VALUE, tempLeft = 0, left = 0, right = 0;
        char[] charsS = s.toCharArray(), charsT = t.toCharArray();
        for (char c: charsT) {
            if (!map.containsKey(c)) map.put(c, 1);
            else map.put(c, map.get(c) + 1);
        }
        for (int i = 0; i < charsS.length; i++) {
            if (map.containsKey(charsS[i])) {
                int cur = map.get(charsS[i]);
                //If cur == 1 will became 0 in the future, then this char will be fully matched, increase count
                if (cur == 1) count++;
                map.put(charsS[i], cur - 1);
            }
            //If all matches update right index
            if (count == map.size() && min > i - tempLeft + 1) {
                min = i - tempLeft + 1;
                right = i;
            }
            //Short the valid length as much as possible
            while (count == map.size()) {
                if (map.containsKey(charsS[tempLeft])) {
                    int cur = map.get(charsS[tempLeft]);
                    if (cur == 0) count--;
                    map.put(charsS[tempLeft], cur + 1);
                }
                //At this time, even count-- already, but tempLeft haven't ++, so tempLeft is still a valid left index, try to update left and also right if min could updated
                if (min > i - tempLeft + 1) {
                    min = i - tempLeft + 1;
                    left = tempLeft;
                    /*
                    The reason why update right too when only move tempLeft
                    When tempLeft moves so count != map.size(), i need to move right to supply this character from right side
                    When find one, the hashMap is updated to count != map.size(), but right not updated (while loop will not end depends on if left < right, so will not move to next for loop to update right)
                     */
                    right = i;
                }
                tempLeft++;
            }
        }
        //If min hasn't update once, then no matched substring, otherwise use left and right index substring
        return min == Integer.MAX_VALUE ? "" : s.substring(left, right + 1);
    }

}
