### Dijkstra

[模板]

private static final int[][] DIRECTIONS = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
pq.offer(new int[]{start_point});
boolean[] visited = new boolean[n]；


int res = 0; 
while (!pq.isEmpty()) {
    int[] top = pq.poll();
    int h = top[0];
    int i = top[1];
    int j = top[2];

    if (visited[cur]) continue;
    visited[cur] = true;
    
    // work with res

    for (int[] dir : DIRECTIONS) {
        int ii = i + dir[0];
        int jj = j + dir[1];
        if (ii >= 0 && ii < m && jj >= 0 && jj < n && !visited[ii][jj]) {
            pq.offer(new int[]{neibors});
        }
    }
}
return res;

