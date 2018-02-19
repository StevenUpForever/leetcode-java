package legacy_code.Problem241To250;

public class Shortest_Word_Distance_III {

    //TAG: LinkedIn
    //TAG: array
    //Difficulty: Medium

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
     * Depends on ShortestWordDistance, diff is a diff way to calculate the distance when word1.equals(word2), need to calculate i - index1, and then update index1 to i
     * For the code, could integrate equals and not equals into one for loop, like
     * if (word1.equals(word2)) else, but this will make extra n times compare will have more time complexity, so check word1.equals(word2) once
     *
     * Time: O(n)
     * Space: O(1)
     */

    public int shortestWordDistance(String[] words, String word1, String word2) {
        //when not find any word, index = -1, max distance (variable min) will be words.length
        int index = -1, min = words.length;
        for (int i = 0; i < words.length; i++) {
            //If i met any word1 or word2
            if (words[i].equals(word1) || words[i].equals(word2)) {
                //If index at some word and (word1 == word2 or if not equal, not at the same word record before, means
                // the other word)
                if (index != -1 && (word1.equals(word2) || !words[i].equals(words[index]))) {
                    min = Math.min(min, i - index);
                }
                index = i;
            }
        }
        return min;
    }

}
