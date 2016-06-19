package LeetCode;

import java.lang.reflect.Array;
import java.util.*;

/*
Problem 1 Two Sum:
 Given an array of integers, return indices of the two numbers such that they add up to a specific target.

You may assume that each input would have exactly one solution.

Example:
Given nums = [2, 7, 11, 15], target = 9,

Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1].
UPDATE (2016/2/13):
The return format had been changed to zero-based indices. Please read the above updated description carefully.

Subscribe to see which companies asked this question

 */
public class Problem1To10 {

    public int[] twoSum(int[] nums, int target) {
        for (int index1 = 0; index1 < nums.length - 1; index1++) {
            for (int index2 = index1 + 1; index2 < nums.length; index2++) {
                if (nums[index1] + nums[index2] == target) {
                    return new int[]{index1, index2};
                }
            }
        }
        return new int[]{0, 0};
    }

    /*
    Problem 2 Add Two Numbers:
    You are given two linked lists representing two non-negative numbers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
     */
    //The linkedList of this problem is implemented by the class ListNode, reference by LeetCode discussion

        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            //Here temp is the final linkedList, result is the linkedList which in processing
            ListNode temp = new ListNode(0);
            ListNode result = temp;
            int nodeSum = 0;
            //If over the last node (null node), jump out the loop
            while (l1 != null || l2 != null)
            {
                //Because two list may not has the same length
                //At the beginning of the loop, nodeSum must be one digit, so nodeSum /= 10 is to clean it
                nodeSum /= 10;
                //If l1 is not over
               if (l1 != null) {
                   nodeSum += l1.val;
                   l1 = l1.next;
               }
                //If l2 is not over
                if (l2 != null) {
                    nodeSum += l2.val;
                    l2 = l2.next;
                }
                //Keep the node value in result be one digit and go next
                result.next = new ListNode(nodeSum % 10);
                result = result.next;
            }
            //If l1 value + l2 value over 9, make the result next node original value be 1
            if (nodeSum / 10 == 1)
                result.next = new ListNode(1);
            //If immediately return result, result.next will be the next of the last node, it must be null, so return a equalled linkedList temp and from the first node
            return temp.next;
        }


    /*
    Problem 3 Longest substring without characters:
    Given a string, find the length of the longest substring without repeating characters. For example, the longest substring without repeating letters for "abcabcbb" is "abc", which the length is 3. For "bbbbb" the longest substring is "b", with the length of 1.
     */
    public int lengthOfLongestSubstring(String s) {

        if (s == null)
        {
            return 0;
        }

        int lenNow = 0, maxLen = 0;
        HashMap<Integer, Integer> noRepeat = new HashMap<>();
        for (int index = 0; index < s.length(); index ++) {
            lenNow++;
            if (noRepeat.containsKey(s.codePointAt(index)) && lenNow > index - noRepeat.get(s.codePointAt(index))) {
                lenNow = index - noRepeat.get(s.codePointAt(index));
            }

            maxLen = Math.max(lenNow, maxLen);
            noRepeat.put(s.codePointAt(index), index);
        }
                    
        return maxLen;
    }

    /*
    Problem 4 Median of two sorted array:
    There are two sorted arrays nums1 and nums2 of size m and n respectively. Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
     */
    //Due to the time complexity limit, we should choose the most efficient sort Algorithm, because of two sorted array, we should choose merge sort
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] result = new int[nums1.length + nums2.length];
        //Merge sort part
        int m = 0, n = 0, len = 0;
        while (m < nums1.length && n < nums2.length) {
            if (nums1[m] <= nums2[n]) {
                result[len++] = nums1[m++];
            }
            else {
                result[len++] = nums2[n++];
            }
        }
        while (m < nums1.length) {
            result[len++] = nums1[m++];
        }
        while (n < nums2.length) {
            result[len++] = nums2[n++];
        }
        //Decide whether result length is odd or even
        return result.length % 2 == 0 ? (double)(result[result.length/2] + result[result.length/2 - 1])/2: (double)result[(result.length - 1)/2];
    }

    /*
    Problem 5 Longest Palindromic Substring:
    Given a string S, find the longest palindromic substring in S. You may assume that the maximum length of S is 1000, and there exists one unique longest palindromic substring.
     */
    //Learned from discussion, previous solution makes over time limit, but more clear
    //previous one list all possible substring first and test them from max length to min one, when find palindrome, jump out the loop and return the result
    private int resultStart, resultLen;

    public String longestPalindrome(String s) {
        //if string is null or 1 character, just return itself
        if (s.length() < 2)
            return s;
        //try to extend substring from i as center, try both odd and even situation
        for (int i = 0; i < s.length() - 1; i++) {
            extendResult(s, i, i);
            extendResult(s, i, i + 1);
        }
        return s.substring(resultStart, resultStart + resultLen);
    }

    private void extendResult(String s, int j, int k) {
        //search by move start to the left and end to the right, to find the longest proper substring
        while (j >= 0 && k < s.length() && s.charAt(j) == s.charAt(k)) {
            j--;
            k++;
        }
        //if new proper substring is longer than former record, replace the result record with the new one
        if (resultLen < k - j - 1) {
            resultStart = j + 1;
            resultLen = k - j - 1;
        }
    }

    public static String interleave(String str1, String str2) {
        StringBuilder resultString = new StringBuilder();
        int i = 0;
        while (i < str1.length() && i < str2.length()) {
            resultString.append(str1.charAt(i));
            resultString.append(str2.charAt(i));
        }
        if (i < str2.length()) {
            resultString.append(str2.substring(i));
        }
        if (i < str1.length()) {
            resultString.append(str1.substring(i));
        }
        return resultString.toString();
    }



    /*
    Problem 6 Zigzag conversion:
    The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)

P   A   H   N
A P L S I I G
Y   I   R
And then read line by line: "PAHNAPLSIIGYIR"
Write the code that will take a string and make this conversion given a number of rows:

string convert(string text, int nRows);
convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
     */
    //This problem seems wired at first, put this pattern in several horizontal Strings, it will looks simple
    public String convert(String s, int numRows) {
        if (s.length() <= 1)
        {
            return s;
        }
        //Create number of numRows StringBuffers to store characters
        StringBuffer total[] = new StringBuffer[numRows];
        for (int i = 0; i < numRows; i ++) {
            total[i] = new StringBuffer();
        }
        int index = 0;
        while(index < s.length()) {
            //This is vertical part in the pattern, make sure not over the length
            for (int appendIndex = 0; appendIndex < numRows && index < s.length(); appendIndex ++) {
                //Append one character in the vertical line to different stringBuffers, then go to second for loop
                total[appendIndex].append(s.charAt(index++));
            }
            //Append character in the Slash line form bottom to top to different stringBuffer then go to next while loop
            for (int appendIndex = numRows - 2; appendIndex >=1 && index < s.length(); appendIndex --) {
                total[appendIndex].append(s.charAt(index++));
            }
        }
        //After all append works to numRows StringBuffers, append the first StringBuffer with other ones and get the result
        for (int i = 1; i < total.length; i ++) {
            total[0] = total[0].append(total[i]);
        }

        return total[0].toString();
    }

    /*
    Problem 7 Reverse Integer:
    Reverse digits of an integer.

Example1: x = 123, return 321
Example2: x = -123, return -321
     */
    public int reverse(int x) {
        //Store x into long type variable first, beacuse it will be an memory error if x overflow int range
        long xLong = x;
        if (xLong == 0)
        {
            return x;
        }
        long result = 0;
        int flag = 0;
        if (xLong < 0)
        {
            xLong = -xLong;
            flag = 1;
        }
        while (xLong != 0)
        {
            // Do not worry about make the full range of result at once
            result *= 10;
            // Always confirm that the added number now is a digit
            result += xLong % 10;
            xLong /= 10;
        }
        // Accorrding to the problem description, if x overflow, return 0
        if (result > Integer.MAX_VALUE)
        {
            return 0;
        }
        else if (flag == 1)
        {
            return -(int)result;
        }
        else {
            return (int)result;
        }
    }

    /*
    Problem 8 String to integer(atoi):
    Implement atoi to convert a string to an integer.

Hint: Carefully consider all possible input cases. If you want a challenge, please do not see below and ask yourself what are the possible input cases.

Notes: It is intended for this problem to be specified vaguely (ie, no given input specs). You are responsible to gather all the input requirements up front.
     */

    /*
    The whole conditions include:
    1. if str is null, return 0;
    2. Except the first character is '+' or '-', Fetch the number until met the character not a number
       char to number should be int num = '6' - '0' because of ASCII
    3. if result larger tha Integer.MAX_VALUE or smaller than Integer_MIN_VALUE should return MAX_VALUE or MIN_VALUE
    4. The blank ' ' before the first character should be ignored and blank after character should be ended
     */
    public int myAtoi(String str) {
        //if str is null, return 0, judge it by the length not equal to "", because null used to judge if use this object
        if (str.length() == 0){
            return 0;
        }
        long result = 0;
        int index = 0;
        boolean firstNum = true;
        //Ignore all blanks before the first available character
        while (str.charAt(index) == ' ' && index < str.length())
        {
                index ++;
        }
        //if the first available character is '+' or '-', give a boolean value to store the status of number
        if (str.charAt(index) == '+' || str.charAt(index) == '-') {
            firstNum = str.charAt(index) == '+';
            index ++;
        }
        while (index < str.length()) {
            //if met the non-number character, break the loop
            if (str.charAt(index) - '0' < 0 || str.charAt(index) - '0' > 9) {
                break;
            }
            /*Judge MAX_VALUE and MIN_VALUE here because Max is 2147483647 and Min is -2147483648, it's not the same
            and before the final return, only one result shown, cannot judge for two conditions once
            */
            if (result > Integer.MAX_VALUE/10 || (result == Integer.MAX_VALUE/10 && str.charAt(index) - '0' > Integer.MAX_VALUE % 10)){
                return firstNum ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            //Similar to reverse a number in Math way
            result = result*10 + (str.charAt(index) - '0');
            index ++;
        }
        return firstNum ? (int)result : -(int)result;
    }


    /*
    Problem 9 Palindrome Number:
    Determine whether an integer is a palindrome. Do this without extra space.
     */
    //Palindrome means that the parameter is symmetrical
    public boolean isPalindrome(int x) {
        // if x is overflowed
        long xLong = x;
        if (xLong >= Integer.MAX_VALUE || xLong <= Integer.MIN_VALUE) {
            return false;
        }
        /*
        if x is less than 0, it must not be palindrome
        if x is times by 10 the num will be length - 1, and 10 times must not be palindrome
        a special case is that 0 is 0 time of 10, and 0 is palindrome
         */
        if (x < 0 || (x != 0 && x % 10 == 0)) {
            return false;
        }
        //if x is one digit and it bigger than 0, it must be palindrome
        else if (x < 10) {
            return true;
        }
        //make num as half length of x and be reverse order
        int num = 0;
        while (num < x ) {
            num *= 10;
            num += x % 10;
            x /= 10;
        }

        //if the length of x is even the result depends on the first part, and if it's odd number, depends on the second part
        return (num == x || num/10 == x);
    }

    /*
    Problem 10 Regular Expression Matching:
    Implement regular expression matching with support for '.' and '*'.

'.' Matches any single character.
'*' Matches zero or more of the preceding element.

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)

Some examples:
isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa", "a*") → true
isMatch("aa", ".*") → true
isMatch("ab", ".*") → true
isMatch("aab", "c*a*b") → true
     */
//Copied from discussion, not exactly undertand the while part, review at 2nd tire or more
        public boolean isMatch(String s, String p) {
            //If p has no element, decide by s.length()
            if (p.isEmpty()) {
                return s.isEmpty();
            }
            //If p has only one elements or the second is not support by '*'
            if (p.length() == 1 || p.charAt(1) != '*') {
                //If s is empty or not support by . or just not equal to each other, return false
                if (s.isEmpty() || (p.charAt(0) != '.' && p.charAt(0) != s.charAt(0))) {
                    return false;
                } else {
                    //Recursion the substring beside the math character
                    return isMatch(s.substring(1), p.substring(1));
                }
            }

            //Main part, keep recursive until met two conditions before
            //Ignore s.length() due to if so, goto the last return and met the first two conditions at next recursive, also equal to each other at the indicated index or supported by '.'
            while (!s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.')) {
                //Because p.length() is bigger than 2, reach here due to skip first two conditions so of course math s with substring(2)
                if (isMatch(s, p.substring(2))) {
                    return true;
                }
                //Make s forward one character
                s = s.substring(1);
            }
            //If not match all conditions before, keep matching s with p.substring(2)
            return isMatch(s, p.substring(2));
        }



}