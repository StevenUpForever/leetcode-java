import java.util.*;

/**
 * Created by ChengzhiJia on 16/2/18.
 */
public class ProblemFrom31To40 {
    /*
    31. Next Permutation
    Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).

The replacement must be in-place, do not allocate extra memory.

Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
1,2,3 → 1,3,2
3,2,1 → 1,2,3
1,1,5 → 1,5,1

public void nextPermutation(int[] nums) {
        if (nums.length < 2) return;
        int index = nums.length - 2;
        outloop: while (index >= 0) {
            if (nums[index] > nums[nums.length - 1]) {
                for (int index2 = index; index2 < nums.length - 1; index2 ++) {
                    if (nums[index2] > nums[index2 + 1]) {
                        int change = nums[index2];
                        nums[index2] = nums[index2 + 1];
                        nums[index2 + 1] = change;
                    }
                }
                index--;
            }
            else {
                for (int index3 = index + 1; index3 < nums.length; index3++) {
                    if (nums[index3] > nums[index]) {
                        int change = nums[index];
                        nums[index] = nums[index3];
                        nums[index3] = change;
                        break outloop;
                    }
                }
            }
        }
    }
     */
    public void nextPermutation(int[] num) {
        int n=num.length;
        if(n<2) return;
        int index=n-1;
        while(index>0){
            if(num[index-1]<num[index]) break;
            index--;
        }
        if(index==0) reverseSort(num,0,n-1);
        else{
            int val=num[index-1], j=n-1;
            while(j>=index){
                if(num[j]>val) break;
                j--;
            }
            swap(num,j,index-1);
            reverseSort(num,index,n-1);
        }
    }

    private void swap(int[] num, int i, int j){
        int temp=num[i];
        num[i]=num[j];
        num[j]=temp;
    }

    private void reverseSort(int[] num, int start, int end){
        if(start>end) return;
        for(int i=start;i<=(end+start)/2;i++)
            swap(num,i,start+end-i);
    }

    /*
    32. Longest Valid Parentheses
    Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.

For "(()", the longest valid parentheses substring is "()", which has length = 2.

Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.
     */
    public int longestValidParentheses(String s) {
        LinkedList<Integer> stack = new LinkedList<>();
        int result = 0;
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ')' && stack.size() > 1 && s.charAt(stack.peek()) == '(') {
                stack.pop();
                result = Math.max(result, i - stack.peek());
            } else {
                stack.push(i);
            }
        }
        return result;
    }

    /*
    33. Search in Rotated Sorted Array
    Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

You are given a target value to search. If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.
     */
    public int search(int[] nums, int target) {
        if (nums.length == 0) return -1;
        int index = 0;
        if (nums[0] <= target) {
            while (index < nums.length) {
                if (nums[index] < target) index++;
                else if (nums[index] == target) return index;
                else return -1;
            }
        }
        else {
            index = nums.length - 1;
            while (index >= 0) {
                if (nums[index] > target) index--;
                else if (nums[index] == target) return index;
                else return -1;
            }
        }
        return -1;
    }

    /*
    34. Search for a Range
    Given a sorted array of integers, find the starting and ending position of a given target value.

Your algorithm's runtime complexity must be in the order of O(log n).

If the target is not found in the array, return [-1, -1].

For example,
Given [5, 7, 7, 8, 8, 10] and target value 8,
return [3, 4].
     */
        public int[] searchRange(int[] nums, int target) {
            int[] result = {-1, -1};
            if (nums.length == 0) return result;
            int index1 = 0, index2 = 0;
            if (nums[0] <= target && nums[nums.length - 1] >= target) {
                while (index1 < nums.length && nums[index1] <= target) {
                    if (nums[index1] < target) index1++;
                    else if (nums[index1] == target) {
                        index2++;
                        index1++;
                    }
                }
                if (index2 != 0) result = new int[]{index1 - index2, index1 - 1};
            }
            return result;
        }

    /*
    35. Search Insert Position
    Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

You may assume no duplicates in the array.

Here are few examples.
[1,3,5,6], 5 → 2
[1,3,5,6], 2 → 1
[1,3,5,6], 7 → 4
[1,3,5,6], 0 → 0
     */
    public int searchInsert(int[] nums, int target) {
        int index = 0;
        while (index < nums.length) {
            if (nums[index] < target) index++;
            else return index;
        }
        return index;
    }

    /*
    36. Valid Sudoku
    Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.

The Sudoku board could be partially filled, where empty cells are filled with the character '.'.


A partially filled sudoku which is valid.

Note:
A valid Sudoku board (partially filled) is not necessarily solvable. Only the filled cells need to be validated.
     */
    public boolean isValidSudoku(char[][] board) {
        for(int i = 0; i < 9; i++) {
            if(!isValid(board, i, i, 0, 8) || !isValid(board, 0, 8, i, i) || !isValid(board, i / 3 * 3, i / 3 * 3 + 2, i % 3 * 3, i % 3 * 3 + 2)) return false;
        }

        return true;
    }

    private boolean isValid(char[][] board, int xStart, int xEnd, int yStart, int yEnd) {
        HashSet set = new HashSet();
        for(int x = xStart; x <= xEnd; x++) {
            for(int y = yStart; y <= yEnd; y++) {
                if(board[x][y] != '.' && !set.add(board[x][y])) return false;
            }
        }
        return true;
    }

    /*
    37. Sudoku Solver
    Write a program to solve a Sudoku puzzle by filling the empty cells.

Empty cells are indicated by the character '.'.

You may assume that there will be only one unique solution.


A sudoku puzzle...


...and its solution numbers marked in red.
     */
    public void solveSudoku(char[][] board) {
        if(board == null || board.length == 0)
            return;
        solve(board);
    }

    private boolean solve(char[][] board){
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(board[i][j] == '.'){
                    for(char c = '1'; c <= '9'; c++){//trial. Try 1 through 9 for each cell
                        if(isValid(board, i, j, c)){
                            board[i][j] = c; //Put c for this cell

                            if(solve(board))
                                return true; //If it's the solution return true
                            else
                                board[i][j] = '.'; //Otherwise go back
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValid(char[][] board, int i, int j, char c){
        //Check colum
        for(int row = 0; row < 9; row++)
            if(board[row][j] == c)
                return false;

        //Check row
        for(int col = 0; col < 9; col++)
            if(board[i][col] == c)
                return false;

        //Check 3 x 3 block
        for(int row = (i / 3) * 3; row < (i / 3) * 3 + 3; row++)
            for(int col = (j / 3) * 3; col < (j / 3) * 3 + 3; col++)
                if(board[row][col] == c)
                    return false;
        return true;
    }

    /*
    38. Count and Say
    The count-and-say sequence is the sequence of integers beginning as follows:
1, 11, 21, 1211, 111221, ...

1 is read off as "one 1" or 11.
11 is read off as "two 1s" or 21.
21 is read off as "one 2, then one 1" or 1211.
Given an integer n, generate the nth sequence.

Note: The sequence of integers will be represented as a string.
     */
    public String countAndSay(int n) {
        if(n <= 0) return "-1";
        String result = "1";

        for(int i = 1; i < n; i ++) {
            result = build(result);
        }
        return result;
    }

    private String build(String result) {
        StringBuilder builder = new StringBuilder();
        int p = 0;
        while(p < result.length()) {
            char val = result.charAt(p);
            int count = 0;

            while(p < result.length() &&
                    result.charAt(p) == val){
                p ++;
                count ++;
            }
            builder.append(String.valueOf(count));
            builder.append(val);
        }
        return builder.toString();
    }

}
