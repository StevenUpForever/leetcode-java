package depth_first_search;

import java.util.*;

public class WordBreakII {

    //TAG: Uber
    //TAG: DFS

    /**
     * 140. Word Break II
     * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s to construct a sentence where each word is a valid dictionary word. You may assume the dictionary does not contain duplicate words.

     Return all such possible sentences.

     For example, given
     s = "catsanddog",
     dict = ["cat", "cats", "and", "sand", "dog"].

     A solution is ["cats and dog", "cat sand dog"].

     UPDATE (2017/1/4):
     The wordDict parameter had been changed to a list of strings (instead of a set of strings). Please reload the code definition to get the latest changes.
     */

    public List<String> wordBreak(String s, List<String> wordDict) {
        return wordBreakHelper(s, wordDict, new HashMap<>());
    }

    List<String> wordBreakHelper(String s, List<String> wordDict, HashMap<String, List<String>>map) {
        //Map contains current substring -> String combine, so that needn't duplicated generate same list
        if (map.containsKey(s))
            return map.get(s);

        List<String>res = new LinkedList<>();
        //If s loop to the end, add "", so that at the end of res, will not append one more " "
        if (s.length() == 0) {
            res.add("");
            return res;
        }
        for (String word : wordDict) {
            if (s.startsWith(word)) {
                List<String> sublist = wordBreakHelper(s.substring(word.length()), wordDict, map);
                for (String sub: sublist)
                    //if sub is "" described above
                    res.add(word + (sub.isEmpty() ? "" : " ") + sub);
            }
        }
        map.put(s, res);
        return res;
    }

}
