package math;

public class Q1037ValidBoomerang {

    //Difficulty: easy
    //TAG: math

    /**
     * 1037. Valid Boomerang
     * A boomerang is a set of 3 points that are all distinct and not in a straight line.
     *
     * Given a list of three points in the plane, return whether these points are a boomerang.
     *
     *
     *
     * Example 1:
     *
     * Input: [[1,1],[2,3],[3,2]]
     * Output: true
     * Example 2:
     *
     * Input: [[1,1],[2,2],[3,3]]
     * Output: false
     *
     *
     * Note:
     *
     * points.length == 3
     * points[i].length == 2
     * 0 <= points[i][j] <= 100
     */

    /*
    Solution:
     Simply verify if two points of three combined a slop is same or not

     Time: O(1)
     Space: O(1)
     */

    public boolean isBoomerang(int[][] p) {
        //divider may be 0, multiple is better than divide
        return (p[0][0] - p[1][0]) * (p[0][1] - p[2][1]) != (p[0][0] - p[2][0]) * (p[0][1] - p[1][1]);
    }

}
