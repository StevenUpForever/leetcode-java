package data_structure;

import java.util.HashMap;
import java.util.Map;

public class Q308RangeSumQuery2DMutable {

    //TAG: Google
    //TAG: Data structure
    //Difficulty: Hard

    /**
     * 308. Range Sum Query 2D - Mutable
     * Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).

     Range Sum Query 2D
     The above rectangle (with the red border) is defined by (row1, col1) = (2, 1) and (row2, col2) = (4, 3), which contains sum = 8.

     Example:
     Given matrix = [
     [3, 0, 1, 4, 2],
     [5, 6, 3, 2, 1],
     [1, 2, 0, 1, 5],
     [4, 1, 0, 1, 7],
     [1, 0, 3, 0, 5]
     ]

     sumRegion(2, 1, 4, 3) -> 8
     update(3, 2, 2)
     sumRegion(2, 1, 4, 3) -> 10
     Note:
     The matrix is only modifiable by the update function.
     You may assume the number of calls to update and sumRegion function is distributed evenly.
     You may assume that row1 ≤ row2 and col1 ≤ col2.

     */

    class NumMatrix {

        //Use prefix sum matrix to calculate sub matrix sum
        private int[][] prefixMatrix;
        private int[][] matrix;
        private Map<cell, Integer> map;

        public NumMatrix(int[][] matrix) {
            if (matrix == null || matrix.length == 0) return;
            this.prefixMatrix = new int[matrix.length][matrix[0].length];
            this.matrix = new int[matrix.length][matrix[0].length];
            this.map = new HashMap<>();
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    if (i == 0) prefixMatrix[i][j] = matrix[i][j];
                    else prefixMatrix[i][j] = matrix[i][j] + prefixMatrix[i - 1][j];
                    this.matrix[i][j] = matrix[i][j];
                }
            }
        }

        public void update(int row, int col, int val) {
            if (matrix == null) return;
            //Update prefixSum matrix first and then matrix
            int diff = val - matrix[row][col];
            for (cell key: map.keySet()) {
                if (row >= key.row1 && col >= key.col1 && row <= key.row2 && col <= key.col2) {
                    Integer oldVal = map.get(key);
                    map.put(key, oldVal + diff);
                }
            }
            for (int i = row; i < matrix.length; i++) prefixMatrix[i][col] += diff;
            matrix[row][col] = val;
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            //First check if value saved in HashMap, if not, use prefix sum matrix to calculate sub matrix sum
            if (matrix == null) return 0;
            cell newcell = new cell(row1, col1, row2, col2);
            if (map.containsKey(newcell)) return map.get(newcell);
            else {
                int val = 0;
                for (int i = col1; i <= col2; i++) {
                    val += prefixMatrix[row2][i] - (row1 == 0 ? 0 : prefixMatrix[row1 - 1][i]);
                }
                map.put(newcell, val);
                return val;
            }
        }
    }

    class cell {
        int row1;
        int col1;
        int row2;
        int col2;
        public cell(int row1, int col1, int row2, int col2) {
            this.row1 = row1;
            this.col1 = col1;
            this.row2 = row2;
            this.col2 = col2;
        }

        //Override hashCode and equals to make cell as key in HashMap

        @Override
        public int hashCode() {
            String hashStr = "row1: " + row1 + ",col1: " + col1 +
                    ",row2: " + row2 + ",col2: " + col2;
            return hashStr.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof cell) {
                cell newObj = (cell)obj;
                return this.row1 == newObj.row1 && this.row2 == newObj.row2 &&
                        this.col1 == newObj.col1 && this.col2 == newObj.col2;
            }
            return false;
        }
    }

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * obj.update(row,col,val);
 * int param_2 = obj.sumRegion(row1,col1,row2,col2);
 */

}
