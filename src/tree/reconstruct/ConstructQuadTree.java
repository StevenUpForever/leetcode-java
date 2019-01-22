package tree.reconstruct;

public class ConstructQuadTree {

    //Difficulty: Easy
    //TAG: Uber
    //TAG: tree
    //TAG: DFS

    /**
     * 427. Construct Quad Tree
     * We want to use quad trees to store an N x N boolean grid. Each cell in the grid can only be true or false. The root node represents the whole grid. For each node, it will be subdivided into four children nodes until the values in the region it represents are all the same.
     *
     * Each node has another two boolean attributes : isLeaf and val. isLeaf is true if and only if the node is a leaf node. The val attribute for a leaf node contains the value of the region it represents.
     *
     * Your task is to use a quad tree to represent a given grid. The following example may help you understand the problem better:
     *
     * Given the 8 x 8 grid below, we want to construct the corresponding quad tree:
     *
     *
     *
     * It can be divided according to the definition above:
     *
     *
     *
     *
     *
     * The corresponding quad tree should be as following, where each node is represented as a (isLeaf, val) pair.
     *
     * For the non-leaf nodes, val can be arbitrary, so it is represented as *.
     *
     *
     *
     * Note:
     *
     * N is less than 1000 and guaranteened to be a power of 2.
     * If you want to know more about the quad tree, you can refer to its wiki.
     */

    /*
    Solution:
    construct tree node by if current sub matrix sum is filled by 1 (means row * col) or 0, then is leaf node
    otherwise dfs build sub node

    Time: O(n^2 build presum matrix + 4^logn)
    Space: O(logn)
     */

    public Node construct(int[][] grid) {
        if (grid == null) return null;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (i > 0) grid[i][j] += grid[i - 1][j];
                if (j > 0) grid[i][j] += grid[i][j - 1];
                if (i > 0 && j > 0) grid[i][j] -= grid[i - 1][j - 1];
            }
        }
        return constructHelper(grid, 0, 0, grid.length - 1, grid[0].length - 1);
    }

    private Node constructHelper(int[][] presum, int start_x, int start_y, int end_x, int end_y) {
        /*
        We don't need corner case here, e.g.: start_x >= end_x start_y >= end_y
        due to:
        1. dfs rule, start will not larger than end
        2. when start == end, need return a leaf node then stop
         */
        //sum of current sub matrix = total - left - top + topLeft
        int matrix_sum = presum[end_x][end_y] - (start_x > 0 ? presum[start_x - 1][end_y] : 0) -
                (start_y > 0 ? presum[end_x][start_y - 1] : 0) +
                (start_x > 0 && start_y > 0 ? presum[start_x - 1][start_y - 1] : 0);
        if (matrix_sum == 0)
            return new Node(false, true, null, null, null, null);
        else if (matrix_sum == (end_x - start_x + 1) * (end_y - start_y + 1))
            return new Node(true, true, null, null, null, null);
        else {
            int mid_x = start_x + (end_x - start_x)/2, mid_y = start_y + (end_y - start_y)/2;
            return new Node(false, false,
                    constructHelper(presum, start_x, start_y, mid_x, mid_y),
                    constructHelper(presum, start_x, mid_y + 1, mid_x, end_y),
                    constructHelper(presum, mid_x + 1, start_y, end_x, mid_y),
                    constructHelper(presum, mid_x + 1, mid_y + 1, end_x, end_y)
            );
        }
    }

    class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;

        public Node() {}

        public Node(boolean _val,boolean _isLeaf,Node _topLeft,Node _topRight,Node _bottomLeft,Node _bottomRight) {
            val = _val;
            isLeaf = _isLeaf;
            topLeft = _topLeft;
            topRight = _topRight;
            bottomLeft = _bottomLeft;
            bottomRight = _bottomRight;
        }
    }

}
