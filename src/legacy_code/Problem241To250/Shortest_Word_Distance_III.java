package legacy_code.Problem241To250;

public class Shortest_Word_Distance_III {

    /**
     * 245. Shortest Word Distance III
     * This is a follow up of Shortest Word Distance. The only difference is now word1 could be the same as word2.

     Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.

     word1 and word2 may be the same and they represent two individual words in the list.

     For example,
     Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

     Given word1 = “makes”, word2 = “coding”, return 1.
     Given word1 = "makes", word2 = "makes", return 3.

     Note:
     You may assume word1 and word2 are both in the list.
     */

    /**
     * Solution:
     * Depends on Shortest_Word_Distance, diff is a diff way to calculate the distance when word1.equals(word2), need to calculate i - index1, and then update index1 to i
     * For the code, could integrate equals and not equals into one for loop, like
     * if (word1.equals(word2)) else, but this will make extra n times compare will have more time complexity, so check word1.equals(word2) once
     *
     * Time: O(n)
     * Space: O(1)
     */

    public int shortestWordDistance(String[] words, String word1, String word2) {
        //If index1 or index2 is -1, means haven't found a matched word
        int index1 = -1;
        int res = Integer.MAX_VALUE;
        if (word1.equals(word2)) {
            for (int i = 0; i < words.length; i++) {
                if (words[i].equals(word1)) {
                    if (index1 != -1) res = Math.min(res, Math.abs(i - index1));
                    index1 = i;
                }
            }
        } else {
            int index2 = -1;
            for (int i = 0; i < words.length; i++) {
                if (words[i].equals(word1)) index1 = i;
                else if (words[i].equals(word2)) index2 = i;
                //If word1 and word2 found at least once, update the res
                if (index1 != -1 && index2 != -1) res = Math.min(res, Math.abs(index1 - index2));
            }
        }
        return res;
    }

}
