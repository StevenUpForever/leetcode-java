package public_class;

public class UnionFind {

    /**
     * count is the union units found in current graph
     *
     * parent is the head of each union units which represent of current union unit
     *
     * rank is the count of current union units, which means when two units going to union,
     * the rank is the rules which one merge to which one
     */
    private int count;
    private int[] parents;
    private int[] rank;

    //For question like 200, a matrix initially with 1s
    public UnionFind(char[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        count = 0;
        parents = new int[row * col];
        rank = new int[row * col];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                int index = i * col + j;
                if (grid[i][j] == '1') {
                    //Set parents array without consider about if islands could union
                    parents[index] = index;
                    count++;
                }
                rank[index] = 0;
            }
        }
    }

    //For questions like 305, init with empty board and dynamically add numbers
    public UnionFind(int n) {
        count = 0;
        parents = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            //Due to number may be 0
            parents[i] = -1;
            rank[i] = 0;
        }
    }

    public int getCount() {
        return count;
    }

    //Check wether current unit contains elements
    public boolean isValid(int i) {
        return parents[i] >= 0;
    }

    //When update parent, update the count too, fix count in union method
    public void setParents(int i) {
        parents[i] = i;
        count++;
    }

    public int find(int i) {
        /*
        If parent of current is not itself, find the highest number of this unit, and set to current
        to make the unit consistent
         */
        if (parents[i] != i) parents[i] = find(parents[i]);
        return parents[i];
    }

    //union units contains i and j
    public void union(int i, int j) {
        //First find parent of these two units
        int parent1 = find(i);
        int parent2 = find(j);
        //If we find two units need to union by parent not same
        if (parent1 != parent2) {
            //Compare rank of them, always merge lower rank one to higher rank
            if (rank[parent1] < rank[parent2]) parents[parent1] = parent2;
            else if (rank[parent1] > rank[parent2]) parents[parent2] = parent1;
            else {
                parents[parent2] = parent1;
                rank[parent1]++;
            }
            //Decrease count due to union two units
            count--;
        }
    }

}
