import public_class.TreeNode;

import java.math.BigInteger;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Main main = new Main();
    }

    public String removeOuterParentheses(String S) {
        Stack<Integer> stack = new Stack<>();
        String res = "";
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            if (stack.isEmpty() || c == '(') {
                stack.push(i);
            } else {
                int pop = stack.pop();
                if (stack.isEmpty()) {
                    res += (pop + 1 == i) ? "" : S.substring(pop + 1, i);
                }
            }
        }
        return res;
    }

    private BigInteger bigInteger = new BigInteger("0");
    public int sumRootToLeaf(TreeNode root) {
        dfs(root, new StringBuilder());
        System.out.println(bigInteger.intValue());
        return bigInteger.mod(new BigInteger("1000000007")).intValue();
    }

    private void dfs(TreeNode root, StringBuilder builder) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            builder.append(root.val);
            bigInteger = bigInteger.add(new BigInteger(builder.toString(), 2));
            builder.deleteCharAt(builder.length() - 1);
            return;
        }
        builder.append(root.val);
        dfs(root.left, builder);
        dfs(root.right, builder);
        builder.deleteCharAt(builder.length() - 1);
    }

    public List<Boolean> camelMatch(String[] queries, String pattern) {
        List<Boolean> list = new ArrayList<>();
        if (queries == null || pattern == null) return list;
        outer: for (String query: queries) {
            int index = 0;
            for (int i = 0; i < query.length(); i++) {
                char c = query.charAt(i);
                if (c - 'A' >= 0 && c - 'A' < 26) {
                    if (index == pattern.length() || c != pattern.charAt(index)) {
                        list.add(false);
                        continue outer;
                    } else index++;
                } else if (index < pattern.length() && c == pattern.charAt(index)) {
                    index++;
                }
            }
            list.add(index == pattern.length());
        }
        return list;
    }

    public int videoStitching(int[][] clips, int T) {
        if (clips == null) return -1;
        Arrays.sort(clips, (int[] o1, int[] o2) -> o1[1] == o2[1] ? o1[0] - o2[0] : o1[1] - o2[1]);
        int[] dp = new int[clips.length];
        Arrays.fill(dp, Integer.MAX_VALUE);
        int min = dp.length + 1;
        for (int i = clips.length - 1; i >= 0; i--) {
            int[] clip = clips[i];
            if (clip[1] == T) {
                dp[i] = 1;
            } else {
                for (int j = i + 1; j < clip.length; j++) {
                    int[] next = clips[j];
                    if (clip[1] >= next[0]) {
                        dp[i] = Math.min(dp[i], dp[j] == Integer.MAX_VALUE ? Integer.MAX_VALUE : dp[j] + 1);
                    }
                }
            }
            if (clip[0] == 0) min = Math.min(min, dp[i]);
        }
        return min == dp.length + 1 ? -1 : min;
    }

}





