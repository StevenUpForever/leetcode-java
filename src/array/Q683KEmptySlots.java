package array;

import java.util.HashSet;
import java.util.Set;

public class Q683KEmptySlots {

    //TAG: Google
    //TAG: Array
    //TAG: set
    //Difficulty: Hard

    /**
     * 683. K Empty Slots
     * There is a garden with N slots. In each slot, there is a flower. The N flowers will bloom one by one in N days. In each day, there will be exactly one flower blooming and it will be in the status of blooming since then.

     Given an array flowers consists of number from 1 to N. Each number in the array represents the place where the flower will open in that day.

     For example, flowers[i] = x means that the unique flower that blooms at day i will be at position x, where i and x will be in the range from 1 to N.

     Also given an integer k, you need to output in which day there exists two flowers in the status of blooming, and also the number of flowers between them is k and these flowers are not blooming.

     If there isn't such day, output -1.

     Example 1:
     Input:
     flowers: [1,3,2]
     k: 1
     Output: 2
     Explanation: In the second day, the first and the third flower have become blooming.
     Example 2:
     Input:
     flowers: [1,2,3]
     k: 1
     Output: -1
     Note:
     The given array will be in the range [1, 20000].
     */

    /**
     * Solution: Brute force
     * for loop the flowers i
     *      for loop from 0 to i, j
     *      if abs(flowers[i] - flowers[j]) - 1 == k &&
     *          for m loop from i to end
     *          if all numbers between flowers[i] - flowers[j] is in m -> end, which means flowers[m] < i && > j
     *          return i;
     * return -1
     *
     * Time: O(n^2) since, when j loop from 0 .. i, m loop from i .. end same as another loop from 0 to end
     * Space: O(1)
     *
     * Since "The given array will be in the range [1, 20000]" O(n^2) is not good
     */

    /**
     * Solution 2:
     * ** similar to Q1TwoSum ***
     * Use Set to filter when flower at flowers[i] - k - 1 (smaller than i) or flowers[i] + k + 1 is recorded,
     * which means blooming
     * then loop between smaller -> i, and i -> larger, find if non of number in set, if so, return i, i will be
     * the earliest day
     *
     * Time: O(n^2) if k == 20000
     * Space: O(n)
     */

    public int kEmptySlotsS2(int[] flowers, int k) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < flowers.length; i++) {
            boolean pass = true;
            if (set.contains(flowers[i] - k - 1)) {
                for (int j = flowers[i] - k; j < flowers[i]; j++) {
                    if (set.contains(j)) {
                        pass = false;
                        break;
                    }
                }
                if (pass) return i + 1;
            }
            if (set.contains(flowers[i] + k + 1)) {
                for (int j = flowers[i] + 1; j < flowers[i] + k + 1; j++) {
                    if (set.contains(j)) {
                        pass = false;
                        break;
                    }
                }
                if (pass) return i + 1;
            }
            set.add(flowers[i]);
        }
        return -1;
    }

    /**
     * Solution 3:
     * Think reversely of index is day, number is place, use an array of index represent place, and number is day
     * init the first scope is 0...k, loop the day array, try to find place i which days[i] earlier than left 0 or larger
     * than right day[k], means this day is not within this scope, place i is not blooming
     * if loop cloud until right, means all days in scope is checked, use max day of (left, right), means until this day,
     * find a valid scope
     * otherwise update left = i, right = i + k + 1, update to scope start i to i + k + 1
     *
     * Time: O(n)
     * Space: O(n)
     */

    public int kEmptySlots(int[] flowers, int k) {
        int[] days = new int[flowers.length];
        //place start from 1, index need start from 0
        for(int i = 0; i < flowers.length; i++) days[flowers[i] - 1] = i + 1;
        int left = 0, right = k + 1, res = Integer.MAX_VALUE;
        for(int i = 0; right < days.length; i++){
            //if flower i is blooming earlier than left or right, i is not fit for left -> right scope,
            // check if i met the end of scope and update scope
            if(days[i] < days[left] || days[i] <= days[right]){
                /*
                if flower i loop to current right scope, then this scope is valid, update day to the
                earliest day of the scope right boundary
                 */
                if(i == right) res = Math.min(res, Math.max(days[left], days[right]));
                left = i;
                right = i + k + 1;
            }
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }


}
