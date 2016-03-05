import java.util.Arrays;

/**
 * Created by ChengzhiJia on 16/3/5.
 */
public class ProblemFrom41To50 {

    /*
    41. First Missing Positive
    Given an unsorted integer array, find the first missing positive integer.

For example,
Given [1,2,0] return 3,
and [3,4,-1,1] return 2.

Your algorithm should run in O(n) time and uses constant space.
     */
    public int firstMissingPositive(int[] A) {
        int i = 0;
        while(i < A.length){
            if(A[i] == i+1 || A[i] <= 0 || A[i] > A.length) i++;
            else if(A[A[i]-1] != A[i]) swap(A, i, A[i]-1);
            else i++;
        }
        i = 0;
        while(i < A.length && A[i] == i+1) i++;
        return i+1;
    }

    private void swap(int[] A, int i, int j){
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    /*
    42. Trapping Rain Water
    Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.

For example,
Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.


The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped. Thanks Marcos for contributing this image!
     */
    public int trap(int[] height) {
        int a = 0, b = height.length-1;
        int max = 0, leftmax = 0, rightmax = 0;
        while(a <= b){
            leftmax = Math.max(leftmax,height[a]);
            rightmax = Math.max(rightmax,height[b]);
            if(leftmax < rightmax){
                max +=(leftmax - height[a]);
                a++;
            }
            else{
                max += (rightmax-height[b]);
                b--;
            }
        }
        return max;
    }
}
