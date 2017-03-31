package LeetCode;

import java.lang.reflect.Array;
import java.util.*;

public class Problem11To20 {

    /*
    Problem 11 Container With Most Water:
    Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.

Note: You may not slant the container.
     */
    /*
    Former solution:
    public int maxArea(int[] height) {
        int maxArea = 0;
        int index1 = 0, index2 = 1;
        //for (int index1 = 0; index1 < height.length - 1; index1 ++) {
            while (index1 < height.length - 1 && index2 < height.length) {
                maxArea = Math.max(maxArea, (index2 - index1) * Math.min(height[index1], height[index2]));
                index2 ++;
                if (index2 == height.length - 1) {
                    index1 ++;
                    index2 = index1 + 1;
                }
            }
        return maxArea;
    }
    Still over time limit
     */
    //先考虑的应该是运算时间而不是时间复杂度,所以虽然减少了一层循环,减少了O(n),但是运算时间并没有改变,有很多次无效运算
    //主体部分是一样的,用了Math.max, Math.min, 重点思路在于并不是暴力比较,而是将两点放在两边,从最大的底边开始比较,那边的height小那边就向对方移动,然后去max再进行比较,主要是找到两个height最接近且最大的点,只比较一次loop即可
    public int maxArea(int[] height) {
        int maxArea = 0;
        int index1 = 0, index2 = height.length - 1;
            while (index1 < index2) {
                maxArea = Math.max(maxArea, (index2 - index1) * Math.min(height[index1], height[index2]));
                if (height[index1] < height[index2]) {
                    index1 ++;
                }
                else {
                    index2 --;
                }
            }
        return maxArea;
    }

//    Problem 12 Integer to Roman:
//    Given an integer, convert it to a roman numeral.
//    Input is guaranteed to be within the range from 1 to 3999.
//    public String intToRoman(int num) { Memory limit exceed
//        StringBuilder resultString = new StringBuilder();
//        resultString.append(romanNumbers(num/1000 % 10, "M", "", ""));
//        resultString.append(romanNumbers(num/100 % 10, "C", "D", "M"));
//        resultString.append(romanNumbers(num/10 % 10, "X", "L", "C"));
//        resultString.append(romanNumbers(num % 10, "I", "V", "X"));
//        return resultString.toString();
//    }
//    private String romanNumbers(int input, String one, String five, String ten) {
//        if (input == 0) {
//            return "";
//        }
//        else if (input > 0 && input < 4) {
//            StringBuilder result = new StringBuilder();
//            while (input > 0) {
//                result.append(one);
//                input --;
//            }
//            return result.toString();
//        }
//        else if (input == 4) {
//            return one + five;
//        }
//        else if (input > 4 && input < 9) {
//            StringBuilder result = new StringBuilder();
//            result.append(five);
//            while (input - 5 > 0) {
//                result.append(one);
//            }
//            return result.toString();
//        }
//        else if (input == 9) {
//            return one + ten;
//        }
//        else {
//            return "";
//        }
//    }
    public String intToRoman(int num) {
        String thousand[] = {"", "M", "MM", "MMM"};
        String hundred[] = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String ten[] = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String one[] = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        return thousand[num/1000%10] + hundred[num/100%10] + ten[num/10%10] + one[num%10];
    }

    /*Problem 13 Roman to Integer:
    Given a roman numeral, convert it to an integer.

Input is guaranteed to be within the range from 1 to 3999.
     */
    public int romanToInt(String s) {
        if (s.length() == 0) {
            return 0;
        }
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        int result = map.get(s.charAt(s.length() - 1));
        int tempNum = result;
        for (int index = s.length() - 2; index >= 0; index --) {
            int innerTempNum = map.get(s.charAt(index));
            if (innerTempNum >= tempNum) {
                result += innerTempNum;
            }
            else {
                result -= innerTempNum;
            }
            tempNum = innerTempNum;
        }
        return result;
    }

