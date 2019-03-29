import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        String str;
    }

    public int cutOffTree(List<List<Integer>> forest) {
        if (forest == null || forest.size() == 0) return -1;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (List<Integer> list: forest) {
            for (int num: list) {
                if (num > 1) pq.offer(num);
            }
        }
        int row = forest.size(), col = forest.get(0).size();
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[row][col];
         if (forest.get(0).get(0) > 0 && forest.get(0).get(0) <= pq.peek()) {
             queue.offer(new int[]{0, 0});
             visited[0][0] = true;
         }
        int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int step = 0;
        while (!queue.isEmpty() && !pq.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                if (pq.isEmpty()) return step;
                int[] poll = queue.poll();
                int x = poll[0], y = poll[1], val = forest.get(x).get(y);
                if (val == pq.peek()) {
                    pq.poll();
                    queue.clear();
                    size = 0;
                    step--;
                    queue.offer(new int[]{x, y});
                    visited = new boolean[row][col];
                    visited[x][y] = true;
                    System.out.println("x: " + x + " y : " + y);
                    System.out.println("step: " + step);
                } else {
                    for (int[] dir: dirs) {
                        int m = x + dir[0], n = y + dir[1];
                        if (m >= 0 && m < row && n >= 0 && n < col &&
                                !visited[m][n] && forest.get(m).get(n) != 0 && forest.get(m).get(n) <= pq.peek()) {
                            visited[m][n] = true;
                            queue.offer(new int[]{m, n});
                        }
                    }
                }
            }
            step++;
        }
        return pq.isEmpty() ? step : -1;
    }

}



