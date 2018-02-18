package string.rainbow_sort;

public class ReverseVowelsOfAString {

    //TAG: Google
    //TAG: string
    //TAG: rainbow sort
    //Difficulty: Easy

    /**
     * 345. Reverse Vowels of a String
     * Write a function that takes a string as input and reverse only the vowels of a string.

     Example 1:
     Given s = "hello", return "holle".

     Example 2:
     Given s = "leetcode", return "leotcede".

     Note:
     The vowels does not include the letter "y".
     */

    /**
     * Solution:
     * *** Similar to rainbow sort (sort array contains -1, 0, 1 so that put -1 at left and 1 at right) ***
     * set left and right index, if all met vowels, swap and move to middle
     * else if left met vowels, move to wait right to met vowels
     * else if right met vowels or any haven't met vowels, move left
     *
     * Time: O(n)
     * Space: O(1)
     */

    public String reverseVowels(String s) {
        if (s == null || s.length() == 0) return s;
        //Contains upper and lower cases
        char[] chars = s.toCharArray(), vowels = new char[]{'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'};
        int left = 0, right = chars.length - 1;
        while (left < right) {
            if (contains(vowels, chars[left]) && contains(vowels, chars[right])) {
                swap(chars, left, right);
                left++;
                right--;
            } else if (contains(vowels, chars[left])) right--;
            else left++;
        }
        return new String(chars);
    }

    private boolean contains(char[] chars, char c) {
        for (char cur: chars) {
            if (cur == c) return true;
        }
        return false;
    }

    private void swap(char[] chars, int a, int b) {
        char temp = chars[a];
        chars[a] = chars[b];
        chars[b] = temp;
    }

}

