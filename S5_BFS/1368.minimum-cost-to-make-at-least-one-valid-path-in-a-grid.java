import java.util.PriorityQueue;

/*
 * @lc app=leetcode id=1368 lang=java
 *
 * [1368] Minimum Cost to Make at Least One Valid Path in a Grid
 */

// @lc code=start
class Solution {
    // S1: Dijkstra
    // time = O(m * n * log(m * n)), space = O(m * n)
    private int[][] directions = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public int minCost(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]); // {cost, x, y}
        pq.offer(new int[]{0, 0, 0});
        boolean[][] visited = new boolean[m][n];

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int cost = cur[0], x = cur[1], y = cur[2];
            if (visited[x][y]) continue;
            visited[x][y] = true;

            if (x == m - 1 && y == n - 1) return cost;

            for (int k = 0; k < 4; k++) {
                int i = x + directions[k][0];
                int j = y + directions[k][1];
                if (i < 0 || i >= m || j < 0 || j >= n) continue;
                if (visited[i][j]) continue;
                int addon = k + 1 == grid[x][y] ? 0 : 1;
                pq.offer(new int[]{cost + addon, i, j});
            }
        }
        return -1;
    }

    // S2: BFS
    // time = O(m * n), space = O(m * n)
    public int minCost2(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        List<int[]> temp = new ArrayList<>();
        boolean[][] visited = new boolean[m][n];
        dfs(grid, 0, 0, temp, visited);
        Queue<int[]> queue = new LinkedList<>();
        for (int[] x : temp) queue.offer(x);

        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int[] cur = queue.poll();
                int x = cur[0], y = cur[1];
                if (x == m - 1 && y == n - 1) return step;
                for (int k = 0; k < 4; k++) {
                    int i = x + directions[k][0];
                    int j = y + directions[k][1];
                    if (i < 0 || i >= m || j < 0 || j >= n) continue;
                    if (visited[i][j]) continue;

                    temp = new ArrayList<>(); // must clear the temp list before new round!
                    dfs(grid, i, j, temp, visited);
                    for (int[] t : temp) queue.offer(t);
                }
                
            }
            step++;
        }
        return -1;
    }

    private void dfs(int[][] grid, int x, int y, List<int[]> temp, boolean[][] visited) {
        int m = grid.length, n = grid[0].length;
        if (x < 0 || x >= m || y < 0 || y >= n) return;
        if (visited[x][y]) return;

        temp.add(new int[]{x, y});
        visited[x][y] = true;
        
        int i = x + directions[grid[x][y] - 1][0];
        int j = y + directions[grid[x][y] - 1][1];
        dfs(grid, i, j, temp, visited);
    }
}
// @lc code=end

