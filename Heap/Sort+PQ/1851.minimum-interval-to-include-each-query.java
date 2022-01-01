import java.util.Arrays;
import java.util.PriorityQueue;

/*
 * @lc app=leetcode id=1851 lang=java
 *
 * [1851] Minimum Interval to Include Each Query
 */

// @lc code=start
class Solution {
    // time = O(mlogm + nlogn), space = O(m + n)
    public int[] minInterval(int[][] intervals, int[] queries) {
        List<int[]> qs = new ArrayList<>();
        for (int i = 0; i < queries.length; i++) {
            qs.add(new int[]{queries[i], i});
        }

        Collections.sort(qs, (o1, o2) -> o1[0] - o2[0]);
        Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
        int[] res = new int[queries.length];
        Arrays.fill(res, -1);
        int i = 0;
        for (int[] query : qs) {
            int q = query[0];
            int idx = query[1];

            while (i < intervals.length && intervals[i][0] <= q) {
                pq.offer(new int[]{intervals[i][1] - intervals[i][0] + 1, intervals[i][1]});
                i++;
            }
            while (!pq.isEmpty() && pq.peek()[1] < q) pq.poll();
            if (!pq.isEmpty()) res[idx] = pq.peek()[0];
        }
        return res;
    }
}
// @lc code=end

