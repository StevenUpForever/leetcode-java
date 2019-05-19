package dynamic_programming.two_d_dp;

public class Q1049LastStoneWeightII {

    //Difficulty: medium
    //TAG: DP

    /**
     * 1049. Last Stone Weight II
     * We have a collection of rocks, each rock has a positive integer weight.
     *
     * Each turn, we choose any two rocks and smash them together.  Suppose the stones have weights x and y with x <= y.  The result of this smash is:
     *
     * If x == y, both stones are totally destroyed;
     * If x != y, the stone of weight x is totally destroyed, and the stone of weight y has new weight y-x.
     * At the end, there is at most 1 stone left.  Return the smallest possible weight of this stone (the weight is 0 if there are no stones left.)
     *
     *
     *
     * Example 1:
     *
     * Input: [2,7,4,1,8,1]
     * Output: 1
     * Explanation:
     * We can combine 2 and 4 to get 2 so the array converts to [2,7,1,8,1] then,
     * we can combine 7 and 8 to get 1 so the array converts to [2,1,1,1] then,
     * we can combine 2 and 1 to get 1 so the array converts to [1,1,1] then,
     * we can combine 1 and 1 to get 0 so the array converts to [1] then that's the optimal value.
     *
     *
     * Note:
     *
     * 1 <= stones.length <= 30
     * 1 <= stones[i] <= 100
     */

    /*
    Solution:

    https://leetcode.com/problems/last-stone-weight-ii/discuss/295030/share-my-java-solution-(knapsacks-problem)
     */

    public int lastStoneWeightII(int[] stones) {
        int sum = 0;
        for(int ele: stones){
            sum+=ele;
        }
        int total_sum = sum;
        int n = stones.length;
        sum/=2;

        boolean[][] dp = new boolean[sum+1][n+1];
        for(int j=0;j<=n;j++){
            dp[0][j] = true;
        }
        int max = 0;
        for(int i=1;i<=sum;i++){
            for(int j=1;j<=stones.length;j++){
                if(dp[i][j-1] || (i>=stones[j-1] && dp[i-stones[j-1]][j-1])){
                    dp[i][j] = true;
                    max = Math.max(i, max);
                }
            }
        }
        return total_sum-max*2;
    }

}
