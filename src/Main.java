import com.sun.org.apache.xpath.internal.operations.Bool;
import public_class.ListNode;

import java.math.BigInteger;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        System.out.println(main.oneCharDiff("lest", "lose"));
        List<String> list;
        Set<String> set = new HashSet<>();
        System.out.println(intToBinary(35));
    }

    public List<Boolean> prefixesDivBy5(int[] A) {
        List<Boolean> list = new ArrayList<>();
        BigInteger cur = new BigInteger("0");
        for (int num: A) {
            cur = cur.multiply(new BigInteger("2")).add(new BigInteger("" + num));
            list.add(cur.mod(new BigInteger("5")).compareTo(new BigInteger("0")) == 0);
        }
        return list;
    }

    private boolean oneCharDiff(String str1, String str2) {
        int[] count = new int[26];
        for (int i = 0; i < str1.length(); i++) {
            count[str1.charAt(i) - 'a']++;
        }
        for (int i = 0; i < str2.length(); i++) {
            count[str2.charAt(i) - 'a']--;
        }
        int num = 0;
        for (int i = 0; i < 26; i++) {
            if (count[i] > 0) num += count[i];
        }
        return num == 1;
    }

    public static String intToBinary(int n)
    {
        String s = "";
        while (n > 0)
        {
            s =  ( (n % 2 ) == 0 ? "0" : "1") +s;
            n = n / 2;
        }
        return s;
    }

    public List<Integer> negativeBase(int integer, int base) {
        List<Integer> digits = new ArrayList<>();
        while (integer != 0) {
            int i = integer % base;
            integer /= base;
            if(i < 0) {
                i += Math.abs(base);
                integer++;
            }
            digits.add(0, i);
        }
        return digits;
    }

//    public List<Boolean> prefixesDivBy5(int[] A) {
//        long num = 0;
//        List<Boolean> res = new ArrayList<>();
//        for (int cur: A) {
//            num <<= 1;
//            num |= cur;
//            res.add(num%5 == 0);
//        }
//        return res;
//    }



    public int[] nextLargerNodes(ListNode head) {
        List<Integer> list = new ArrayList<>();
        ListNode temp = head;
        while (temp != null) {
            list.add(temp.val);
            temp = temp.next;
        }
        int[] res = new int[list.size()];
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> list.get(o1) - list.get(o2));
        for (int i = 0; i < list.size(); i++) {
            while (!pq.isEmpty() && list.get(pq.peek()) < list.get(i)) {
                res[pq.poll()] = list.get(i);
            }
            pq.offer(i);
        }
        return res;
    }

    public int numEnclaves(int[][] A) {
        if (A == null || A[0].length == 0) return 0;
        int row = A.length, col = A[0].length;
        for (int i = 0; i < row; i++) {
            dfs(A, i, 0);
            dfs(A, i, col - 1);
        }
        for (int i = 0; i < col; i++) {
            dfs(A, 0, i);
            dfs(A, row - 1, i);
        }
        int count = 0;
        for (int[] arr: A) {
            for (int num: arr) {
                if (num == 1) count++;
            }
        }
        return count;
    }

    private void dfs(int[][] grid, int x, int y) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[x].length || grid[x][y] == 0) return;
        grid[x][y] = 0;
        dfs(grid, x + 1, y);
        dfs(grid, x - 1, y);
        dfs(grid, x, y + 1);
        dfs(grid, x, y - 1);
    }

//    public String baseNeg2(int N) {
//
//    }



}





