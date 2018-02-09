package string;

public class ReverseWordsInAStringII {

    //TAG: Uber
    //TAG: string

    /**
     * 186. Reverse Words in a String II
     * Given an input string, reverse the string word by word. A word is defined as a sequence of non-space characters.

     The input string does not contain leading or trailing spaces and the words are always separated by a single space.

     For example,
     Given s = "the sky is blue",
     return "blue is sky the".

     Could you do it in-place without allocating extra space?

     Related problem: Rotate Array

     Update (2017-10-16):
     We have updated the function signature to accept a character array, so please reset to the default code definition by clicking on the reload button above the code editor. Also, Run Code is now available!

     */

    /**
     * Solution 1:
     * Split string to words array by " "
     * reverse array order and append to a new string
     *
     * Time: O(2n) = O(n)
     * Space: O(n)
     */

    /**
     * Solution 2:
     * reverse String so that "the sky is blue" to "eulb si yks eht"
     * Then reverse every word in the reverse string it became "blue is sky the"
     *
     * Time: O(2n) = O(n)
     * Space: O(1)
     */

    public void reverseWords(char[] str) {
        if (str == null || str.length == 0) return;
        reverse(str, 0, str.length - 1);
        int slow = 0;
        for (int fast = 0; fast < str.length; fast++) {
            if (fast == str.length - 1 || str[fast + 1] == ' ') {
                reverse(str, slow, fast);
                slow = fast + 2;
            }
        }
    }

    private void reverse(char[] chars, int start, int end) {
        while (start < end) {
            char temp = chars[start];
            chars[start] = chars[end];
            chars[end] = temp;
            start++;
            end--;
        }
    }

}
