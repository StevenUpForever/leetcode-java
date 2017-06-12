package Problem251To260;

public class Paint_House {

    /**
     * 256. Paint House
     * There are a row of n houses, each house can be painted with one of the three colors: red, blue or green. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.

     The cost of painting each house with a certain color is represented by a n x 3 cost matrix. For example, costs[0][0] is the cost of painting house 0 with color red; costs[1][2] is the cost of painting house 1 with color green, and so on... Find the minimum cost to paint all houses.

     Note:
     All costs are positive integers.
     */

    /**
     * Approach:
     * As no two adjacent houses have the same color, so this question can be simplify with pick one number from each row, and two adjacent y cannot be the same, the smallest sum of one column
     */

    /**
     * Solution 1: Brute force (similar as N Queen)
     * Recursion every number from row 1 to row n, add all possible sums and update a global min one
     *
     * Time: O(n!) n represent numbers of elements in a row
     * Space: O(n) recursion levels
     */

    private int minCost = Integer.MAX_VALUE;
    public int minCostS1(int[][] costs) {
        minCostHelper(costs, 0, -1, 0);
        return minCost;
    }

    //row represent which row current at, col represent the last number col index which cannot be duplicated at this position
    private void minCostHelper(int[][] costs, int row, int col, int cost) {
        if (cost >= minCost) return;
        if (row == costs.length) {
            minCost = Math.min(minCost, cost);
            return;
        }
        for (int i = 0; i < costs[0].length; i++) {
            if (i != col) minCostHelper(costs, row + 1, i, cost + costs[row][i]);
        }
    }

    /**
     * Solution 2: DP
     * Base case: three case costs[0][0], costs[0][1], costs[0][2]
     * Induction rule: for every cell, it's best cost is add min of other two y index of previous row
     * At last choose the min of last row numbers as the final results
     *
     * Time: O(n) n represent all numbers in costs
     * Space: O(1)
     */

    public int minCostS2(int[][] costs) {
        if (costs == null || costs.length == 0) return 0;
        for (int i = 1; i < costs.length; i++) {
            costs[i][0] += Math.min(costs[i - 1][1], costs[i - 1][2]);
            costs[i][1] += Math.min(costs[i - 1][0], costs[i - 1][2]);
            costs[i][2] += Math.min(costs[i - 1][0], costs[i - 1][1]);
        }
        return Math.min(Math.min(costs[costs.length - 1][0], costs[costs.length - 1][1]), costs[costs.length - 1][2]);
    }

}
