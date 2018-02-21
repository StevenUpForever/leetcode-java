package depth_first_search;

import java.util.Arrays;
import java.util.HashMap;

public class CanIWin {

    //TAG: LinkedIn
    //TAG: DFS
    //Difficulty: Medium

    /**
     * 464. Can I Win
     * In the "100 game," two players take turns adding, to a running total, any integer from 1..10. The player who first causes the running total to reach or exceed 100 wins.

     What if we change the game so that players cannot re-use integers?

     For example, two players might take turns drawing from a common pool of numbers of 1..15 without replacement until they reach a total >= 100.

     Given an integer maxChoosableInteger and another integer desiredTotal, determine if the first player to move can force a win, assuming both players play optimally.

     You can always assume that maxChoosableInteger will not be larger than 20 and desiredTotal will not be larger than 300.

     Example

     Input:
     maxChoosableInteger = 10
     desiredTotal = 11

     Output:
     false

     Explanation:
     No matter which integer the first player choose, the first player will lose.
     The first player can choose an integer from 1 up to 10.
     If the first player choose 1, the second player can only choose integers from 2 up to 10.
     The second player will win by choosing 10 and get a total = 11, which is >= desiredTotal.
     Same with other integers chosen by the first player, the second player will always win.
     */

    /**
     * Solution: DFS
     * Base case: when total <= 0, base case represent the other player win
     * recursion rule: loop the number, pick one for one player, recursion total - num,
     * if recursion is false, means the other player lose, set (num, true) pair, return true
     *
     * Time: O(n!)
     * Space: O(n)
     */

    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if (desiredTotal <= 0) return true;
        //Sum of equal difference series less than desiredTotal, will never reach this number, couldn't win
        if (maxChoosableInteger*(maxChoosableInteger + 1)/2 < desiredTotal) return false;
        return canIWinHelper(new HashMap<>(), desiredTotal, new int[maxChoosableInteger]);
    }

    private boolean canIWinHelper(HashMap<String, Boolean> map, int leftTotal, int[] visited) {
        //Here's a little trick, the recursion step is for to verify if the other player win, so if leftTotal <= 0, means the other player lose, return false
        if (leftTotal <= 0) return false;
        String key = Arrays.toString(visited);
        if (map.containsKey(key)) return map.get(key);
        for (int i = 0; i < visited.length; i++) {
            //Try to give the other player every number which not been used
            if (visited[i] == 0) {
                visited[i] = 1;
                if (!canIWinHelper(map, leftTotal - i - 1, visited)) {
                    map.put(key, true);
                    //Reset status here before return
                    visited[i] = 0;
                    return true;
                }
                //if the other player could win, also reset the status
                visited[i] = 0;
            }
        }
        map.put(key, false);
        return false;
    }

}
