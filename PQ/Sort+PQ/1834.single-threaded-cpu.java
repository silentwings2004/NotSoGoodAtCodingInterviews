import java.util.PriorityQueue;

/*
 * @lc app=leetcode id=1834 lang=java
 *
 * [1834] Single-Threaded CPU
 */

// @lc code=start
class Solution {
     // time = O(nlogn), space = O(n)
    public int[] getOrder(int[][] tasks) {
        int n  = tasks.length;
        int[][] jobs = new int[n][3];
        for (int i = 0; i < n; i++) {
            jobs[i][0] = tasks[i][0];
            jobs[i][1] = tasks[i][1];
            jobs[i][2] = i;
        }

        Arrays.sort(jobs, (o1, o2) -> o1[0] - o2[0]);

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] != o2[0] ? o1[0] - o2[0] : o1[1] - o2[1]);

        int curTime = 0, i = 0;
        int[] res = new int[n];

        for (int[] job : jobs) {
            while (!pq.isEmpty() && curTime < job[0]) {
                res[i++] = pq.peek()[1];
                curTime += pq.poll()[0];
            }
            curTime = Math.max(curTime, job[0]);
            pq.offer(new int[]{job[1], job[2]});
        }

        while (!pq.isEmpty()) {
            res[i++] = pq.poll()[1];
        }
        return res;
    }
}
// @lc code=end

