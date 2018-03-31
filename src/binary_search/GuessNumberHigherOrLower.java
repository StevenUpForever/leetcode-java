package binary_search;

public class GuessNumberHigherOrLower extends GuessGame {

    //TAG: Google
    //TAG: Binary search
    //Difficulty: Easy

    /**
     * 374. Guess Number Higher or Lower
     * We are playing the Guess Game. The game is as follows:

     I pick a number from 1 to n. You have to guess which number I picked.

     Every time you guess wrong, I'll tell you whether the number is higher or lower.

     You call a pre-defined API guess(int num) which returns 3 possible results (-1, 1, or 0):

     -1 : My number is lower
     1 : My number is higher
     0 : Congrats! You got it!
     Example:
     n = 10, I pick 6.

     Return 6.
     */

    /**
     * Solution:
     * Binary search
     * Start from mid of 1 and n, do binary search
     *
     * Time: O(logn)
     * Space: O(1)
     */

    public int guessNumber(int n) {
        int l = 1, r = n;
        while (l <= r) {
            int mid = l + (r - l)/2;
            if (guess(mid) == 0) return mid;
            else if (guess(mid) == -1) r = mid - 1;
            else l = mid + 1;
        }
        return -1;
    }

}

class GuessGame {

    protected int n;

    /* The guess API is defined in the parent class binary_search.GuessGame.
   @param num, your guess
   @return -1 if my number is lower, 1 if my number is higher, otherwise return 0
      int guess(int num); */
    int guess(int num) {
        if (num == n) return 0;
        else if (num < n) return -1;
        else return 1;
    }

}
