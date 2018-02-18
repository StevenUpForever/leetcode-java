package map_set;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SentenceSimilarity {

    //TAG: Google
    //TAG: map_set
    //Difficulty: Easy

    /**
     * 734. Sentence Similarity
     * Given two sentences words1, words2 (each represented as an array of strings), and a list of similar word pairs pairs, determine if two sentences are similar.

     For example, "great acting skills" and "fine drama talent" are similar, if the similar word pairs are pairs = [["great", "fine"], ["acting","drama"], ["skills","talent"]].

     Note that the similarity relation is not transitive. For example, if "great" and "fine" are similar, and "fine" and "good" are similar, "great" and "good" are not necessarily similar.

     However, similarity is symmetric. For example, "great" and "fine" being similar is the same as "fine" and "great" being similar.

     Also, a word is always similar with itself. For example, the sentences words1 = ["great"], words2 = ["great"], pairs = [] are similar, even though there are no specified similar word pairs.

     Finally, sentences can only be similar if they have the same number of words. So a sentence like words1 = ["great"] can never be similar to words2 = ["doubleplus","good"].

     Note:

     The length of words1 and words2 will not exceed 1000.
     The length of pairs will not exceed 2000.
     The length of each pairs[i] will be 2.
     The length of each words[i] and pairs[i][j] will be in the range [1, 20].
     */

    /**
     * Solution:
     * since "The length of pairs will not exceed 2000" search pairs will cost a lot of time
     * depends on "similarity is symmetric", and similarity chain
     * e.g. if [a, b] and [b, c] so similarity of b will be a and c *** this is the key point ***
     * put pairs in HashMap, which key is string (pairs[0] and pairs[1]) value is HashSet which contains
     * [a, b] b -> a and [b, c] b -> c
     * after base case loop words1 find if string is equal or map do not have words1[i] as key or set value
     * do not contains words2[i]
     *
     * Time: O(pairs + words1)
     * Space: O(2 * pairs)
     */

    public boolean areSentencesSimilar(String[] words1, String[] words2, String[][] pairs) {
        if (words1.length == 0 && words2.length == 0) return true;
        if (words1.length != words2.length) return false;
        if (pairs.length == 0) {
            for (int i = 0; i < words1.length; i++) {
                if (!words1[i].equals(words2[i])) return false;
            }
            return true;
        }
        Map<String, Set<String>> map = new HashMap<>();
        for (String[] pair: pairs) {
            if (!map.containsKey(pair[0])) map.put(pair[0], new HashSet<>());
            Set<String> val1 = map.get(pair[0]);
            val1.add(pair[1]);
            map.put(pair[0], val1);
            if (!map.containsKey(pair[1])) map.put(pair[1], new HashSet<>());
            Set<String> val2 = map.get(pair[1]);
            val2.add(pair[0]);
            map.put(pair[1], val2);
        }
        for (int i = 0; i < words1.length; i++) {
            //Be aware of that map may not contains such a key
            if (!words1[i].equals(words2[i]) &&
                    (!map.containsKey(words1[i]) || !map.get(words1[i]).contains(words2[i])))
                return false;
        }
        return true;
    }

}
