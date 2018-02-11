package legacy_code.Problem41To50;

public class Trapping_Rain_Water {

    /**
     * 42. Trapping Rain Water
     * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.

     For example,
     Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
     */

    /**
     * Solution:
     * DP problem, as the max watter trapped is determined by the shorter side of left and right side
     * 1. define the left side (0) and right side (len - 1)
     * 2. DP rules:
     *      Base case: M[0] = 0, as no matter what is the height of left and right, but cannot trap any water on them
     *      Induction rule: for each index, the water is determined by shorter one of left and right, so keep leftMax and rightMax
     *              1. if leftMax < rightMax, res +=  max(0, leftMax - height[left]), leftMax = max(leftMax, height[left])
     *              2. if leftMax > rightMax, res +=  max(0, rightMax - height[right]), rightMax = max(rightMax, height[right])
     *
     * Time: O(n)
     * Space: O(1)
     */

    public int trap(int[] height) {
        if (height.length == 0) return 0;
        int left = 0, right = height.length - 1, res = 0, leftMax = height[left], rightMax = height[right];
        while (left < right) {
            if (height[left] <= height[right]) {
                //Only if max > current height add diff to res
                res += Math.max(0, leftMax - height[left]);
                //update max after add to res
                leftMax = Math.max(leftMax, height[left]);
                left++;
            } else {
                res += Math.max(0, rightMax - height[right]);
                rightMax = Math.max(rightMax, height[right]);
                right--;
            }
        }
        return res;
    }

}
