package LeetCode;

/**
 * Created by ChengzhiJia on 6/23/16.
 */
public class Problem121To130 {

    /*
    121. Best Time to Buy and Sell Stock
    Say you have an array for which the ith element is the price of a given stock on day i.

If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), design an algorithm to find the maximum profit.

Example 1:
Input: [7, 1, 5, 3, 6, 4]
Output: 5

max. difference = 6-1 = 5 (not 7-1 = 6, as selling price needs to be larger than buying price)
Example 2:
Input: [7, 6, 4, 3, 1]
Output: 0

In this case, no transaction is done, i.e. max profit = 0.
     */
    public int maxProfit(int[] prices) {
        int res = 0;
        if (prices.length == 0) return res;
        int min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            res = Math.max(prices[i] - min, res);
            min = Math.min(prices[i], min);
        }
        return res;
    }

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
