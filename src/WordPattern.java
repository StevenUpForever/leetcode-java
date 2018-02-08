import java.util.HashMap;
import java.util.Map;

public class WordPattern {

    /**
     * 290. Word Pattern
     * Given a pattern and a string str, find if str follows the same pattern.

     Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in str.

     Examples:
     pattern = "abba", str = "dog cat cat dog" should return true.
     pattern = "abba", str = "dog cat cat fish" should return false.
     pattern = "aaaa", str = "dog cat cat dog" should return false.
     pattern = "abba", str = "dog dog dog dog" should return false.
     Notes:
     You may assume pattern contains only lowercase letters, and str contains lowercase letters separated by a single space.
     */

    public boolean wordPattern(String pattern, String str) {
        String[] strs = str.split(" ");
        Map<Character, String> map = new HashMap<>();
        Map<String, Character> map2 = new HashMap<>();
        if (pattern.length() != strs.length) return false;
        for (int i = 0; i < pattern.length(); i++) {
            char key = pattern.charAt(i);
            if (!map.containsKey(key)) {
                if (map2.containsKey(strs[i])) return false;
                map.put(key, strs[i]);
                map2.put(strs[i], key);
            } else if (!map.get(key).equals(strs[i])) return false;
        }
        return true;
    }

}
