package bit_operation;

public class Q1023BinaryStringWithSubstringsRepresenting1ToN {

    //Difficulty: medium
    //TAG: bit operation

    /**
     * 1023. Binary String With Substrings Representing 1 To N
     * Given a binary string S (a string consisting only of '0' and '1's) and a positive integer N, return true if and only if for every integer X from 1 to N, the binary representation of X is a substring of S.
     *
     *
     *
     * Example 1:
     *
     * Input: S = "0110", N = 3
     * Output: true
     * Example 2:
     *
     * Input: S = "0110", N = 4
     * Output: false
     *
     *
     * Note:
     *
     * 1 <= S.length <= 1000
     * 1 <= N <= 10^9
     */

    /*
    Solution:
    check from N to N / 2 see if any converted binary string is substring of S

    Time: O(S^2)
    Space: O(1)
     */

    public boolean queryString(String S, int N) {
        /*
        due to for N binary string XXXXX, 2*N binary string is 1XXXXX, so if S contains 2 * N, then S must contains S
        we just need check from N to N/2
         */
        for (int i = N; i >= N/2; i--) {
            if (!S.contains(Integer.toBinaryString(i))) return false;
        }
        return true;
    }

}
