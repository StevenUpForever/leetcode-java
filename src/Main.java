
import public_class.TreeNode;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
    }

    public int maxProduct(String[] words) {
        Arrays.sort(words, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.length() - o1.length();
            }
        });
        int max = 0;
        for (int i = 0; i < words.length - 1; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if (words[i].length() * words[j].length() < max) break;
                if (!shardCommonChars(words[i], words[j])) {
                    max = words[i].length() * words[j].length();
                }
            }
        }
        return max;
    }

    private boolean shardCommonChars(String a, String b) {
        int[] chars = new int[26];
        for (int i = 0; i < a.length(); i++) chars[a.charAt(i) - 'a']++;
        for (int i = 0; i < b.length(); i++) {
            if (chars[b.charAt(i) - 'a'] > 0) return true;
        }
        return false;
    }

}



