package dynamic_programming.one_d_dp;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class WordBreak {

    //TAG: Uber
    //TAG: DP
    //TAG: 1d DP

    /**
     * 139. Word Break
     * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words. You may assume the dictionary does not contain duplicate words.

     For example, given
     s = "leetcode",
     dict = ["leet", "code"].

     Return true because "leetcode" can be segmented as "leet code".

     UPDATE (2017/1/4):
     The wordDict parameter had been changed to a list of strings (instead of a set of strings). Please reload the code definition to get the latest changes.
     */

    /**
     * 1 d dp, base case dp[0] = false, which "" cannot segmented from words in dict
     * induction rule: dp[i] represent s.substring(0, i) could be segmented from words in dict, which steps follow:
     *  1. if wordDict contains s.substring(0, i) dp[i] = true
     *  2. else loop j from 0 to i - 1, if dp[j] = true, means s.subString(0, j) in dict, and
     *  wordDict contains(s.substring(j + 1, i)), dp[i] = true, s.substring(0, i) canbe segmented into (0, j) + (j + 1, i)
     *  return dp[len - 1]
     *
     *  Time: O(n^2)
     *  Space: O(n)
     */

    public boolean wordBreak(String s, List<String> wordDict) {
        if (s.length() == 0 && wordDict.size() == 0) return true;
        if (s.length() == 0 || wordDict.size() == 0) return false;
        //Convert list to set, speed up the look up
        HashSet<String> set = new HashSet<>();
        for (String str: wordDict) set.add(str);
        boolean dp[] = new boolean[s.length()];
        for (int i = 0; i < s.length(); i++) {
            if (set.contains(s.substring(0, i + 1))) dp[i] = true;
            for (int j = 0; j < i; j++) {
                if (dp[j] && set.contains(s.substring(j + 1, i + 1)))
                    dp[i] = true;
            }
        }
        return dp[dp.length - 1];
    }

}