    /*Problem 14 Longest comment prefix:
    Write a function to find the longest common prefix string amongst an array of strings.
     */
    public String longestCommonPrefix(String[] strs) {
        //if array strs has no elements, return ""
        if (strs.length == 0){
            return "";
        }
        //find the min length string in strs as minStr
        int minLen = strs[0].length();
        String minStr = strs[0];
        for (String str: strs) {
            if (str.length() == 0) {
                return "";
            }
            if (minLen > Math.min(str.length(), minLen)) {
                minLen = Math.min(str.length(), minLen);
                minStr = str;
            }
        }
        //Shrink the minStr by 1 each step to make sure each string in strs contains this substring and also start from 0
        for (String str: strs) {
            while (str.indexOf(minStr) != 0) {
                minStr = minStr.substring(0, minStr.length() - 1);
            }
        }
        return minStr;
    }

    /*
    Problem 15 3Sum:
    Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

Note:
Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ≤ b ≤ c)
The solution set must not contain duplicate triplets.
    For example, given array S = {-1 0 1 2 -1 -4},

    A solution set is:
    (-1, 0, 1)
    (-1, -1, 2)
     */
//    public List<List<Integer>> threeSum(int[] nums) {
//        Arrays.sort(nums);
//        List<List<Integer>> result = new ArrayList<>();
//        for (int index1 = 0; index1 < nums.length - 2; index1 ++) {
//            if (index1 > 0 && nums[index1 - 1] == nums[index1]) continue;
//            int index2 = index1 + 1, index3 = nums.length - 1, temp = -nums[index1];
//            while (index2 < index3) {
//                if (nums[index2] + nums[index3] == temp) {
//                    result.add(Arrays.asList(nums[index1], nums[index2], nums[index3]));
//                    while (index2 < index3 && nums[index2] == nums[index2 + 1]) index2++;
//                    while (index3 > index2 && nums[index3 - 1] == nums[index3]) index3--;
//                    index2 ++;
//                    index3 --;
//                }
//                else if (nums[index2] + nums[index3] > temp) index3--;
//                else index2++;
//            }
//        }
//        return result;
//    }

    //TODO: Another two solutions of 15. 3 sum
    /*
    Approach 1: sort array first, then could run from begin and end to middle to find target
     */
     /*
     Approach 2: run two loops, which outer loop and inner loop are two sum, and inner loop run another two sum, using HashMap
      */

    /*
    Problem 16 3Sum Closest:
    Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target. Return the sum of the three integers. You may assume that each input would have exactly one solution.

    For example, given array S = {-1 2 1 -4}, and target = 1.

    The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
     */
    public int threeSumClosest(int[] nums, int target) {
        if (nums.length < 3) return 0;
        Arrays.sort(nums);
        int result = nums[0] + nums[1] + nums[2], compareNum = Math.abs(result - target);
        for (int index1 = 0; index1 < nums.length - 2; index1 ++) {
            if (index1 > 0 && nums[index1] == nums[index1 - 1]) continue;
            int index2 = index1 + 1, index3 = nums.length - 1;
            while (index2 < index3) {
                int temp = nums[index1] + nums[index2] + nums[index3];
                int tempNum = Math.abs(temp - target);
                if (tempNum < compareNum) {
                    compareNum = tempNum;
                    result = temp;
                }
                while (index2 < index3 && nums[index2] == nums[index2 + 1]) index2++;
                while (index3 > index2 && nums[index3 - 1] == nums[index3]) index3--;
                if (temp < target) index2++;
                else if (temp > target) index3 --;
                else break;
            }
        }
        return result;
    }

