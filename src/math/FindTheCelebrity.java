package math;


public class FindTheCelebrity extends Relation {

    //TAG: Facebook
    //TAG: LinkedIn
    //TAG: math
    //Difficulty: Medium

    /**
     * 277. Find the Celebrity
     * Suppose you are at a party with n people (labeled from 0 to n - 1) and among them, there may exist one celebrity. The definition of a celebrity is that all the other n - 1 people know him/her but he/she does not know any of them.

     Now you want to find out who the celebrity is or verify that there is not one. The only thing you are allowed to do is to ask questions like: "Hi, A. Do you know B?" to get information of whether A knows B. You need to find out the celebrity (or verify there is not one) by asking as few questions as possible (in the asymptotic sense).

     You are given a helper function bool knows(a, b) which tells you whether A knows B. Implement a function int findCelebrity(n), your function should minimize the number of calls to knows.

     Note: There will be exactly one celebrity if he/she is in the party. Return the celebrity's label if there is a celebrity in the party. If there is no celebrity, return -1.
     */

    /**
     * Solution:
     * 1st loop: find the celebrity
     *      assume 0 (res) is the celebrity, loop i from 1 to n-1, if i knows res, res is at least the celebrity of current scope
     *      res = i
     * 2nd loop: identify this celebrity res, is really the celebrity, j from 0 to n-1
     *      if j doesn't know res, or res knows j, res is not the celebrity return -1, (i skip the res itself)
     *      if all number go through the first check, res is the celebrity
     */

    public int findCelebrity(int n) {
        int res = 0;
        //Find the celebrity
        for (int i = 1; i < n; i++) {
            if (knows(res, i)) res = i;
        }
        //Verify the celebrity
        for (int j = 0; j < n; j++) {
            if (j != res && (!knows(j, res) || knows(res, j))) return -1;
        }
        return res;
    }

}
