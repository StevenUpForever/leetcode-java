import java.util.*;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();

    }

    public boolean isValid(String S) {
        if (S == null || S.length() == 0 || !S.startsWith("a") || !S.endsWith("c")) return false;
        Stack<Character> stack = new Stack<>();
        for (char c: S.toCharArray()) {
            switch (c) {
                case 'a':
                    stack.push(c);
                    break;
                case 'b':
                    if (!stack.isEmpty() && stack.peek() == 'a') stack.push(c);
                    else return false;
                    break;
                case 'c':
                    if (!stack.isEmpty() && stack.peek() == 'b') {
                        stack.pop();
                        if (!stack.isEmpty()) stack.pop();
                        else return false;
                    } else return false;
                    break;
                    default:
                        return false;
            }
        }
        return stack.isEmpty();
    }

    public int longestOnes(int[] A, int K) {
        if (A == null || A.length == 0) return 0;
        int countOf0 = 0, countOf1 = 0;
        List<int[]> lists = new ArrayList<>();
        for (int i = 0; i < A.length; i++) {
            if (A[i] == 0) {
                if (countOf1 > 0) {
                    lists.add(new int[]{countOf0, countOf1});
                    countOf0 = 1;
                    countOf1 = 0;
                } else countOf0++;
            } else if (A[i] == 1) {
                countOf1++;
            }
        }
        lists.add(new int[]{A.length - 1, countOf0, countOf1});
        int max = 0;
        for (int i = 0; i < lists.size(); i++) {
            int temp = K, j = i, cur = 0;
            while (temp >= 0 && j >= 0) {
                int numOf0 = lists.get(j)[0], numOf1 = lists.get(j)[1];
                cur += numOf1;
                if (temp >= numOf0) {
                    cur += numOf0;
                    temp -= numOf0;
                } else {
                    cur += temp;
                    break;
                }
                j--;
            }
            max = Math.max(max, cur);
        }
        return max;
    }

//    public int mergeStones(int[] stones, int K) {
//        if (stones == null || K <= 1 || (stones.length - 1) % (K - 1) != 0) return -1;
//
//    }
//
//    private int mergeStonesHelper(int[] stones, int K, int cost, int placeTaken) {
//        if (stones.length - placeTaken <= 1) {
//            return cost;
//        }
//
//    }

}