    /*
    Problem 17 Letter Combinations of a Phone Number:
    Given a digit string, return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below.



Input:Digit string "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
     */
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits.length() == 0) return result;
        String[] str = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        result.add("");
        for (char c: digits.toCharArray()) {
            int indexC = c - '0';
            List<String> tempList = new ArrayList<>();
            for (char c2: str[indexC].toCharArray()) {
                    for (String resStr: result) {
                        tempList.add(resStr + c2);
                    }
            }
            result = tempList;
        }
        return result;
    }

    /*
    Problem 18 4Sum:
    Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.

Note:
Elements in a quadruplet (a,b,c,d) must be in non-descending order. (ie, a ≤ b ≤ c ≤ d)
The solution set must not contain duplicate quadruplets.
    For example, given array S = {1 0 -1 0 -2 2}, and target = 0.

    A solution set is:
    (-1,  0, 0, 1)
    (-2, -1, 1, 2)
    (-2,  0, 0, 2)
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums.length < 4) return result;
        Arrays.sort(nums);
        for (int index1 = 0; index1 < nums.length - 3; index1 ++) {
            if (index1 > 0 && nums[index1 - 1] == nums[index1]) continue;
            for (int index2 = index1 + 1; index2 < nums.length - 2; index2 ++) {
                if (index2 > index1 + 1 && nums[index2 - 1] == nums[index2]) continue;
                int tempTarget = target - nums[index1] - nums[index2];
                int index3 = index2 + 1, index4 = nums.length - 1;
                while (index3 < index4) {
                    if (nums[index3] + nums[index4] == tempTarget) {
                        result.add(Arrays.asList(nums[index1], nums[index2], nums[index3], nums[index4]));
                        while (index3 < index4 && nums[index3] == nums[index3 + 1]) index3 ++;
                        while (index3 < index4 && nums[index4 - 1] == nums[index4]) index4 --;
                        index3 ++;
                        index4 --;
                    }
                    else if (nums[index3] + nums[index4] < tempTarget) index3++;
                    else index4 --;
                }
            }
        }
        return result;
    }

    /*
    Problem 19 Remove Nth Node From End of List:
    Given a linked list, remove the nth node from the end of list and return its head.

For example,

   Given linked list: 1->2->3->4->5, and n = 2.

   After removing the second node from the end, the linked list becomes 1->2->3->5.
Note:
Given n will always be valid.
Try to do this in one pass.
     */
    private class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        LinkedList<Integer> tempList = new LinkedList<>();
        while (head != null) {
            tempList.add(head.val);
            head = head.next;
        }
        tempList.remove(tempList.size() - n);
        ListNode temp1 = new ListNode(0);
        ListNode temp2 = temp1;
        for (Integer i: tempList) {
            temp1.next = new ListNode(i);
            temp1 = temp1.next;
        }
        return temp2.next;
    }

    /*
    Problem 20 Valid Parentheses:
    Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.\
My code, could pass
 public boolean isValid(String s) {
        if (s.length() < 2) return false;
        StringBuilder resultStr = new StringBuilder(s);
        ArrayList<Character> characterArray = new ArrayList<>(Arrays.asList('{', '[', '(', '}', ']', ')'));
        if (characterArray.indexOf(resultStr.charAt(0)) > 2) return false;
        int index = 0;
        while (index < resultStr.length()){
            int index2 = characterArray.indexOf(resultStr.charAt(index));
            if (index2 > 2 && index > 0) {
                int index3 = characterArray.indexOf(resultStr.charAt(index - 1));
                if (index2 == index3 + 3) {
                    resultStr.delete(index - 1, index + 1);
                    index = index - 1;
                    continue;
                } else return false;
            }
            index ++;
        }
        System.out.print(resultStr);
        return resultStr.length() == 0;
    }
     */
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        for(int i = 0; i<s.length(); i++) {
            if(s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) == '{')
                stack.push(s.charAt(i));
            else if(s.charAt(i) == ')' && !stack.empty() && stack.peek() == '(')
                stack.pop();
            else if(s.charAt(i) == ']' && !stack.empty() && stack.peek() == '[')
                stack.pop();
            else if(s.charAt(i) == '}' && !stack.empty() && stack.peek() == '{')
                stack.pop();
            else
                return false;
        }
        return stack.empty();
    }
}
