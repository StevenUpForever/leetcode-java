public class ValidPalindromeII {

    //TAG: Facebook
    //TAG: Recursion
    //Difficulty: Easy

    /**
     * 680. Valid Palindrome II
     * Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.
     *
     * Example 1:
     * Input: "aba"
     * Output: True
     * Example 2:
     * Input: "abca"
     * Output: True
     * Explanation: You could delete the character 'c'.
     * Note:
     * The string will only contain lowercase characters a-z. The maximum length of the string is 50000.
     */

    /**
     * Solution:
     * Recursion, check current left and right char is paired, if not and not delete char before, try
     * delete left and right char and || the results
     *
     * Time: O(2n)
     * Space: O(n)
     *
     * May stackoverflow due to The maximum length of the string is 50000
     */

    public boolean validPalindrome(String s) {
        if (s == null || s.length() == 0) return false;
        return validPalindromeHelper2(s, 0, s.length() - 1, true);
    }

    private boolean validPalindromeHelper(String s, int l, int r, boolean modify) {
        if (l >= r) return true;
        char a = s.charAt(l), b = s.charAt(r);
        if (a != b && !modify) return false;
        if (a == b) return validPalindromeHelper(s, l + 1, r - 1, modify);
        else return validPalindromeHelper(s, l, r - 1, false) ||
                validPalindromeHelper(s, l + 1, r, false);
    }

    /**
     * Solution 2: iteration
     * same thing as solution 1, but use iteration, max 3 levels
     *
     * Time: O(n)
     * Space: O(1)
     */

    private boolean validPalindromeHelper2(String s, int l, int r, boolean modify) {
        while (l < r) {
            if (s.charAt(l) != s.charAt(r)) {
                if (modify) {
                    return validPalindromeHelper2(s, l, r - 1, false) ||
                            validPalindromeHelper2(s, l + 1, r, false);
                } else return false;
            }
            l++;
            r--;
        }
        return true;
    }

}
