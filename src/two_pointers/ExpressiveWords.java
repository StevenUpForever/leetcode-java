package two_pointers;

public class ExpressiveWords {

    //TAG: Google
    //TAG: two pointers
    //Difficulty: medium

    /**
     * 809. Expressive Words
     *
     Sometimes people repeat letters to represent extra feeling, such as "hello" -> "heeellooo", "hi" -> "hiiii".  Here, we have groups, of adjacent letters that are all the same character, and adjacent characters to the group are different.  A group is extended if that group is length 3 or more, so "e" and "o" would be extended in the first example, and "i" would be extended in the second example.  As another example, the groups of "abbcccaaaa" would be "a", "bb", "ccc", and "aaaa"; and "ccc" and "aaaa" are the extended groups of that string.

     For some given string S, a query word is stretchy if it can be made to be equal to S by extending some groups.  Formally, we are allowed to repeatedly choose a group (as defined above) of characters c, and add some number of the same character c to it so that the length of the group is 3 or more.  Note that we cannot extend a group of size one like "h" to a group of size two like "hh" - all extensions must leave the group extended - ie., at least 3 characters long.

     Given a list of query words, return the number of words that are stretchy.

     Example:
     Input:
     S = "heeellooo"
     words = ["hello", "hi", "helo"]
     Output: 1
     Explanation:
     We can extend "e" and "o" in the word "hello" to get "heeellooo".
     We can't extend "helo" to get "heeellooo" because the group "ll" is not extended.
     Notes:

     0 <= len(S) <= 100.
     0 <= len(words) <= 100.
     0 <= len(words[i]) <= 100.
     S and all words in words consist only of lowercase letters
     */

    public int expressiveWords(String S, String[] words) {
        int res = 0;
        for (String W : words) if (check(S, W)) res++;
        return res;
    }
    public boolean check(String S, String W) {
        int i = 0, j = 0, n = S.length(), m = W.length();
        while (i < n && j < m) {
            //if char is equal, matched, and move two pointers
            while (i < n && j < m && S.charAt(i) == W.charAt(j)) {i++; j++;}
            if (i == n && j == m) return true;
            //if not matched but i is repeat, skip all repeat chars, char(i) may the extension
            while (i > 0 && i < n && S.charAt(i) == S.charAt(i - 1)) i++;
            //Verify if i is valid extension, if not, return false
            if (i < 3 || S.charAt(i - 1) != S.charAt(i - 2) || S.charAt(i - 2) != S.charAt(i - 3)) return false;
        }
        return i == n && j == m;
    }

}
