package Problem51To60;

public class Length_of_Last_Word {

    /**
     * 58. Length of Last Word
     * Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word in the string.

     If the last word does not exist, return 0.

     Note: A word is defined as a character sequence consists of non-space characters only.

     For example,
     Given s = "Hello World",
     return 5.
     */

    /**
     * Solution:
     * As the string not only contains alphabets and ' ', no symbols like ',' '.', so no need to check these symbols
     * for loop from the end
     * 1st to skip all suffix ' '
     * 2nd count the characters appeared until met the ' ' or reach the start
     * return the count
     *
     * Time: O(n)
     * Space: O(1)
     */

    public int lengthOfLastWord(String s) {
        int index = s.length() - 1, count = 0;
        while (index >= 0 && s.charAt(index) == ' ') index--;
        while (index >= 0 && s.charAt(index--) != ' ') count++;
        return count;
    }

}
