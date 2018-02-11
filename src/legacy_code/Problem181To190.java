package legacy_code;

/**
 * Created by c0j00cs on 3/15/17.
 */
public class Problem181To190 {
    /*
    186. Reverse Words in a String II
    Given an input string, reverse the string word by word. A word is defined as a sequence of non-space characters.

The input string does not contain leading or trailing spaces and the words are always separated by a single space.

For example,
Given s = "the sky is blue",
return "blue is sky the".

Could you do it in-place without allocating extra space?
     */
    /*
    Approach: reverse all string, then reverse each word
     */
    public void reverseWords(char[] s) {
        if (s == null || s.length < 2) return;
        reverseStr(s, 0, s.length - 1);
        int i = 0, j = 0;
        while (j < s.length) {
            if (s[j] == ' ') {
                reverseStr(s, i, j - 1);
                i = j + 1;
            }
            j++;
        }
        reverseStr(s, i, s.length - 1);
    }

    private void reverseStr(char[] s, int a, int b) {
        while (a < b) {
            char temp = s[a];
            s[a] = s[b];
            s[b] = temp;
            a++;
            b--;
        }
    }
}
