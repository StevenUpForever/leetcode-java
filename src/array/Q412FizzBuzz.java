package array;

import java.util.ArrayList;
import java.util.List;

public class Q412FizzBuzz {

    //Difficulty: Easy
    //TAG: Apple
    //TAG: array
    //TAG: math

    /**
     * 412. Fizz Buzz
     * Write a program that outputs the string representation of numbers from 1 to n.
     *
     * But for multiples of three it should output “Fizz” instead of the number and for the multiples of five output “Buzz”. For numbers which are multiples of both three and five output “array.Q412FizzBuzz”.
     *
     * Example:
     *
     * n = 15,
     *
     * Return:
     * [
     *     "1",
     *     "2",
     *     "Fizz",
     *     "4",
     *     "Buzz",
     *     "Fizz",
     *     "7",
     *     "8",
     *     "Fizz",
     *     "Buzz",
     *     "11",
     *     "Fizz",
     *     "13",
     *     "14",
     *     "array.Q412FizzBuzz"
     * ]
     */

    /*
    Solution:
    Fist check array.Q412FizzBuzz due to check both first will reduce check afterwards

    Time: O(n)
    Space: O(n)
     */

    public List<String> fizzBuzz(int n) {
        List<String> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                list.add("array.Q412FizzBuzz");
            } else if (i % 3 == 0) {
                list.add("Fizz");
            } else if (i % 5 == 0) {
                list.add("Buzz");
            } else {
                list.add(String.valueOf(i));
            }
        }
        return list;
    }

}
