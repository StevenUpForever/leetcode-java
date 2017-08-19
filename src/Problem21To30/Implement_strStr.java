package Problem21To30;

import java.util.HashMap;

public class Implement_strStr {

    /**
     * 28. Implement strStr()
     * Implement strStr().

     Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
     */

    /**
     * Solution:
     * for loop the haystack, when met the first character, try to match next one, is all matches, immediately return the index of the first character
     *
     * Time: O(mn) m represent length of haystack, n represent length of needle
     * Space: O(1)
     */

    public int strStr(String haystack, String needle) {
        if (haystack == null || needle == null) return -1;
        char[] chars1 = haystack.toCharArray(), chars2 = needle.toCharArray();
        //Be aware of <= , simple example, when chars1.len == chars2.len, i should start from 0
        for (int i = 0; i <= chars1.length - chars2.length; i++) {
            int j = 0;
            while (j < chars2.length && chars1[i + j] == chars2[j]) j++;
            if (j == chars2.length) return i;
        }
        return -1;
    }

}
