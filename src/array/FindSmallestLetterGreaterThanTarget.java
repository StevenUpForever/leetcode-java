package array;

public class FindSmallestLetterGreaterThanTarget {

    //TAG: LinkedIn
    //TAG: array
    //Difficulty: Easy

    /**
     * 744. Find Smallest Letter Greater Than Target
     * Given a list of sorted characters letters containing only lowercase letters, and given a target letter target, find the smallest element in the list that is larger than the given target.

     Letters also wrap around. For example, if the target is target = 'z' and letters = ['a', 'b'], the answer is 'a'.

     Examples:
     Input:
     letters = ["c", "f", "j"]
     target = "a"
     Output: "c"

     Input:
     letters = ["c", "f", "j"]
     target = "c"
     Output: "f"

     Input:
     letters = ["c", "f", "j"]
     target = "d"
     Output: "f"

     Input:
     letters = ["c", "f", "j"]
     target = "g"
     Output: "j"

     Input:
     letters = ["c", "f", "j"]
     target = "j"
     Output: "c"

     Input:
     letters = ["c", "f", "j"]
     target = "k"
     Output: "c"
     Note:
     letters has a length in range [2, 10000].
     letters consists of lowercase letters, and contains at least 2 unique letters.
     target is a lowercase letter.
     */

    /**
     * Solution:
     * loop letters from index 1 to the end, when letters[i] > target && letters[i - 1] <= target i is the letter
     * if first char larger than target or last char smaller than target, the loop will end, and return letters[0]
     *
     * Time: O(n)
     * Space: O(1)
     */

    public char nextGreatestLetter(char[] letters, char target) {
        if (letters == null || letters.length == 0) return target;
        //Ignore first char or last char smaller than target
        for (int i = 1; i < letters.length; i++) {
            if (letters[i] > target && letters[i - 1] <= target)
                return letters[i];
        }
        //This condition contains when first char is larger than target or last char is smaller than target, all fits
        return letters[0];
    }

}
