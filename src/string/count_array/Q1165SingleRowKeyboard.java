package string.count_array;

public class Q1165SingleRowKeyboard {

  //Difficulty: Easy
  //TAG: String

  /**
   * 1165. Single-Row Keyboard
   * There is a special keyboard with all keys in a single row.
   *
   * Given a string keyboard of length 26 indicating the layout of the keyboard (indexed from 0 to 25), initially your finger is at index 0. To type a character, you have to move your finger to the index of the desired character. The time taken to move your finger from index i to index j is |i - j|.
   *
   * You want to type a string word. Write a function to calculate how much time it takes to type it with one finger.
   *
   *
   *
   * Example 1:
   *
   * Input: keyboard = "abcdefghijklmnopqrstuvwxyz", word = "cba"
   * Output: 4
   * Explanation: The index moves from 0 to 2 to write 'c' then to 1 to write 'b' then to 0 again to write 'a'.
   * Total time = 2 + 1 + 1 = 4.
   * Example 2:
   *
   * Input: keyboard = "pqrstuvwxyzabcdefghijklmno", word = "leetcode"
   * Output: 73
   *
   *
   * Constraints:
   *
   * keyboard.length == 26
   * keyboard contains each English lowercase letter exactly once in some order.
   * 1 <= word.length <= 10^4
   * word[i] is an English lowercase letter.
   */

  /*
  Solution:

  Put char in 26 int array, arr[char - 'a'] = i so for each char place, number i represent index in keyboard
  and calculate

  Time: O(m + n)
  Space: O(m)
   */

  public int calculateTime(String keyboard, String word) {
    int[] pad = new int[26];
    for (int i = 0; i < keyboard.length(); i++) {
      char c = keyboard.charAt(i);
      pad[c - 'a'] = i;
    }
    int index = 0, count = 0;
    for (int i = 0; i < word.length(); i++) {
      char c = word.charAt(i);
      int pos = pad[c - 'a'];
      count += Math.abs(pos - index);
      index = pos;
    }
    return count;
  }

}
