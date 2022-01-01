import java.util.PriorityQueue;

/*
 * @lc app=leetcode id=1792 lang=java
 *
 * [1792] Maximum Average Pass Ratio
 */

// @lc code=start
class Solution {
    // time = O(nlogn), space = O(n)
    public double maxAverageRatio(int[][] classes, int extraStudents) {
        PriorityQueue<double[]> pq = new PriorityQueue<>((o1, o2) -> Double.compare(o2[0], o1[0]));
        for (int[] c : classes) {
            double p = c[0], t = c[1];
            pq.offer(new double[]{(p + 1) / (t + 1) - p / t, p, t});
        }       

        for (int i = 0; i < extraStudents; i++) {
            double[] cur = pq.poll();
            double dr = cur[0], p = cur[1], t = cur[2];
            p++;
            t++;
            pq.offer(new double[]{(p + 1) / (t + 1) - p / t, p, t});
        }

        double sum = 0;
        while (!pq.isEmpty()) {
            double[] cur = pq.poll();
            double p = cur[1], t = cur[2];
            sum += p / t;
        }
        return sum / classes.length;
    }
}
// @lc code=end

