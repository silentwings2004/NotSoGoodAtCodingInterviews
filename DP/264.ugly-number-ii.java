import java.util.PriorityQueue;

/*
 * @lc app=leetcode id=264 lang=java
 *
 * [264] Ugly Number II
 */

// @lc code=start
class Solution {
    // S1: PQ
    // time = O(nlogn), space = O(n)
    public int nthUglyNumber(int n) {
        PriorityQueue<Long> pq = new PriorityQueue<>();
        pq.offer(1L);

        for (int t = 0; t < n; t++) {
            long cur = pq.poll();
            if (t == n - 1) return (int) cur;
            while (!pq.isEmpty() && pq.peek() == cur) pq.poll();

            pq.offer(cur * 2);
            pq.offer(cur * 3);
            pq.offer(cur * 5);
        }
        return -1;
    }

    // S2: Greedy
    // time = O(n), space = O(n)
    public int nthUglyNumber2(int n) {
        int i = 0, j = 0, k = 0;
        int[] res = new int[n];
        res[0] = 1;

        for (int t = 0; t < n - 1; t++) {
            int cur = Math.min(res[i] * 2, Math.min(res[j] * 3, res[k] * 5));
            res[t + 1] = cur;
            if (cur == res[i] * 2) i++;
            if (cur == res[j] * 3) j++;
            if (cur == res[k] * 5) k++;
        }
        return res[n - 1];
    }
}
// @lc code=end

