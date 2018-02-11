package legacy_code.Problem181To190;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Repeated_DNA_Sequences {

    /**
     * 187. Repeated DNA Sequences
     * All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG". When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.

     Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.

     For example,

     Given s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT",

     Return:
     ["AAAAACCCCC", "CCCCCAAAAA"].
     */

    /**
     * Solution:
     * Use HashMap to record the frequency of all 10 length substring of s, if will > 1 then
     * use HashSet to filter and add this substring
     * Add all HashSet to List
     *
     * Time: O(n) all substrings + O(n) set to List = O(n)
     * Space: O(n/10) HashMap with no dup substrings or no dup set = O(n)
     */

    public List<String> findRepeatedDnaSequences(String s) {
        List<String> res = new ArrayList<>();
        if (s == null || s.length() < 10) return res;
        HashMap<String, Integer> map = new HashMap<>();
        HashSet<String> set = new HashSet<>();
        for (int j = 9; j < s.length(); j++) {
            String cur = s.substring(j - 9, j + 1);
            if (!map.containsKey(cur)) map.put(cur, 1);
            else set.add(cur);
        }
        res.addAll(set);
        return res;
    }

}
