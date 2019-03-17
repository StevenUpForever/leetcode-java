
import public_class.TreeNode;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
//        System.out.println(main.shipWithinDays(new int[]{1,2,3,1,1}, 4));
    }

    private int dfs(int[] weights, int cur, int sum, int D) {
//        if (D == 0)
//        if (min * D < sum)
    }

    public int bitwiseComplement(int N) {
        int temp = N, num = 0;
        while (temp > 0) {
            temp >>= 1;
            num <<= 1;
            num |= 1;
        }
        return N ^ num;
    }

    public int numPairsDivisibleBy60(int[] time) {
        if (time == null) return 0;
        Map<Integer, Integer> map = new HashMap<>();
        int count = 0;
        for (int num: time) {
            for (int i = 1; i <= 16; i++) {
                int key = i * 60 - num;
                if (map.containsKey(key)) {
                    count += map.get(key);
                }
            }
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        return count;
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



