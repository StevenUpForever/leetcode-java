import public_class.TreeNode;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
//        System.out.println(main.numSquarefulPerms(new int[]{13949,64411,26920,5204,2177,23617,44128,3455,47315,40706,45874,22858}));
//        for (List<Integer> list: main.permuteUnique()) {
        System.out.println(main.numSquarefulPerms(new int[]{1, 17, 8}));

//            for (int num: list) System.out.print(num + ",");
//            System.out.println();
//        }
        //13949,64411,26920,5204,2177,23617,44128,3455,47315,40706,45874,22858
    }

    public boolean isCousins(TreeNode root, int x, int y) {
        return isCousinsHelper(root, x, y) == 0;
    }

    private int isCousinsHelper(TreeNode root, int x, int y) {
        if (root == null) return -1;
        if (root.val == x || root.val == y) return 1;
        int left = isCousinsHelper(root.left, x, y);
        int right = isCousinsHelper(root.right, x, y);
        if (left > 0 && right > 0) {
            if (left == right) {
                return (root.left.val == x && root.right.val == y) ||
                        (root.right.val == x && root.left.val == y) ? -1 : 0;
            } else return -1;
        } else if (left >= 0) return left == 0 ? 0 : left + 1;
        else if (right >= 0) return right == 0 ? 0 : right + 1;
        else return -1;
    }

    public int orangesRotting(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        Queue<int[]> queue = new LinkedList<>();
        int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int row = grid.length, col = grid[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 2) queue.offer(new int[]{i, j});
            }
        }
        int step = 3;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int[] poll = queue.poll();
                for (int[] dir: dirs) {
                    int x = poll[0] + dir[0], y = poll[1] + dir[1];
                    if (x < 0 || x >= row || y < 0 || y >= col ||
                    grid[x][y] != 1) continue;
                    grid[x][y] = step;
                    queue.offer(new int[]{x, y});
                }
            }
            step++;
        }
        int min = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) return -1;
                if (grid[i][j] >= 3) {
                    min = Math.max(min, grid[i][j] - 2);
                }
            }
        }
        return min;
    }

//    public int minKBitFlips(int[] A, int K) {
//
//    }

    public List<String> permutations(String set) {
        List<String> res = new ArrayList<>();
        if (set == null) return res;
//        addPermutaions(set.toCharArray(), 0, res);
        return res;
    }

    private int count = 0;
    public int numSquarefulPerms(int[] A) {
        Arrays.sort(A);
        List<Integer> current = new ArrayList<>();
        boolean[] visited = new boolean[A.length];
        permute(current, A, visited);
        return count;
    }

//    private void addPermutaions(int[] nums, int cur) {
//        if (cur == nums.length) {
//            for (int i = 0; i < nums.length - 1; i++) {
//                int sum = nums[i] + nums[i + 1];
//
//            }
//            count++;
//            return;
//        }
//        Set<Integer> set = new HashSet<>();
//        for (int i = cur; i < nums.length; i++) {
//            if (set.add(nums[i])) {
//                if ((cur == 0 || isPerfectSqaure(nums[cur - 1] + nums[i])) &&
//                        (cur == nums.length - 1 || isPerfectSqaure(nums[cur + 1] + nums[i]))) {
//                    swap(nums, i, cur);
//                    addPermutaions(nums, cur + 1);
//                    swap(nums, i, cur);
//                };
//            }
//        }
//    }
//
//    private void swap(int[] nums, int a, int b) {
//        int c = nums[a];
//        nums[a] = nums[b];
//        nums[b] = c;
//    }

//    public List<List<Integer>> permuteUnique(int[] num) {
//        Arrays.sort(num);
//        List<List<Integer>> result = new ArrayList<>();
//        List<Integer> current = new ArrayList<>();
//        boolean[] visited = new boolean[num.length];
//        permute(result, current, num, visited);
//        return result;
//    }

    private void permute(List<Integer> current, int[] num, boolean[] visited) {
        if (current.size() == num.length) {
            count++;
            return;
        }
        for (int i=0; i<visited.length; i++) {
            if (!visited[i]) {
                if (i > 0 && num[i] == num[i-1] && visited[i-1]) {
                    return;
                }
                if (current.size() == 0 || isPerfectSqaure(current.get(current.size() - 1) + num[i])) {
                    visited[i] = true;
                    current.add(num[i]);
                    permute(current, num, visited);
                    current.remove(current.size()-1);
                    visited[i] = false;
                }
            }
        }
    }

    private boolean isPerfectSqaure(int num) {
        double sqrt = Math.sqrt(num);
        return sqrt - Math.floor(sqrt) == 0;
    }

}



