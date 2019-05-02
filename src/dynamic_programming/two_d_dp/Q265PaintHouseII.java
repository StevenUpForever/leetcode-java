package dynamic_programming.two_d_dp;

public class Q265PaintHouseII {

    //Difficulty: hard
    //TAG: LinkedIn
    //TAG: dp

    /**
     * 265. Paint House II
     * There are a row of n houses, each house can be painted with one of the k colors. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.
     *
     * The cost of painting each house with a certain color is represented by a n x k cost matrix. For example, costs[0][0] is the cost of painting house 0 with color 0; costs[1][2] is the cost of painting house 1 with color 2, and so on... Find the minimum cost to paint all houses.
     *
     * Note:
     * All costs are positive integers.
     *
     * Example:
     *
     * Input: [[1,5,3],[2,9,4]]
     * Output: 5
     * Explanation: Paint house 0 into color 0, paint house 1 into color 2. Minimum cost: 1 + 4 = 5;
     *              Or paint house 0 into color 2, paint house 1 into color 0. Minimum cost: 3 + 2 = 5.
     * Follow up:
     * Could you solve it in O(nk) runtime?
     */

    /*
    Solution:

    Similar to paint houses I, set two values min1, min2, smallest and second smallest, and do similar to paint houses I

    Time: O(nk)
    Space: O(1)
     */

    public int minCostII(int[][] costs) {
        if (costs.length == 0 || costs[0].length == 0) return 0;
        int index1 = -1, num1 = -1, index2 = -1, num2 = -1;
        for (int i = 0; i < costs.length; i++) {
            int tempIndex1 = -1, temp1 = -1, tempIndex2 = -1, temp2 = -1;
            for (int j = 0; j < costs[i].length; j++) {
                if (i > 0) costs[i][j] += j == index1 ? num2 : num1;
                int val = costs[i][j];
                if (tempIndex1 == -1) {
                    temp1 = val; tempIndex1 = j;
                } else {
                    if (temp1 > val) {
                        temp2 = temp1; tempIndex2 = tempIndex1;
                        temp1 = val; tempIndex1 = j;
                    } else if (tempIndex2 == -1 || temp2 > val) {
                        temp2 = val; tempIndex2 = j;
                    }
                }
            }
            index1 = tempIndex1; num1 = temp1;
            index2 = tempIndex2; num2 = temp2;
        }
        return num1;
    }

}
