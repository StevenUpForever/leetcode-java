package array.cycle;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Q957PrisonCellsAfterNDays {

    //Difficulty: medium
    //TAG: array
    //TAG: cycle

    /**
     * 957. Prison Cells After N Days
     * There are 8 prison cells in a row, and each cell is either occupied or vacant.
     *
     * Each day, whether the cell is occupied or vacant changes according to the following rules:
     *
     * If a cell has two adjacent neighbors that are both occupied or both vacant, then the cell becomes occupied.
     * Otherwise, it becomes vacant.
     * (Note that because the prison is a row, the first and the last cells in the row can't have two adjacent neighbors.)
     *
     * We describe the current state of the prison in the following way: cells[i] == 1 if the i-th cell is occupied, else cells[i] == 0.
     *
     * Given the initial state of the prison, return the state of the prison after N days (and N such changes described above.)
     *
     *
     *
     * Example 1:
     *
     * Input: cells = [0,1,0,1,1,0,0,1], N = 7
     * Output: [0,0,1,1,0,0,0,0]
     * Explanation:
     * The following table summarizes the state of the prison on each day:
     * Day 0: [0, 1, 0, 1, 1, 0, 0, 1]
     * Day 1: [0, 1, 1, 0, 0, 0, 0, 0]
     * Day 2: [0, 0, 0, 0, 1, 1, 1, 0]
     * Day 3: [0, 1, 1, 0, 0, 1, 0, 0]
     * Day 4: [0, 0, 0, 0, 0, 1, 0, 0]
     * Day 5: [0, 1, 1, 1, 0, 1, 0, 0]
     * Day 6: [0, 0, 1, 0, 1, 1, 0, 0]
     * Day 7: [0, 0, 1, 1, 0, 0, 0, 0]
     *
     * Example 2:
     *
     * Input: cells = [1,0,0,1,0,0,1,0], N = 1000000000
     * Output: [0,0,1,1,1,1,1,0]
     *
     *
     * Note:
     *
     * cells.length == 8
     * cells[i] is in {0, 1}
     * 1 <= N <= 10^9
     */

    /*
    Solution:

    We need loop N as fast as possible, since cells may have duplicated state, so we add cells state as string
    in map, with value N--, when we find dup state, we know that it contains a cycle,
    we can make N = N % map.get(state) - N

    Time: O(N)
    Space: O(1)
     */

    public int[] prisonAfterNDays(int[] cells, int N) {
        Map<String, Integer> map = new HashMap<>();
        while (N > 0) {
            int[] temp = new int[cells.length];
            map.put(Arrays.toString(cells), N--);
            for (int i = 1; i < 7; i++) {
                temp[i] = cells[i - 1] == cells[i + 1] ? 1 : 0;
            }
            cells = temp;
            String key = Arrays.toString(cells);
            if (map.containsKey(key)) N %= map.get(key) - N;
        }
        return cells;
    }



}
