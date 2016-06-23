package LeetCode;

/**
 * Created by ChengzhiJia on 6/23/16.
 */
public class Problem121To130 {

    /*
 125. Valid Palindrome
 Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

 For example,
 "A man, a plan, a canal: Panama" is a palindrome.
 "race a car" is not a palindrome.

 Note:
 Have you consider that the string might be empty? This is a good question to ask during an interview.

 For the purpose of this problem, we define empty string as valid palindrome.
 */
    public boolean isPalindrome(String s) {
        if (s.length() == 0) return true;
        s = s.toLowerCase();
        int start = 0, end = s.length() - 1;
        while (start < end) {
            while (start < end && (s.charAt(start) - 'a' < 0 || s.charAt(start) - 'a' > 25) && (s.charAt(start) - '0' < 0 || s.charAt(start) - '0' > 9))
                start++;
            while (start < end && (s.charAt(end) - 'a' < 0 || s.charAt(end) - 'a' > 25) && (s.charAt(end) - '0' < 0 || s.charAt(end) - '0' > 9))
                end--;
            if (s.charAt(start) != s.charAt(end)) return false;
            start++;
            end--;
        }
        return true;
    }

}
