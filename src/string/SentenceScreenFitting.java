package string;

public class SentenceScreenFitting {

    //TAG: Google
    //TAG: String
    //TAG: Math
    //Difficulty: Medium

    /**
     * 418. Sentence Screen Fitting
     * Given a rows x cols screen and a sentence represented by a list of non-empty words, find how many times the given sentence can be fitted on the screen.

     Note:

     A word cannot be split into two lines.
     The order of words in the sentence must remain unchanged.
     Two consecutive words in a line must be separated by a single space.
     Total words in the sentence won't exceed 100.
     Length of each word is greater than 0 and won't exceed 10.
     1 ≤ rows, cols ≤ 20,000.
     Example 1:

     Input:
     rows = 2, cols = 8, sentence = ["hello", "world"]

     Output:
     1

     Explanation:
     hello---
     world---

     The character '-' signifies an empty space on the screen.
     Example 2:

     Input:
     rows = 3, cols = 6, sentence = ["a", "bcd", "e"]

     Output:
     2

     Explanation:
     a-bcd-
     e-a---
     bcd-e-

     The character '-' signifies an empty space on the screen.
     Example 3:

     Input:
     rows = 4, cols = 5, sentence = ["I", "had", "apple", "pie"]

     Output:
     1

     Explanation:
     I-had
     apple
     pie-I
     had--

     The character '-' signifies an empty space on the screen.
     */

    /**
     * Solution 1:
     * Loop the matrix, and count the repeat of words
     *
     * Time: O(rows * cols)
     * Space: O(1)
     */

    public int wordsTyping(String[] sentence, int rows, int cols) {
        int index = 0, count = 0;
        for (int i = 0; i < rows; i++) {
            int j = 0;
            //Assume add space before the word, so when j == 0, don't need to add prefix space
            while (j < cols && cols - j >= sentence[index].length() + (j == 0 ? 0 : 1)) {
                j += sentence[index].length() + (j == 0 ? 0 : 1);
                index++;
                if (index >= sentence.length) {
                    index = 0;
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Solution 2:
     * combine a string with spaces between and after all words
     * Only loop rows, for each row:
     * add col to totalLen, and do following checks:
     *      1. if totalLen/s.len is ' ', we don't need ' ' at last, len++
     *      2. if not ' ' skip back to previous word, since current word cannot full fill the col
     *
     * Time: O(rows * col) since go back may go all col length
     * Space: O(words total length)
     */

    public int wordsTyping2(String[] sentence, int rows, int cols) {
        String s = String.join(" ", sentence) + " ";
        //Len represent total length fit for total string with spaces
        int len = 0, l = s.length();
        for (int i = 0; i < rows; i++) {
            len += cols;
            /*
            if string filled after col stopped at space ' ', means last char in col is space,
            skip this space, means len++
             */
            if (s.charAt(len % l) == ' ') {
                len++;
                //else need to go back to previous words, since current word cannot filled left spaces in col
            } else {
                while (len > 0 && s.charAt((len - 1) % l) != ' ') {
                    len--;
                }
            }
        }
        return len / s.length();
    }

}
