package map_set;

import java.util.*;

public class ShortestWordDistanceII {

    //TAG: LinkedList
    //TAG: map_set
    //TAG: array
    //Difficulty: Medium

    /**
     * 244. Shortest Word Distance II
     * This is a follow up of Shortest Word Distance. The only difference is now you are given the list of words and your method will be called repeatedly many times with different parameters. How would you optimize it?

     Design a class which receives a list of words in the constructor, and implements a method that takes two words word1 and word2 and return the shortest distance between these two words in the list.

     For example,
     Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

     Given word1 = “coding”, word2 = “practice”, return 3.
     Given word1 = "makes", word2 = "coding", return 1.

     Note:
     You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
     */

    /**
     * Solution:
     * Depends on ShortestWordDistance solution, the method will be called multiple times, it may duplicated calculate the distance of some visited words, so we'd better to record the result of any visited pair of words' shortest distance
     * As different pair of word distances, so could not only record the most updated word index, need to record every appeared index of every word
     * So we use a hashMap, which the key is the word, value is an arrayList to store all indices of this word
     * for shortest distance method, do a similar way as ShortestWordDistance, update smaller index of two indices of word1 word2, keep updating the min value until any of the list is done (if one list is done, and another is not, along with this array goes, the distance will must increase, must not be the min value, so any one is done, all loop is done)
     *
     * Time: O(n) construct hashMap + O(n) * times
     * Space: O(n) if every word appear once in hashMap
     */

    public class WordDistance {

        HashMap<String, List<Integer>> map;

        public WordDistance(String[] words) {
            map = new HashMap<>();
            for (int i = 0; i < words.length; i++) {
                if (map.containsKey(words[i])) map.get(words[i]).add(i);
                else {
                    List<Integer> list = new ArrayList<>();
                    list.add(i);
                    map.put(words[i], list);
                }
            }
        }

        public int shortest(String word1, String word2) {
            List<Integer> list1 = map.get(word1), list2 = map.get(word2);
            int distance = Integer.MAX_VALUE;
            if (list1 == null || list2 == null) return distance;
            for (int i = 0, j = 0; i < list1.size() && j < list2.size();) {
                int index1 = list1.get(i), index2 = list2.get(j);
                distance = Math.min(distance, Math.abs(index1 - index2));
                //Move smaller index to try to make distance shorter
                if (index1 < index2) i++;
                else j++;
            }
            return distance;
        }
    }

}
