import java.util.Queue;
import java.util.TreeMap;

/*
 * @lc app=leetcode id=675 lang=java
 *
 * [675] Cut Off Trees for Golf Event
 */

// @lc code=start
class Solution {
    // time = O(m * n * log(m * n)), space = O(m * n)
    private static final int[][] DIRECTIONS = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    public int cutOffTree(List<List<Integer>> forest) {
        // corner case
        if (forest == null || forest.size() == 0 || forest.get(0) == null || forest.get(0).size() == 0) {
            return 0;
        }
        
        int m = forest.size(), n = forest.get(0).size();
        TreeMap<Integer, int[]> map = new TreeMap<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (forest.get(i).get(j) > 1) {
                    map.put(forest.get(i).get(j), new int[]{i, j});
                }
            }
        }

        int x = 0, y = 0, res = 0;
        for (int key : map.keySet()) {
            int xx = map.get(key)[0], yy = map.get(key)[1];
            int step = bfs(forest, x, y, xx, yy);
            if (step == -1) return -1;
            res += step;
            x = xx;
            y = yy;
        }
        return res;
    }

    private int bfs(List<List<Integer>> forest, int x, int y, int xx, int yy) {
        int m = forest.size(), n = forest.get(0).size();
        if (x == xx && y == yy) return 0;

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(x * n + y);
        boolean[][] visited = new boolean[m][n];
        visited[x][y] = true;

        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int cur = queue.poll();
                int i = cur / n, j = cur % n;
                for (int[] dir : DIRECTIONS) {
                    int ii = i + dir[0];
                    int jj = j + dir[1];
                    if (ii == xx && jj == yy) return step + 1;
                    if (ii >= 0 && ii < m && jj >= 0 && jj < n && !visited[ii][jj] && forest.get(ii).get(jj) != 0) {
                        queue.offer(ii * n + jj);
                        visited[ii][jj] = true;
                    }
                }
            }
            step++;
        }
        return -1;
    }
}
// @lc code=end

