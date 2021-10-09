package BFS.Dijkstra;

import java.util.PriorityQueue;

class Solution {
    // time = O(m * n * log(m * n)), space = O(m * n)
    private static final int[][] DIRECTIONS = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        int m = maze.length, n = maze[0].length;
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
        pq.offer(new int[]{0, start[0], start[1]});
        boolean[][] visited = new boolean[m][n];
        
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int d = cur[0];
            int x = cur[1];
            int y = cur[2];
            if (visited[x][y]) continue;
            visited[x][y] = true;
            
            if (x == destination[0] && y == destination[1]) return d;
            
            for (int[] dir : DIRECTIONS) {
                int i = x, j = y, dist = 0;
                while (i + dir[0] >= 0 && i + dir[0] < m && j + dir[1] >= 0 && j + dir[1] < n && maze[i + dir[0]][j + dir[1]] != 1) {
                    dist++;
                    i += dir[0];
                    j += dir[1];
                }
                if (visited[i][j]) continue;
                pq.offer(new int[]{d + dist, i, j});
            }
        }
        return -1;
    }
}