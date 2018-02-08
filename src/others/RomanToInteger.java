package others;

import java.util.HashMap;

public class RomanToInteger {

    //TAG: Uber
    //TAG: Others

    /**
     * 13. Roman to Integer
     * Given a roman numeral, convert it to an integer.
     Input is guaranteed to be within the range from 1 to 3999.
     */

    /**
     * Solution:
     * Roman has a feature that about the last second number of 5 or 10, it's smaller append larger number, otherwise, it's larger number append multiple smaller or equal number, so we parse the roman from end to start
     * 1. Need a dictionary to represent different level of roman (multiple by 5), for 1, 5, 10, 50, 100, 500, 1000, every roman is combine with several roman from these tagged romans
     * 2. loop from end of s to front
     *      1. when current roman is larger than pre one (next roman), means it's a number except 5, 10, increase the current number
     *      2. when current roman is smaller than pre one, like "IV" 4, "IX" 9, means we decrease pre number 5, 10 by current roman 1 to 4, 9
     * 3. When met the next level number, repeat step 2
     * 4. got final integer and return, not necessary to * 10 + % 10, due to different roman represent already enough bits of 0s [1, 10, 100, 1000]
     *
     * Time: O(n)
     * Space: O(1) constant space for map
     */

    public int romanToInt(String s) {
        if (s == null) return 0;
        HashMap<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        //Max Roman to 3999, so int is enough
        int res = map.get(s.charAt(s.length() - 1));
        //Keep a global pre number to compare with current number to determine if need to add or minus
        int pre = res;
        for (int i = s.length() - 2; i >= 0; i--) {
            int cur = map.get(s.charAt(i));
            /*
            This is else contained several conditions
                1. when cur and pre is at same level, like I and V, (for example III, VI, IV) it's right
                2. when cur and pre is not at same level, like I and X (for example cur is X, pre is I, or reverse order, means IX 9, or XI 11) it right
             */
            if (cur < pre) res -= cur;
            else res += cur;
            pre = cur;
        }
        return res;
    }

}
