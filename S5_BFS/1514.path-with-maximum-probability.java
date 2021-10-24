import java.util.PriorityQueue;
import java.util.Queue;

/*
 * @lc app=leetcode id=1514 lang=java
 *
 * [1514] Path with Maximum Probability
 */

// @lc code=start
class Solution {
    // S1: BFS
    // time = O(m + n), space = O(m * n)
    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        List<Pair>[] graph = new List[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for (int i = 0; i < edges.length; i++) {
            graph[edges[i][0]].add(new Pair(edges[i][1], succProb[i]));
            graph[edges[i][1]].add(new Pair(edges[i][0], succProb[i]));
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        double[] prob = new double[n];
        prob[start] = 1;

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (Pair next : graph[cur]) {
                if (prob[next.node] >= prob[cur] * next.val) continue;
                prob[next.node] = prob[cur] * next.val;
                queue.offer(next.node);
            }
        }
        return prob[end];
    }

    private class Pair {
        private int node;
        private double val;
        public Pair(int node, double val) {
            this.node = node;
            this.val = val;
        }
    }

    // S2: Dijkstra
    // time = O(mlogm), space = O(n)
    public double maxProbability2(int n, int[][] edges, double[] succProb, int start, int end) {
        List<Pair>[] graph = new List[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for (int i = 0; i < edges.length; i++) {
            graph[edges[i][0]].add(new Pair(edges[i][1], -Math.log(succProb[i])));
            graph[edges[i][1]].add(new Pair(edges[i][0], -Math.log(succProb[i])));
        }

        PriorityQueue<Pair> pq = new PriorityQueue<>((o1, o2) -> Double.compare(o1.val, o2.val));
        pq.offer(new Pair(start, 0));
        double[] dist = new double[n];
        Arrays.fill(dist, -1);

        while (!pq.isEmpty()) {
            Pair cur = pq.poll();
            int node = cur.node;
            double val = cur.val;

            if (node == end) return Math.exp(-val);

            if (dist[node] != -1) continue;
            dist[node] = val;
            
            for (Pair next : graph[node]) {
                if (dist[next.node] != -1) continue;
                pq.offer(new Pair(next.node, next.val + dist[node]));
            }
        }
        return 0;
    }
}
// @lc code=end

