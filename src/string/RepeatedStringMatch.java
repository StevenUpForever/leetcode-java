package string;

public class RepeatedStringMatch {

    //TAG: Google
    //TAG: String
    //Difficulty: Easy

    /**
     * 686. Repeated String Match
     * Given two strings A and B, find the minimum number of times A has to be repeated such that B is a substring of it. If no such solution, return -1.

     For example, with A = "abcd" and B = "cdabcdab".

     Return 3, because by repeating A three times (“abcdabcdabcd”), B is a substring of it; and B is not a substring of A repeated two times ("abcdabcd").

     Note:
     The length of A and B will be between 1 and 10000.
     */

    /**
     * Solution:
     * compare from B to A, make A as assumed longer string than B (By make indexA repeat from 0 to A.length() - 1 until
     * indexB met unequal char or goes to B.length())
     *
     * Time: O(mn)
     * Space: O(1)
     */

    public int repeatedStringMatch(String A, String B) {
        if (A.length() == 0 && B.length() == 0) return 0;
        if (A.length() == 0 || B.length() == 0) return -1;
        int i = 0, count = 1;
        char[] chars = A.toCharArray();
        while (i < chars.length) {
            int k = i, j = 0;
            while (j < B.length() && B.charAt(j) == chars[k]) {
                k++;
                j++;
                //Be aware that if K need to repeat, check if B is finished validation, if so, no need to start A again
                if (k >= A.length() && j < B.length()) {
                    k = 0;
                    count++;
                }
            }
            if (j == B.length()) return count;
            count = 1;
            i++;
        }
        return -1;
    }

}
