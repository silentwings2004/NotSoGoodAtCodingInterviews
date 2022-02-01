import java.util.PriorityQueue;
import java.util.Queue;

/*
 * @lc app=leetcode id=1882 lang=java
 *
 * [1882] Process Tasks Using Servers
 */

// @lc code=start
class Solution {
    // time = O((m + n) * logm), space = O(m)
    public int[] assignTasks(int[] servers, int[] tasks) {
        PriorityQueue<int[]> free = new PriorityQueue<>((o1, o2) -> o1[0] != o2[0] ? o1[0] - o2[0] : o1[1] - o2[1]);
        PriorityQueue<int[]> busy = new PriorityQueue<>((o1, o2) -> o1[0] != o2[0] ? o1[0] - o2[0] : (o1[1] != o2[1] ? o1[1] - o2[1] : o1[2] - o2[2]));

        for (int i = 0; i < servers.length; i++) {
            free.offer(new int[]{servers[i], i});
        }

        int[] res = new int[tasks.length];
        Queue<Integer> queue = new LinkedList<>();
        for (int j = 0; j < tasks.length; j++) {
            queue.offer(j);
            // busy -> free
            while (!busy.isEmpty() && busy.peek()[0] <= j) {
                int[] cur = busy.poll();
                int t = cur[0], w = cur[1], idx = cur[2];
                free.offer(new int[]{w, idx});
            }

            // dispatch tasks
            while (!free.isEmpty() && !queue.isEmpty()) {
                int job = queue.poll();
                int[] cur = free.poll();
                int w = cur[0], idx = cur[1];
                res[job] = idx;
                busy.offer(new int[]{j + tasks[job], w, idx});
            }
        }

        // jobs waiting for the servers
        while (!queue.isEmpty()) {
            int job = queue.poll();
            int[] cur = busy.poll();
            int t = cur[0], w = cur[1], idx = cur[2];
            res[job] = idx;
            busy.offer(new int[]{t + tasks[job], w, idx});
        }
        return res;
    }
}
// @lc code=end

