package S8_BFSDFS;
import java.util.*;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: WallsandGates
 * Creater: Silentwings
 * Date: Feb, 2020
 * Description: 286. Walls and Gates
 */
public class LC286_WallsandGates {
    /**
     * You are given a m x n 2D grid initialized with these three possible values.
     *
     * -1 - A wall or an obstacle.
     * 0 - A gate.
     * INF - Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume
     * that the distance to a gate is less than 2147483647.
     * Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate,
     * it should be filled with INF.
     *
     * Example:
     *
     * Given the 2D grid:
     *
     * INF  -1  0  INF
     * INF INF INF  -1
     * INF  -1 INF  -1
     *   0  -1 INF INF
     * After running your function, the 2D grid should be:
     *
     *   3  -1   0   1
     *   2   2   1  -1
     *   1  -1   2  -1
     *   0  -1   3   4
     * @param rooms
     */
    // time = O(V + E) = O(m * n), space = O(m * n)
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public void wallsAndGates(int[][] rooms) {
        // corner case
        if (rooms == null || rooms.length == 0 || rooms[0] == null || rooms[0].length == 0) return;

        int row = rooms.length, col = rooms[0].length;
        // BFS
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (rooms[i][j] == 0) {
                    queue.offer(i * col + j);
                }
            }
        }

        int minLen = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int cur = queue.poll();
                int i = cur / col, j = cur % col;
                for (int[] dir : DIRECTIONS) {
                    int ii = i + dir[0];
                    int jj = j + dir[1];
                    if (ii >= 0 && ii < row && jj >= 0 && jj < col && rooms[ii][jj] == Integer.MAX_VALUE) {
                        queue.offer(ii * col + jj);
                        rooms[ii][jj] = minLen;
                    }
                }
            }
            minLen++;
        }
    }
}
