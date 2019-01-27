package greey;

public class StringWithoutAAAOrBBB {

    //Difficulty: Easy
    //TAG: greedy

    /**
     * 984. String Without AAA or BBB
     * Given two integers A and B, return any string S such that:
     *
     * S has length A + B and contains exactly A 'a' letters, and exactly B 'b' letters;
     * The substring 'aaa' does not occur in S;
     * The substring 'bbb' does not occur in S.
     *
     *
     * Example 1:
     *
     * Input: A = 1, B = 2
     * Output: "abb"
     * Explanation: "abb", "bab" and "bba" are all correct answers.
     * Example 2:
     *
     * Input: A = 4, B = 1
     * Output: "aabaa"
     *
     *
     * Note:
     *
     * 0 <= A <= 100
     * 0 <= B <= 100
     * It is guaranteed such an S exists for the given A and B.
     */

    /*
    Solution:
    try to arrange a b to ababab or bababa, their might be any more a or b
    if a is more than b, try to arrange like aabaabaabababab
    otherwise as bbabbabbabababa
    overall means try to insert the more char as early as possible, and make the more char before less char
    so that e.g. for A = 1 B = 3 will be babb not abbb

    Time: O(max(A, B))
    Space: O(1)
     */

    public String strWithout3a3b(int A, int B) {
        char c = A > B ? 'a' : 'b';
        StringBuilder builder = new StringBuilder();
        int diff = Math.abs(A - B);
        for (int i = 0; i < Math.min(A, B); i++) {
            //try to append more char first, no matter it's a or b, but will always be char c
            builder.append(c);
            if (diff-- > 0) builder.append(c);
            //append another char
            builder.append(c == 'a' ? 'b' : 'a');
        }
        //remember append rest of more char
        while (diff-- > 0) builder.append(c);
        return builder.toString();
    }

}
