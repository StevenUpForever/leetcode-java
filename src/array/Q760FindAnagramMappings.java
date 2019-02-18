package array;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Q760FindAnagramMappings {

    //TAG: Google
    //TAG: array
    //Difficulty: Easy

    /**
     * 760. Find Anagram Mappings
     * Given two lists Aand B, and B is an anagram of A. B is an anagram of A means B is made by randomizing the order of the elements in A.

     We want to find an index mapping P, from A to B. A mapping P[i] = j means the ith element in A appears in B at index j.

     These lists A and B may contain duplicates. If there are multiple answers, output any of them.

     For example, given

     A = [12, 28, 46, 32, 50]
     B = [50, 12, 32, 46, 28]
     We should return
     [1, 4, 3, 2, 0]
     as P[0] = 1 because the 0th element of A appears at B[1], and P[1] = 4 because the 1st element of A appears at B[4], and so on.
     Note:

     A, B have equal lengths in range [1, 100].
     A[i], B[i] are integers in range [0, 10^5].
     */

    /**
     * Solution 1:
     * for for loop B, and find first element same as current elm in A, which the index is not used
     *
     * Solution 2:
     * Use integer, list pair map to save B value -> all indices to map
     * loop A, add first index in map for given value A, and remove this value in list represent already visited
     *
     * Time: O(2n)
     * Space: O(n)
     */

    public int[] anagramMappings(int[] A, int[] B) {
        if (A == null || B == null || A.length != B.length) return null;
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < B.length; i++) {
            if (!map.containsKey(B[i])) map.put(B[i], new LinkedList<>());
            map.get(B[i]).add(i);
        }
        int[] res = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            res[i] = map.get(A[i]).get(0);
            map.get(A[i]).remove(0);
        }
        return res;
    }

}