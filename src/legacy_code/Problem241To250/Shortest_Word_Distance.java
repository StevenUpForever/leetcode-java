package legacy_code.Problem241To250;

public class Shortest_Word_Distance {

    /**
     * 243. Shortest Word Distance
     * Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.

     For example,
     Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

     Given word1 = “coding”, word2 = “practice”, return 3.
     Given word1 = "makes", word2 = "coding", return 1.

     Note:
     You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
     */

    /**
     * Solution:
     * keep track of index1 (new index of word1), index2(new index of word2)
     * keep track of a global min as result
     * for every string in words
     *      if met word1 or word2, update index1 or index2, and update result Math.abs(index1 - index2), until the for loop is done
     * Reason: as the string moves from left to right,
     *      if new index1(index2) is more approach to index2(index1), it will be a better min solution
     *      if new index1(index2) is further to index2(index1), the Math.min will keep the min result (the best answer is already visited before some updates)
     *
     * Time: O(n)
     * Space: O(1)
     */

    public int shortestDistance(String[] words, String word1, String word2) {
        //If index1 or index2 is -1, means haven't found a matched word
        int index1 = -1, index2 = -1;
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1)) index1 = i;
            else if (words[i].equals(word2)) index2 = i;
            //If word1 and word2 found at least once, update the res
            if (index1 != -1 && index2 != -1) res = Math.min(res, Math.abs(index1 - index2));
        }
        return res;
    }

}
