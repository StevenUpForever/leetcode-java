package recursion;

public class MedianTwoSortedArrays {

    //TAG: Google
    //TAG: Uber
    //TAG: recursion
    //TAG: binary search
    //Difficulty: Hard

    /**
    4. Median of Two Sorted Arrays
    There are two sorted arrays nums1 and nums2 of size m and n respectively.
    Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).

    Example 1:
    nums1 = [1, 3]
    nums2 = [2]
    The median is 2.0

    Example 2:
    nums1 = [1, 2]
    nums2 = [3, 4]
    The median is (2 + 3)/2 = 2.5
     */

    /*
     * Solution 1
     * Iterative each array, compare every element, merge two arrays to one array
     * need to consider about the total length of m + n is odd number or even number
     *
     * Time: O((m + n) m, n represent number of elements in each array
     * Space: O(m + n)
     */
    public double findMedianSortedArraysS1(int[] nums1, int[] nums2) {
        int[] res = new int[nums1.length + nums2.length];
        int i = 0, j = 0, m = 0;
        while (i < nums1.length && j < nums2.length) {
            res[m++] = nums1[i] < nums2[j] ? nums1[i++] : nums2[j++];
        }
        //Remember the post steps when length of nums1 and nums2 is not same
        while (i < nums1.length) res[m++] = nums1[i++];
        while (j < nums2.length) res[m++] = nums2[j++];
        return res.length % 2 == 0 ? (double)(res[res.length/2] + res[res.length/2 - 1])/2 : (double)res[res.length/2];
    }

    /*
     * Solution 2
     * Due to sorted array and find median, use binary search, recursion steps (also could implement in iterative way)
     * 1. for first step, assume the total length k
     *      1. When k/2 is over any of array's length (finally will ensure one array will over limit), return another array element which currentStart + currentK - 1, index is smaller 1 than length k
     *      2. assume k/2 will not over length of array 1 and array 2, then compare num1[k/2] and num2[k/2], pass all numbers in smaller k/2 part of the two arrays, because median is not possible in the smaller array k/2 part, as the worst case, array {1, 2, 6, 7, 8} and {3, 4, 5, 7, 8} although all elements in array 2 before k/2 is larger than array 1, but final result is 6, the k/2 in Max(array1[k/2], array2[k/2])
     * 2. recursion step, k = k/2, already ignore k/2 part, next step is find in smaller parts which in k/4 part
     *
     * Time: O(log(min(m, n)))
     * Space: Theoretically O(1), worst case O(log(min(m, n))
     *
     */

    public double findMedianSortedArraysS2(int[] nums1, int[] nums2) {
        /*
        Define two types of length
            if total length is odd number, +1 and +2 /2 are all the median index + 1
            if total length is even number, +1 and +2 /2 are left and right of median index + 1
         */
        int len1 = nums1.length + nums2.length + 1;
        int len2 = nums1.length + nums2.length + 2;
        return (findMedianHelper(nums1, 0, nums2, 0, len1/2) + findMedianHelper(nums1, 0, nums2, 0, len2/2))/2.0;
    }

    private double findMedianHelper(int[] nums1, int i, int[] nums2, int j, int k) {
        //if i or j is over the array length, no need to compare k/2 part from each array any more, return the kth number in the other valid array
        if (i >= nums1.length) return nums2[j + k - 1];
        if (j >= nums2.length) return nums1[i + k - 1];
        //if i and j are all not over the length, len1 - len2 < 2, and k ==1 means no more k/2, time to decide which (nums1[i], nums2[j]) is the median,
        // due to need to skip the smaller k/2 part, and for now, the skipped number is the median, so small part of current comparison is the result, choose min(nums1[i], nums2[j])
        if (k == 1) return Math.min(nums1[i], nums2[j]);
        //Choose the current end of k/2 part number of each array, and compare, skip the smaller k/2 part in that array
        int nums1Mid = i + k/2 - 1 < nums1.length ? nums1[i + k/2 - 1] : Integer.MAX_VALUE;
        int nums2Mid = j + k/2 - 1 < nums2.length ? nums2[j + k/2 - 1] : Integer.MAX_VALUE;
        return nums1Mid < nums2Mid ?
                findMedianHelper(nums1, i + k/2, nums2, j, k - k/2) :
                findMedianHelper(nums1, i, nums2, j + k/2, k - k/2);
    }

}
