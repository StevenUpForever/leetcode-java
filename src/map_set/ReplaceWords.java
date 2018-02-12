package map_set;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ReplaceWords {

    //TAG: Uber
    //TAG: HashMap
    //Difficulty: Medium

    /**
     * 648. Replace Words
     * In English, we have a concept called root, which can be followed by some other words to form another longer word - let's call this word successor. For example, the root an, followed by other, which can form another word another.

     Now, given a dictionary consisting of many roots and a sentence. You need to replace all the successor in the sentence with the root forming it. If a successor has many roots can form it, replace it with the root with the shortest length.

     You need to output the sentence after the replacement.

     Example 1:
     Input: dict = ["cat", "bat", "rat"]
     sentence = "the cattle was rattled by the battery"
     Output: "the cat was rat by the bat"
     Note:
     The input will only have lower-case letters.
     1 <= dict words number <= 1000
     1 <= sentence words number <= 1000
     1 <= root length <= 100
     1 <= sentence words length <= 1000
     */

    /**
     * Solution:
     * Put strings from dict to hashSet, easy to find root, record the min length minLen of strings
     * loop split arrays of sentences by " ", loop substring of each string from minLen to end, if set contains it,
     * append this shortest root to stringBuilder
     * after loop if no root found, append the origin string
     * return the stringBuilder
     *
     * Time: O(m) dict -> set + O(n) split sentence by " " + O(n) loop split strings = O(m + n)
     * Space: O(m + n)
     */

    public String replaceWords(List<String> dict, String sentence) {
        int minLen = Integer.MAX_VALUE;
        Set<String> set = new HashSet<>();
        for (int i = 0; i < dict.size(); i++) {
            set.add(dict.get(i));
            minLen = Math.min(minLen, dict.get(i).length());
        }
        StringBuilder builder = new StringBuilder();
        String[] strs = sentence.split(" ");
        for (String str: strs) {
            for (int i = minLen; i <= str.length(); i++) {
                String root = str.substring(0, i);
                if (set.contains(root)) {
                    str = root;
                    break;
                }
            }
            builder.append(str);
            builder.append(" ");
        }
        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }

}
