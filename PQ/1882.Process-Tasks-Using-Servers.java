package PQ;

import java.util.*;

class Solution {
    // time = O((m + n) * logm), space = O(m)
    public int[] assignTasks(int[] servers, int[] tasks) {
        PriorityQueue<int[]> freePQ = new PriorityQueue<>((o1, o2) -> o1[0] != o2[0] ? o1[0] - o2[0] : o1[1] - o2[1]);
        PriorityQueue<int[]> busyPQ = new PriorityQueue<>((o1, o2) -> o1[0] != o2[0] ? o1[0] - o2[0] : (o1[1] != o2[1] ? o1[1] - o2[1] : o1[2] - o2[2]));

        for (int i = 0; i < servers.length; i++) { // O(m)
            freePQ.offer(new int[]{servers[i], i}); // O(logm)
        }

        Queue<Integer> jobs = new LinkedList<>();
        int[] res = new int[tasks.length];

        for (int j = 0; j < tasks.length; j++) { // O(n)
            jobs.offer(j);
            while (!busyPQ.isEmpty() && busyPQ.peek()[0] <= j) {
                int[] cur = busyPQ.poll();
                freePQ.offer(new int[]{cur[1], cur[2]});
            }
            while (!jobs.isEmpty() && !freePQ.isEmpty()) {
                int job = jobs.poll();
                int[] cur = freePQ.poll();
                res[job] = cur[1];
                busyPQ.offer(new int[]{j + tasks[job], cur[0], cur[1]});
            }
        }

        while (!jobs.isEmpty()) { // freePQ must be empty! No longer need freePQ, always let task wait for the servers
            int job = jobs.poll();
            int[] cur = busyPQ.poll();
            res[job] = cur[2];
            busyPQ.offer(new int[]{cur[0] + tasks[job], cur[1], cur[2]});
        }
        return res;
    }
}