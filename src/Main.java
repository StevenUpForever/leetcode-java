import java.util.*;

public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        String s;
        System.out.println(main.lastStoneWeightII(new int[]{1,1,2,3,5,8,13,21,34,55,89,14,23,37,61,98}));

    }

    public int lastStoneWeightII(int[] stones) {
        if (stones == null || stones.length == 0) return 0;
        Arrays.sort(stones);
        Map<int[], Integer> map;
        return dfs(stones, new HashMap<>());
    }

    private int dfs(int[] stones, Map<String, Integer> map) {
        // for (int num: stones) System.out.print(num + ",");
        // System.out.println();
        int count = 0, value = 0;
        StringBuilder builder = new StringBuilder();
        for (int num: stones) {
            if (num != -1) {
                count++;
                value = num;
            }
            builder.append(num + ",");
        }
        String key = builder.toString();
        if (map.containsKey(key)) {
            return map.get(key);
        }
        if (count <= 1) {
            return value;
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < stones.length - 1; i++) {
            if (stones[i] == -1) continue;
            for (int j = i + 1; j < stones.length; j++) {
                if (stones[j] == -1) continue;
                int tempX = stones[i], tempY = stones[j];
                stones[j] = -1;
                if (stones[i] == stones[j]) {
                    stones[i] = -1;
                } else {
                    stones[i] = Math.abs(tempX - tempY);
                }
                res = Math.min(res, dfs(stones, map));
                stones[i] = tempX;
                stones[j] = tempY;
            }
        }
        map.put(key, res);
        return res;
    }

}





