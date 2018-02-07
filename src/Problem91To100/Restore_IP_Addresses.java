package Problem91To100;

import java.util.*;

public class Restore_IP_Addresses {

    /**
     * 93. Restore IP Addresses
     * Given a string containing only digits, restore it by returning all possible valid IP address combinations.

     For example:
     Given "25525511135",

     return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)
     */

    /**
     * Approach:
     * IP address rules, the number between .num.
     *      cannot be length > 3 or == 0
     *      cannot be a number larger than length 1 and start with 0
     *      cannot be > 256
     *      cannot be more than 4 parts
     */

    /**
     * Solution: depth_first_search
     * 3 branch recursion tree, every level represent add how many characters from current index (len < 4)
     * Totally should have 4 levels as when count > 4 will return
     *
     * Time: O(3^4)
     * Space: O(4) + O(3^4) = O(3^4)
     */

    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        IPAddressHelper(0, res, "", s, 0);
        return res;
    }

    private void IPAddressHelper(int index, List<String> res, String cur, String s, int count) {
        //If could separate more than 4 parts, not valid
        if (count > 4) return;
        //only if we could goes here, 4 parts and no one incorrect, add this valid string
        if (count == 4 && index == s.length()) {
            res.add(cur);
            return;
        }
        for (int i = 1; i < 4; i++) {
            //Need to make substring.endIndex <= s.len
            if (index + i <= s.length()) {
                String curStr = s.substring(index, index + i);
                //Rules as described in approach
                if ((curStr.length() > 1 && curStr.charAt(0) == '0') || (i == 3 && Integer.parseInt(curStr) >= 256)) continue;
                IPAddressHelper(index + i, res, cur + curStr + (count == 3 ? "" : "."), s, count + 1);
            }
        }
    }

    /**
     * Solution 2:
     * for string 123 for(1) 123 for(2) 123 for(3) 123, we could insert three for loops which separate this string into 4 parts, have substring (0, a) (a, b) (b, c) (c, d)
     * Have a validation helper function to validate all substrings, if all valid, append together and add to res list
     *
     * Time: O(n^3)
     * Space: O(3^4)
     */

}
