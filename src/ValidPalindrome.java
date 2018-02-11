public class ValidPalindrome {

    //TAG: Uber
    //TAG: String

    /**
     * 125. Valid Palindrome
     * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

     For example,
     "A man, a plan, a canal: Panama" is a palindrome.
     "race a car" is not a palindrome.

     Note:
     Have you consider that the string might be empty? This is a good question to ask during an interview.

     For the purpose of this problem, we define empty string as valid palindrome.
     */

    /**
     * Solution:
     * Time: O(n)
     * Space: O(1)
     */


    public boolean isPalindrome(String s) {
        int start = 0, end = s.length() - 1;
        while (start < end) {
            char startChar = s.charAt(start), endChar = s.charAt(end);
            if (!Character.isLetterOrDigit(startChar)) start++;
            else if (!Character.isLetterOrDigit(endChar)) end--;
            else if (Character.toLowerCase(startChar) == Character.toLowerCase(endChar)) {
                start++;
                end--;
            } else return false;
        }
        return true;
    }


}
