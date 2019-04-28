

public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        main.colorBorder(new int[][]{{1,1},{1,2}}, 0, 0, 3);
    }

    public int[][] colorBorder(int[][] grid, int r0, int c0, int color) {
        dfs(grid, r0, c0, grid[r0][c0], color, new boolean[grid.length][grid[0].length]);
        return grid;
    }

    private boolean dfs(int[][] grid, int x, int y,
                        int origin, int color,
                        boolean[][] visited) {
        System.out.println("1x:" + x + ",y:" + y);
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[x].length ||
                grid[x][y] != origin) return true;
        if (visited[x][y]) return false;
        visited[x][y] = true;
        System.out.println("2x:" + x + ",y:" + y);
        boolean boarderCheck = dfs(grid, x - 1, y, origin, color, visited) ||
                dfs(grid, x + 1, y, origin, color, visited) ||
                dfs(grid, x, y - 1, origin, color, visited) ||
                dfs(grid, x, y + 1, origin, color, visited);
        if (boarderCheck) {
            grid[x][y] = color;
        }
        return false;
    }

}





