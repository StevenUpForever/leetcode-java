package dynamic_programming;

import java.util.Arrays;

public class MinimumCostForTickets {

    //Difficulty: medium
    //TAG: dp

    /**
     * 983. Minimum Cost For Tickets
     * 983. Minimum Cost For Tickets
     * Medium
     *
     * 62
     *
     * 1
     *
     * Favorite
     *
     * Share
     * In a country popular for train travel, you have planned some train travelling one year in advance.  The days of the year that you will travel is given as an array days.  Each day is an integer from 1 to 365.
     *
     * Train tickets are sold in 3 different ways:
     *
     * a 1-day pass is sold for costs[0] dollars;
     * a 7-day pass is sold for costs[1] dollars;
     * a 30-day pass is sold for costs[2] dollars.
     * The passes allow that many days of consecutive travel.  For example, if we get a 7-day pass on day 2, then we can travel for 7 days: day 2, 3, 4, 5, 6, 7, and 8.
     *
     * Return the minimum number of dollars you need to travel every day in the given list of days.
     *
     *
     *
     * Example 1:
     *
     * Input: days = [1,4,6,7,8,20], costs = [2,7,15]
     * Output: 11
     * Explanation:
     * For example, here is one way to buy passes that lets you travel your travel plan:
     * On day 1, you bought a 1-day pass for costs[0] = $2, which covered day 1.
     * On day 3, you bought a 7-day pass for costs[1] = $7, which covered days 3, 4, ..., 9.
     * On day 20, you bought a 1-day pass for costs[0] = $2, which covered day 20.
     * In total you spent $11 and covered all the days of your travel.
     * Example 2:
     *
     * Input: days = [1,2,3,4,5,6,7,8,9,10,30,31], costs = [2,7,15]
     * Output: 17
     * Explanation:
     * For example, here is one way to buy passes that lets you travel your travel plan:
     * On day 1, you bought a 30-day pass for costs[2] = $15 which covered days 1, 2, ..., 30.
     * On day 31, you bought a 1-day pass for costs[0] = $2 which covered day 31.
     * In total you spent $17 and covered all the days of your travel.
     *
     *
     * Note:
     *
     * 1 <= days.length <= 365
     * 1 <= days[i] <= 365
     * days is in strictly increasing order.
     * costs.length == 3
     * 1 <= costs[i] <= 1000
     */

    /*
    Solution 1:
    dp array, represent min cost to current days[i]
    first if days[i] is within 1 or 7 or 30 days from day1 set it to a single ticket, and do dp loop
    loop from 0...i-1 if next arranged day of days[j] means days[j + 1], is within 1 or 7 or 30 days to days[i]
    check min e.g. dp[i] = min(dp[i], dp[j] + cost[0..1..2])

    Time: O(n^2)
    Space: O(n)
     */

    public int mincostTickets(int[] days, int[] costs) {
        int[] dp = new int[days.length];
        dp[0] = costs[0];
        for (int i = 1; i < days.length; i++) {
            if (days[i] - days[0] < 7) dp[i] = costs[1];
            else if (days[i] - days[0] < 30) dp[i] = costs[2];
            else dp[i] = Integer.MAX_VALUE;
            for (int j = 0; j < i; j++) {
                if (days[j + 1] >= days[i]) {
                    dp[i] = Math.min(dp[i], dp[j] + costs[0]);
                } else if (days[j + 1] >= days[i] - 6) {
                    dp[i] = Math.min(dp[i], dp[j] + costs[1]);
                } else if (days[j + 1] >= days[i] - 29) {
                    dp[i] = Math.min(dp[i], dp[j] + costs[2]);
                }
            }
        }
        return dp[dp.length - 1];
    }

    /*
    Solution 2:
    shortage of solution 1 is, j loop needed due to we need find closed day to days[i] - 1 or 7 or 30 may not exist
    int the days[], so we need do at least a binary search or for loop find it

    So we could have array of array[366] due to max 365 days, and need days[1] represent the first day
    then we can easily find days[i - 1...7...30] if days[i - 1...7...30] is not existed in days[] set it in dp[i] to
    dp[i - 1], make the same as previous day so actually we find the closet days but not the actual days[i - 1...7...30]

    Time: O(365)
    Space: O(366)
     */

    public int mincostTickets2(int[] days, int[] costs) {
        boolean[] dayIncluded = new boolean[366];
        for (int day : days) {
            dayIncluded[day] = true;
        }
        int[] minCost = new int[366];
        minCost[0] = 0;
        for (int day = 1; day <= 365; ++day) {
            if (!dayIncluded[day]) {
                minCost[day] = minCost[day-1];
                continue;
            }
            int min;
            min = minCost[day-1] + costs[0];
            min =Math.min(min, minCost[Math.max(0, day-7)] + costs[1]);
            min = Math.min(min, minCost[Math.max(0, day-30)] + costs[2]);
            minCost[day] = min;
        }

        return minCost[365];

    }

}
