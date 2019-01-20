package math.random;

import java.util.*;

public class RandomPickWithWeight {

    //Difficulty: medium
    //TAG: Uber
    //TAG: math
    //TAG: random

    /**
     * 528. Random Pick with Weight
     * Given an array w of positive integers, where w[i] describes the weight of index i, write a function pickIndex which randomly picks an index in proportion to its weight.
     *
     * Note:
     *
     * 1 <= w.length <= 10000
     * 1 <= w[i] <= 10^5
     * pickIndex will be called at most 10000 times.
     * Example 1:
     *
     * Input:
     * ["Solution","pickIndex"]
     * [[[1]],[]]
     * Output: [null,0]
     * Example 2:
     *
     * Input:
     * ["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
     * [[[1,3]],[],[],[],[],[]]
     * Output: [null,0,1,1,1,0]
     * Explanation of Input Syntax:
     *
     * The input is two lists: the subroutines called and their arguments. Solution's constructor has one argument, the array w. pickIndex has no arguments. Arguments are always wrapped with a list, even if there aren't any.
     */

    /*
    Solution:

    random pick happened by weight which means if random number between two adjacent weight, then pick the new index,
    e.g.:
    [2, 4] which means when random between 1-2 pick index 0, when between 3-(4 + 2) pick index 1

    use presum array add weights so that could quickly do a binary search in weights presum array

     */

    class Solution {

        private Random random;
        private int[] presum;

        public Solution(int[] w) {
            this.random = new Random();
            for(int i=1; i<w.length; ++i)
                w[i] += w[i-1];
            this.presum = w;
        }

        public int pickIndex() {
            int len = presum.length;
            int idx = random.nextInt(presum[ len- 1]) + 1;
            int left = 0, right = len - 1;
            while(left < right){
                int mid = left + (right-left)/2;
                if(presum[mid] == idx)
                    return mid;
                else if(presum[mid] < idx)
                    left = mid + 1;
                else
                    right = mid;
            }
            return left;
        }
    }

}
