package array;

public class CanPlaceFlowers {

    //TAG: LinkedIn
    //TAG: array
    //Difficulty: Easy

    /**
     * 605. Can Place Flowers
     * Suppose you have a long flowerbed in which some of the plots are planted and some are not. However, flowers cannot be planted in adjacent plots - they would compete for water and both would die.

     Given a flowerbed (represented as an array containing 0 and 1, where 0 means empty and 1 means not empty), and a number n, return if n new flowers can be planted in it without violating the no-adjacent-flowers rule.

     Example 1:
     Input: flowerbed = [1,0,0,0,1], n = 1
     Output: True
     Example 2:
     Input: flowerbed = [1,0,0,0,1], n = 2
     Output: False
     Note:
     The input array won't violate no-adjacent-flowers rule.
     The input array size is in the range of [1, 20000].
     n is a non-negative integer which won't exceed the input array size.
     */

    /**
     * Solution:
     * Try to fill possible position with 1 from start to the end, with n--
     *      when n met 0 means we could place all these flowers, return true
     *      if loop all numbers and n haven't decrease to 0 then return false
     *
     * Time: O(n)
     * Space: O(1)
     */

    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        for (int i = 0; i < flowerbed.length; i++) {
            if (flowerbed[i] == 0) {
                //Include the situation that length == 1
                int pre = i == 0 ? 0 : flowerbed[i - 1];
                int next = i == flowerbed.length - 1 ? 0 : flowerbed[i + 1];
                if (pre == 0 && next == 0) {
                    flowerbed[i] = 1;
                    n--;
                }
            }
            //check outside if (flowerbed[i] == 0) incase flowerbed cannot be placed any flower but n originally == 0
            if (n <= 0) return true;
        }
        return false;
    }

}
