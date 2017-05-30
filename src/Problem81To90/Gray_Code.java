package Problem81To90;

import java.util.*;

public class Gray_Code {

    /**
     * 89. Gray Code
     * The gray code is a binary numeral system where two successive values differ in only one bit.

     Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray code. A gray code sequence must begin with 0.

     For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:

     00 - 0
     01 - 1
     11 - 3
     10 - 2
     Note:
     For a given n, a gray code sequence is not uniquely defined.

     For example, [0,2,3,1] is also a valid gray code sequence according to the above definition.

     For now, the judge is able to judge based on one instance of gray code sequence. Sorry about that.
     */

    /**
     * Approach: actually, if not consider about sequence order, all numbers are the numbers within scope [0 ..< 2^n]
     * The rule is like:
     *      1. must permutation all bits start by 0 as much as possible
     *      2. next need to make two successive values from small to big, like priority(11) > like priority(10)
     */

    /**
     * Solution:
     * As we know the result of when n == 1 is [0, 1], and n == 2 is [00, 01, 10, 11], which means when n == 2, add all permutations of 0 and 1 to result of n == 1
     * And if n == 3, result of n == 1 is also part of result of n == 3, means [000, 001], so we could iteratively
     * Like if n == 3, ([0, 1] * result(n==2)) * result(n==1)
     *
     * Time: O(2^n) it's a binary tree
     * Space: O(O^n) saved array
     */

    public List<Integer> grayCode(int n) {
        List<Integer> res = new ArrayList<>();
        //Initially add 0 to the list, which when i == 0, 0 | 0 = 0, 0 | 1 = 1, so could have result of when n == 1
        res.add(0);
        for (int i = 0; i < n; i++) {
            int curSize = res.size();
            /*
            Here's something to explain:
            as the code sequence rule,
            1st priority is put as much as 0 at front, which also means the smaller number first
            When first bit is 1 in current size, need to put smallest difference of bits at front, which means under this rule, need to put larger number at first (11 higher than 10 when first bit is 1), so loop from end to start

            When finish reverse loop this time and push all numbers into list, then next reverse will be the normal order, which has 0 at the first bit, for example: 110 higher than 111, although first two bits diff same, but left only one bit (3rd bit) which do not have a pair, so order 0 higher than 1
             */
            for (int j = curSize - 1; j >= 0; j--) {
                //Current value | current highest
                res.add(res.get(j) | 1 << i);
            }
        }
        return res;
    }

}
