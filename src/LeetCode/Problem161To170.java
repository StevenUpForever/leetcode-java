package LeetCode;

/**
 * Created by ChengzhiJia on 6/4/16.
 */
public class Problem161To170 {

    /*
    161. One Edit Distance
    Given two strings S and T, determine if they are both one edit distance apart.
     */
    public boolean isOneEditDistance(String s, String t) {
        int len1 = s.length(), len2 = t.length();
        if (Math.abs(len1 - len2) > 1) return false;
        for (int i = 0; i < Math.min(len1, len2); i++) {
            if (s.charAt(i) != t.charAt(i)) {
                if (len1 == len2) return s.substring(i + 1).equals(t.substring(i + 1));
                else if (len1 < len2) return s.substring(i).equals(t.substring(i + 1));
                else return s.substring(i + 1).equals(t.substring(i));
            }
        }
        return Math.abs(len1 - len2) == 1;
    }

    /*
    162. Find Peak Element
    A peak element is an element that is greater than its neighbors.

Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.

The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.

You may imagine that num[-1] = num[n] = -∞.

For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.
     */
    public int findPeakElement(int[] nums) {
        return searchPeak(nums, 0, nums.length - 1);
    }

    private int searchPeak(int[] nums, int start, int end) {
        if (start == end) return start;
        int middle = (start + end)/2;
        if (nums[middle] < nums[middle + 1]) return searchPeak(nums, middle + 1, end);
        else return searchPeak(nums, start, middle);
    }

    /*
    168. Excel Sheet Column Title
    Given a positive integer, return its corresponding column title as appear in an Excel sheet.

For example:

    1 -> A
    2 -> B
    3 -> C
    ...
    26 -> Z
    27 -> AA
    28 -> AB
     */
    public String convertToTitle(int n) {
        StringBuffer builder = new StringBuffer();
        while (n > 0) {
            builder.append((char)('A' + (--n)%26)); //Due to 'A' - 'A' = 0 but 'A' need represent 1, no good to insert new char at 0 here, due to will cause extra O(n) time complexity
            n/=26;
        }
        return builder.reverse().toString(); //Due to higer digit is append after lower one, need to call reverse() once
    }

}
