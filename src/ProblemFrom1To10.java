import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.*;
import java.util.regex.Matcher;

/*
Problem 1:
 Given an array of integers, find two numbers such that they add up to a specific target number.

The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.

You may assume that each input would have exactly one solution.

Input: numbers={2, 7, 11, 15}, target=9
Output: index1=1, index2=2
 */
public class ProblemFrom1To10 {

    public int[] twoSum(int[] nums, int target) {

        int result[] = new int[2];
        //Pay attention for time limit
        for (int numA = 1; numA < nums.length; numA++) {
            for (int numB = 0; numB < numA; numB++) {
                if (nums[numA] + nums[numB] == target) {
                    if (numA <= numB) {
                        result[0] = numA + 1;//Not zero based
                        result[1] = numB + 1;
                    } else {
                        result[0] = numB + 1;
                        result[1] = numA + 1;
                    }
                }
            }
        }
        return result;
    }

    /*
    Problem 2:
    You are given two linked lists representing two non-negative numbers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
     */
    class ListNode {
        int val;
        ListNode next;
        ListNode(int x){
            val = x;
        }
    }

        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode result = new ListNode(0);
            int nodeSum = 0;
            while (l1 != null || l2 != null)
            {

            }


            return result.next;
        }


    /*
    Problem 3:
    Given a string, find the length of the longest substring without repeating characters. For example, the longest substring without repeating letters for "abcabcbb" is "abc", which the length is 3. For "bbbbb" the longest substring is "b", with the length of 1.
     */
    public int lengthOfLongestSubstring(String s) {

        if (s == null)
        {
            return 0;
        }

        int lenNow = 0, maxLen = 0;
        HashMap<Integer, Integer> noRepeat = new HashMap<>();
        for (int index = 0; index < s.length(); index ++)
        {
            lenNow ++;
            if (noRepeat.containsKey(s.codePointAt(index)) && lenNow > index - noRepeat.get(s.codePointAt(index)))
            {
                lenNow = index - noRepeat.get(s.codePointAt(index));
            }

                maxLen = Math.max(lenNow, maxLen);
                noRepeat.put(s.codePointAt(index), index);
        }
        return maxLen;
    }


    /*
    Problem 6:
    The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)

P   A   H   N
A P L S I I G
Y   I   R
And then read line by line: "PAHNAPLSIIGYIR"
Write the code that will take a string and make this conversion given a number of rows:

string convert(string text, int nRows);
convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
     */
    public String convert(String s, int numRows) {
        if (s.length() <= 1)
        {
            return s;
        }
        StringBuffer result = new StringBuffer();


        return s;
    }

    /*
    Problem 7:
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
    Problem 9:
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






}