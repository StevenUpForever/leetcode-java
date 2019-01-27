package string.reverse;

public class ReverseWordsInAStringIII {

    //Difficulty: Easy
    //TAG: Apple
    //TAG: String

    /**
     * 557. Reverse Words in a String III
     * Given a string, you need to reverse the order of characters in each word within a sentence while still preserving whitespace and initial word order.
     *
     * Example 1:
     * Input: "Let's take LeetCode contest"
     * Output: "s'teL ekat edoCteeL tsetnoc"
     * Note: In the string, each word is separated by single space and there will not be any extra space in the string.
     */

    /*
    Solution 1:
    Split s to array by ' ', reverse each array[i] and append to StringBuilder

    Time: O(n)
    Space: O(n)
     */

    /*
    Solution 2:
    set two index, start and end, find each word in one loop and reverse

    Time: O(n)
    Space: O(1) if not calculate char[] due to immutable String
     */

    public String reverseWords(String s) {
        if (s == null) return null;
        char[] chars = s.toCharArray();
        int start = -1, end = -1;
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (c == ' ') {
                if (start != -1 && end != -1) {
                    swapSequence(chars, start, end);
                }
                start = -1;
                end = -1;
                continue;
            }
            if (start == -1) start = i;
            else end = i;
        }
        if (start != -1) swapSequence(chars, start, chars.length - 1);
        return String.valueOf(chars);
    }

    private void swapSequence(char[] chars, int i, int j) {
        while (i < j) {
            char c = chars[i];
            chars[i] = chars[j];
            chars[j] = c;
            i++;
            j--;
        }
    }

}
