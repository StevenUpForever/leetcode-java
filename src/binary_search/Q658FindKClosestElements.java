package binary_search;

import java.util.ArrayList;
import java.util.List;

public class Q658FindKClosestElements {

    //Difficulty: medium
    //TAG: Uber
    //TAG: binary search

    /**
     * 658. Find K Closest Elements
     * Given a sorted array, two integers k and x, find the k closest elements to x in the array. The result should also be sorted in ascending order. If there is a tie, the smaller elements are always preferred.
     *
     * Example 1:
     * Input: [1,2,3,4,5], k=4, x=3
     * Output: [1,2,3,4]
     * Example 2:
     * Input: [1,2,3,4,5], k=4, x=-1
     * Output: [1,2,3,4]
     * Note:
     * The value k is positive and will always be smaller than the length of the sorted array.
     * Length of the given array is positive and will not exceed 104
     * Absolute value of elements in the array and x will not exceed 104
     * UPDATE (2017/9/19):
     * The arr parameter had been changed to an array of integers (instead of a list of integers). Please reload the code definition to get the latest changes.
     */

    /*
    Solution:
    1. binary search find nearest of x
    2. expand to left and right, add to small list and large list
    3. due to result need to be ascending order, append reverse of small and normal large to list

    Time: O(logn + n)
    Space: O(n)
     */

    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int l = 0, r = arr.length - 1;
        while (l < r - 1) {
            int mid = l + (r - l)/2;
            if (arr[mid] == x) {
                l = mid;
                r = mid + 1;
                break;
            } else if (arr[mid] < x) {
                l = mid;
            } else r = mid;
        }
        List<Integer> small = new ArrayList<>(),
                large = new ArrayList<>();
        while ((l >= 0 || r < arr.length) && k-- > 0) {
            if (l < 0) large.add(arr[r++]);
            else if (r >= arr.length)small.add(arr[l--]);
            else {
                if (x - arr[l] <= arr[r] - x) small.add(arr[l--]);
                else large.add(arr[r++]);
            }
        }
        List<Integer> list = new ArrayList<>();
        for (int i = small.size() - 1; i >= 0; i--) list.add(small.get(i));
        list.addAll(large);
        return list;
    }

}
