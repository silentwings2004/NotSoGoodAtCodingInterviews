package BFS.Dijkstra;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

class Solution {
    // time = O(n + ElogE), space = O(n + E)
    public int networkDelayTime(int[][] times, int n, int k) {
        HashMap<Integer, List<int[]>> map = new HashMap<>();
        for (int[] t : times) {
            map.putIfAbsent(t[0], new ArrayList<>());
            map.get(t[0]).add(new int[]{t[1], t[2]});
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] -o2[0]); // {dist, node}
        pq.offer(new int[]{0, k}); 
        int[] visited = new int[n + 1];
        int res = 0;

        while (!pq.isEmpty()) {
            int[] top = pq.poll();
            int d = top[0], cur = top[1];
            if (visited[cur] == 1) continue;
            visited[cur] = 1;
            res = Math.max(d, res);

            if (map.containsKey(cur)) {
                for (int[] nexts : map.get(cur)) {
                    int node = nexts[0], weight = nexts[1];
                    pq.offer(new int[]{d + weight, node});
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            if (visited[i] == 0) return -1; // check if there is any node unvisited
        }

        return res;
    }
}