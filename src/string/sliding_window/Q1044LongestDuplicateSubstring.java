package string.sliding_window;

import java.util.HashSet;
import java.util.Set;

public class Q1044LongestDuplicateSubstring {

    //Difficulty: hard
    //TAG: binary search
    //TAG: substring
    //TODO: review

    /**
     * 1044. Longest Duplicate Substring
     * Given a string S, consider all duplicated substrings: (contiguous) substrings of S that occur 2 or more times.  (The occurrences may overlap.)
     *
     * Return any duplicated substring that has the longest possible length.  (If S does not have a duplicated substring, the answer is "".)
     *
     *
     *
     * Example 1:
     *
     * Input: "banana"
     * Output: "ana"
     * Example 2:
     *
     * Input: "abcd"
     * Output: ""
     *
     *
     * Note:
     *
     * 2 <= S.length <= 10^5
     * S consists of lowercase English letters.
     */

    /*
    Solution:

    1. do binary search find proper max length, if mid length has no dup, then longer than it will not too
    2. use window hash find dup strings

    https://leetcode.com/problems/longest-duplicate-substring/discuss/290961/Java-w-comments-Binary-Search-Length-%2B-Rolling-Sequence-Hashing

    Time: O(logn * n)
    Space: O(n)
     */

    // Represents a substring and its sequence-dependent hash signature.
    private class CompressedString {
        int leftIncl, rightIncl;
        CompressedString(int li, int ri) { leftIncl=li; rightIncl=ri; }
        // In case of equal signatures, we still need to double-check
        // for actual substring equality, for rare false collisions.
        @Override public boolean equals(Object obj) {
            CompressedString that = (CompressedString)obj;
            int len = rightIncl-leftIncl+1;
            for(int offset = 0; offset < len; offset++) {
                if (S.charAt(this.leftIncl+offset) != S.charAt(that.leftIncl+offset)) {
                    return false;
                }
            }
            return true;
        }
        // Derive the sequence-dependent rolling hash signature of the substring.
        @Override public int hashCode() {
            // To use the cumSum to get our hash, we have to subtract all the
            // contribution to the cumSum from before the substring begins,
            // which would have grown by 26^length.
            long leftSumExcl = leftIncl == 0 ? 0 : cumSum[leftIncl-1];
            leftSumExcl = (leftSumExcl * pow26[rightIncl-leftIncl+1]) % 1000003L;
            return (int)((cumSum[rightIncl] - leftSumExcl + 1000003L) % 1000003L);
        }
    }

    private String S;
    private long[] cumSum; // base-26 sum of the substring [0,idx]
    private long[] pow26;  // just powers of 26 pre-computed

    private void precomputations() {
        int curr = 0;
        final int Slen = S.length();
        // Compute base-26 sum of every substring from the beginning to end (idx).
        for(int idx = 0; idx < Slen; idx++) {
            int val = S.charAt(idx)-'a';
            curr = (curr*26 + val) % 1000003;
            cumSum[idx] = curr;
        }
        pow26 = new long[Slen];
        pow26[0] = 1;
        for(int pow=1; pow < Slen; pow++) {
            pow26[pow] = (pow26[pow-1]*26L) % 1000003L;
        }
    }

    public String longestDupSubstring(String S) {
        this.S = S;
        this.cumSum = new long[S.length()];
        precomputations();
        int Lmin = 0;
        int Lmax = S.length()-1;
        String bestDupSubstring = null;

        // Do the binary search on the substring length.
        // Lmin and Lmax are the range we are searching, but the
        // ideal solution may already have been found outside this range.
        while (Lmin <= Lmax) {
            int Lmid = (Lmin+Lmax)/2;
            String possible = tryfind(Lmid);
            if (possible == null) {
                Lmax = Lmid-1;
            }
            else {
                if (bestDupSubstring == null || possible.length() > bestDupSubstring.length()) {
                    bestDupSubstring = possible;
                }
                Lmin = Lmid+1;
            }
        }
        return bestDupSubstring == null ? "" : bestDupSubstring;
    }

    // See if we can find any duplicate substring of length L.
    private String tryfind(int L) {
        if (L == 0) { return null; }
        final int Slen = S.length();
        // Save the substring signatures as we see them.
        Set<CompressedString> have = new HashSet<>();
        // Slide over every substring of length L.
        for(int leftIncl = 0; leftIncl <= Slen-L; leftIncl++) {
            int rightIncl = L - 1 + leftIncl;
            CompressedString curr = new CompressedString(leftIncl, rightIncl);
            if (have.contains(curr)) {
                return S.substring(leftIncl, rightIncl+1);
            }
            have.add(curr);
        }
        return null;
    }

}
