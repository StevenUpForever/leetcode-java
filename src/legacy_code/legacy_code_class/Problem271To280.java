package legacy_code.legacy_code_class;

import java.util.*;

import java.util.HashMap;

/**
 * Created by ChengzhiJia on 3/14/17.
 */
public class Problem271To280 {

    /*
    273. Integer to English Words
    Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 231 - 1.

For example,
123 -> "One Hundred Twenty Three"
12345 -> "Twelve Thousand Three Hundred Forty Five"
1234567 -> "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
Show Hint
Show Company Tags
Show Tags
Show Similar Problems
     */

    /*
    ****************clean code sample***********************
    private final String[] LESS_THAN_20 = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
private final String[] TENS = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
private final String[] THOUSANDS = {"", "Thousand", "Million", "Billion"};

public String numberToWords(int num) {
    if (num == 0) return "Zero";

    int i = 0;
    String words = "";

    while (num > 0) {
        if (num % 1000 != 0)
    	    words = helper(num % 1000) +THOUSANDS[i] + " " + words;
    	num /= 1000;
    	i++;
    }

    return words.trim();
}

private String helper(int num) {
    if (num == 0)
        return "";
    else if (num < 20)
        return LESS_THAN_20[num] + " ";
    else if (num < 100)
        return TENS[num / 10] + " " + helper(num % 10);
    else
        return LESS_THAN_20[num / 100] + " Hundred " + helper(num % 100);
}
     */


    public String numberToWords(int num) {
        if (num == 0) return "Zero";
        List<String> list = new ArrayList<>();
        while (num != 0) {
            list.add(convert3DigitsNum(num % 1000));
            num /= 1000;
        }
        StringBuilder builder = new StringBuilder();
        for (int i = list.size() - 1; i >= 0; i--) {
            String str = list.get(i);
            builder.append(str.equals("") ? "" : str + carryStr[i]);
        }
        int resLen = builder.length();
        if (resLen > 0 && builder.charAt(resLen - 1) == ' ') builder.deleteCharAt(resLen - 1);
        return builder.toString();
    }

    String[] carryStr = {"", " Thousand ", " Million ", " Billion "};
    String[] oneDigit = {"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
    String[] twoDigits = {"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    String[] multiDigits = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

    private String convert3DigitsNum(int num) {
        StringBuilder builder = new StringBuilder();
        builder.append(num);
        int len = builder.length();
        int index = len - 1;
        StringBuilder res = new StringBuilder();
        while (index >= 0) {
            int curNum = builder.charAt(len - 1 - index) - '0';
            if (curNum != 0) {
                switch (index) {
                    case 2:
                        res.append(oneDigit[curNum] + " Hundred ");
                        break;
                    case 1:
                        if (curNum == 1) return res.append(twoDigits[builder.charAt(len - 1) - '0']).toString();
                        else res.append(multiDigits[curNum] + " ");
                        break;
                    default:
                         res.append(oneDigit[curNum]);
                         break;
                }
            }
            index--;
        }
        int resLen = res.length();
        if (resLen > 0 && res.charAt(resLen - 1) == ' ') res.deleteCharAt(resLen - 1);
        return res.toString();
    }

    /*
    277. Find the Celebrity
    Suppose you are at a party with n people (labeled from 0 to n - 1) and among them, there may exist one celebrity. The definition of a celebrity is that all the other n - 1 people know him/her but he/she does not know any of them.

Now you want to find out who the celebrity is or verify that there is not one. The only thing you are allowed to do is to ask questions like: "Hi, A. Do you know B?" to get information of whether A knows B. You need to find out the celebrity (or verify there is not one) by asking as few questions as possible (in the asymptotic sense).

You are given a helper function bool knows(a, b) which tells you whether A knows B. Implement a function int findCelebrity(n), your function should minimize the number of calls to knows.

Note: There will be exactly one celebrity if he/she is in the party. Return the celebrity's label if there is a celebrity in the party. If there is no celebrity, return -1.
     */
    /*
    answer from leetcode
    Approach: first find the result, then verify if it's the real one
     */
            //    public int findCelebrity(int n) {
            //        if (n == 0) return -1;
            //        int res = 0;
            //        //Because result know no one, and others must know this result, so if there's one, it must be this loop
            //        for (int i = 1; i < n; i++) {
            //            if (knows(res, i)) res = i;
            //        }
            //        for (int i = 0; i < n; i++) {
            //            if (i != res && (knows(res, i) || !knows(i, res))) return -1;
            //        }
            //        return res;
            //    }

    /*
    278. First Bad Version
    You are a product manager and currently leading a team to develop a new product. Unfortunately, the latest version of your product fails the quality check. Since each version is developed based on the previous version, all the versions after a bad version are also bad.

Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the following ones to be bad.

You are given an API bool isBadVersion(version) which will return whether version is bad. Implement a function to find the first bad version. You should minimize the number of calls to the API.
     */
//    public int firstBadVersion(int n) {
//        if (n < 1) return 0;
//        int start = 1;
//        while (start < n - 1) {
//            int mid = start + (n - start)/2;
//            if (isBadVersion(mid)) n = mid;
//            else start = mid;
//        }
//        return isBadVersion(start) ? start : n;
//    }
}
