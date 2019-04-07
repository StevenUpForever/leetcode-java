package string.pattern;

import java.util.ArrayList;
import java.util.List;

public class Q1023CamelcaseMatching {

    //Difficulty: medium
    //TAG: String
    //TAG: pattern

    /**
     * 1023. Camelcase Matching
     * A query word matches a given pattern if we can insert lowercase letters to the pattern word so that it equals the query. (We may insert each character at any position, and may insert 0 characters.)
     *
     * Given a list of queries, and a pattern, return an answer list of booleans, where answer[i] is true if and only if queries[i] matches the pattern.
     *
     *
     *
     * Example 1:
     *
     * Input: queries = ["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"], pattern = "FB"
     * Output: [true,false,true,true,false]
     * Explanation:
     * "FooBar" can be generated like this "F" + "oo" + "B" + "ar".
     * "FootBall" can be generated like this "F" + "oot" + "B" + "all".
     * "FrameBuffer" can be generated like this "F" + "rame" + "B" + "uffer".
     * Example 2:
     *
     * Input: queries = ["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"], pattern = "FoBa"
     * Output: [true,false,true,false,false]
     * Explanation:
     * "FooBar" can be generated like this "Fo" + "o" + "Ba" + "r".
     * "FootBall" can be generated like this "Fo" + "ot" + "Ba" + "ll".
     * Example 3:
     *
     * Input: queries = ["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"], pattern = "FoBaT"
     * Output: [false,true,false,false,false]
     * Explanation:
     * "FooBarTest" can be generated like this "Fo" + "o" + "Ba" + "r" + "T" + "est".
     *
     *
     * Note:
     *
     * 1 <= queries.length <= 100
     * 1 <= queries[i].length <= 100
     * 1 <= pattern.length <= 100
     * All strings consists only of lower and upper case English letters.
     */

    /*
    Solution:

    Two pointers, loop query string as current character c, and index for pattern string:
    if c matches current pattern[index]:
        we move index++, it includes upper and lower case
    otherwise 2 cases:
        if c is lowercase, that matches any inserted chars, we keep going
        if c is uppercase, that means either index == pattern.length or c != pattern[index] then it's false
    after inner loop, if index == pattern.length, means pattern is fully matched, we add true

    Time: O(mn)
    Space: O(1)
     */

    public List<Boolean> camelMatch(String[] queries, String pattern) {
        List<Boolean> list = new ArrayList<>();
        if (queries == null || pattern == null) return list;
        outer: for (String query: queries) {
            int index = 0;
            for (int i = 0; i < query.length(); i++) {
                char c = query.charAt(i);
                if (index < pattern.length() && c == pattern.charAt(index)) {
                    index++;
                } else if (c >= 'A' && c <= 'Z') {
                    list.add(false);
                    continue outer;
                }
            }
            list.add(index == pattern.length());
        }
        return list;
    }

}
