package binary_search;

public class FirstBadVersion {

    //TAG: Facebook
    //TAG: Binary search
    //Difficulty: Easy

    /**
     * 278. First Bad Version
     * You are a product manager and currently leading a team to develop a new product. Unfortunately, the latest version of your product fails the quality check. Since each version is developed based on the previous version, all the versions after a bad version are also bad.
     *
     * Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the following ones to be bad.
     *
     * You are given an API bool isBadVersion(version) which will return whether version is bad. Implement a function to find the first bad version. You should minimize the number of calls to the API.
     */

    /**
     * Solution:
     * 1 - n is continuous number which in ascending order, use binary search will be fast and reduce API call
     * It's find the first occurrence number problem, so when isBadVersion need move right = mid, which
     * when left two numbers, check from left to right
     *
     * Time: O(logn)
     * Space: O(1)
     */

    public int firstBadVersion(int n) {
        int l = 1, r = n;
        while (l < r - 1) {
            int mid = l + (r - l)/2;
            if (isBadVersion(mid)) r = mid;
            else l = mid;
        }
        //First try to check l, and if l and r are all not bad, no bad version and return 0
        if (isBadVersion(l)) return l;
        return isBadVersion(r) ? r : 0;
    }

    boolean isBadVersion(int version) {
        return true;
    }

}
