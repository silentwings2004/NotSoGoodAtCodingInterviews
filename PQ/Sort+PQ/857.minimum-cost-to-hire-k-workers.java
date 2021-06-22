/*
 * @lc app=leetcode id=857 lang=java
 *
 * [857] Minimum Cost to Hire K Workers
 */
import java.util.*;
// @lc code=start
class Solution {
    public double mincostToHireWorkers(int[] quality, int[] wage, int k) {
        int n = quality.length;
        int[][] person = new int[n][2];
        for (int i = 0; i < n; i++) {
            person[i][0] = quality[i];
            person[i][1] = wage[i];
        }

        Arrays.sort(person, (o1, o2) -> Double.compare(o1[1] * 1.0 / o1[0], o2[1] * 1.0 / o2[0]));

        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);

        int sum = 0;
        double res = 1e20;

        for (int[] p : person) {
            double unitWage = p[1] * 1.0 / p[0];
            sum += p[0];
            while (pq.size() > k - 1) {
                sum -= pq.poll();
            }
            if (pq.size() == k - 1) res = Math.min(res, unitWage * sum);
            pq.offer(p[0]);
        }
        return res;
    }
}
// @lc code=end

