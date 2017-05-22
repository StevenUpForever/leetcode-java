package Problem71To80;

public class Edit_Distance {

    /**
     * 72. Edit Distance
     * Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2. (each operation is counted as 1 step.)

     You have the following 3 operations permitted on a word:

     a) Insert a character
     b) Delete a character
     c) Replace a character
     */

    /**
     * Solution 1: 2D DP
     * We consider this problem from base case, when word1 and word2 are all "" or one character, every time we check if current subString(word1) and subString(word2) need how many min fixes to became same, we can infer that from
     *      1. how to modify current characters word1(i) and word2(j)
     *      2. how many fixes of prefix length as (i-1, j) (i-1, j-1), (i, j-1)
     * M is a dp matrix of M[word1.len + 1][word2.len + 1] have base case when word1 = "" and word2 = ""
     * Base case: M[0][0] = 0, as when word1 == "", word2 = "", no need to convert
     * Induction rule: for current length i of word1 and j of word2
     *      1. if word1[i] == word2[j], M[i][j] = M[i - 1][j - 1]
     *      2. else M[i][j] = min(M[i - 1][j - 1] (replace current char), M[i][j - 1](delete current char), M[i - 1][j](add current char which equals to word2[j])) + 1 need add one step (any of insert, delete or replace)
     *      return M[len1][len2]
     *
     * Sample matrix as below:
     *    "" a c e d f
     * "" 0  1 2 3 4 5 (when word1 or word2 == "", need as least current length steps to convert)
     * c  1  1 1 2 3 4
     * e  2  2 2 1 2 3
     * g  3  3 3 2 2 3
     * b  4  4 4 3 3 3
     * d  5  5 5 4 3 4
     *
     * Time: O(mn)
     * Space: O(mn)
     */

    public int minDistanceS1(String word1, String word2) {
        char[] chars1 = word1.toCharArray(), chars2 = word2.toCharArray();
        int[][] dp = new int[chars1.length + 1][chars2.length + 1];
        //i and j is index + 1 in chars1 and chars2
        for (int i = 0; i <= chars1.length; i++) {
            for (int j = 0; j <= chars2.length; j++) {
                if (i == 0) dp[i][j] = j;
                else if (j == 0) dp[i][j] = i;
                else {
                    //Induction rule as explained above
                    if (chars1[i - 1] == chars2[j - 1]) dp[i][j] = dp[i - 1][j - 1];
                    else dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                }
            }
        }
        return dp[chars1.length][chars2.length];
    }

    /**
     * Solution 2: Space Optimized 2D DP
     * Basic idea is same as solution 1, due we usually only need M[i - 1][j - 1], M[i][j - 1], M[i - 1][j], So we could use one variable to represent M[i - 1][j - 1]
     * ***(array value will later added to  M[i][j - 1] or  M[i - 1][j] depends on column array or row array)***
     * Use 1d array, array[i] represent the dp value of current column or row
     *
     * Time: O(mn)
     * Space: O(m or n)
     */

    public int minDistanceS2(String word1, String word2) {
        char[] chars1 = word1.toCharArray(), chars2 = word2.toCharArray();
        int[] dp = new int[chars2.length + 1];
        //i and j is index + 1 in chars1 and chars2
        int topLeft = 0;
        for (int i = 0; i <= chars1.length; i++) {
            for (int j = 0; j <= chars2.length; j++) {
                if (i == 0) dp[j] = j;
                else if (j == 0) dp[0] = i;
                else {
                    int temp = dp[j];
                    //Induction rule as explained above
                    if (chars1[i - 1] == chars2[j - 1]) dp[j] = topLeft;
                    else dp[j] = Math.min(Math.min(dp[j - 1], dp[j]), topLeft) + 1;
                    topLeft = temp;
                }
            }
        }
        return dp[chars2.length];
    }

}
