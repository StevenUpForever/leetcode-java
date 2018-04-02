package array.interval;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class QueueReconstructionByHeight {

    //TAG: Google
    //TAG: array
    //TAG: interval
    //Difficulty: medium

    /**
     * 406. Queue Reconstruction by Height
     * Suppose you have a random list of people standing in a queue. Each person is described by a pair of integers (h, k), where h is the height of the person and k is the number of people in front of this person who have a height greater than or equal to h. Write an algorithm to reconstruct the queue.

     Note:
     The number of people is less than 1,100.


     Example

     Input:
     [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]

     Output:
     [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
     */

    /**
     * Solution:
     * If the array need sort by multiple factors, like this need consider about h and k,
     * similar to time interval problems, fix one factor and consider about others
     *
     * In this problem we fix height factor due to it's easier to sort,
     * so select highest height arrays, sort by k
     * The select second highest height arrays, sort by k, this k is prior than before height k
     *
     * 1. Sort the array by height then by k,
     * 2. loop the arr, insert current people into people[1] place into a linkedList (Which is better in insert)
     * The reason is the lower height always have higher priority to insert by k
     *
     * Time: O(nlogn + n)
     * Space: O(n)
     */

    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {
                    return o1[1] - o2[1];
                } else {
                    return o2[0] - o1[0];
                }
            }
        });
        List<int[]> list = new LinkedList<>();
        for (int[] arr: people) {
            list.add(arr[1], arr);
        }
        return list.toArray(new int[people.length][]);
    }

}

