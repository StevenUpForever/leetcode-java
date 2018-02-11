package legacy_code.Problem1To10;

public class Palindrome_Number {

    /**
     * 9. Palindrome Number
     * Determine whether an integer is a palindrome. Do this without extra space.
     */

    /**
     * Solution:
     * Base case:
     *      1. cannot use parse to String, due to no extra space
     *      2. cannot revert all the integer due to may overflow
     *      3. be aware of if negative number
     * Revert at most half of integer to compare new generated integer with rest of origin integer
     */

    public boolean isPalindrome(int x) {
        //If x is like 20, 2320, use follow while loop will be true, but they actually are false
        if (x < 0 || (x != 0 && x % 10 == 0)) return false;
        int temp = 0;
        //While loop until new number is larger or equal to rest of old number, at most will be half length of orginal number, which will guarantee not overflow
        while (temp < x) {
            temp = temp * 10 + x % 10;
            x /= 10;
        }
        //if 2332, x == temp, if 9 or 232 x == temp/10
        return x == temp || x == temp/10;
    }

}
