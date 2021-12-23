/*
 * @lc app=leetcode id=1353 lang=java
 *
 * [1353] Maximum Number of Events That Can Be Attended
 */
import java.util.*;
// @lc code=start
class Solution {
    public int maxEvents(int[][] events) {
        Arrays.sort(events, (o1, o2) -> o1[0] - o2[0]);

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        int i = 0, res = 0;
        for (int day = 1; day <= 100000; day++) {
            while (i < events.length && events[i][0] <= day) {
                pq.offer(events[i][1]);
                i++;
            }
            while (!pq.isEmpty() && pq.peek() < day) pq.poll();
            if (!pq.isEmpty()) {
                pq.poll();
                res++;
            }
            if (res == events.length) break;
        }        
        return res;
    }
}
// @lc code=end

