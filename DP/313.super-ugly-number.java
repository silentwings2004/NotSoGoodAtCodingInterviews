/*
 * @lc app=leetcode id=313 lang=java
 *
 * [313] Super Ugly Number
 */

// @lc code=start
class Solution {
    // time = O(nlogk), space = O(n);
    public int nthSuperUglyNumber(int n, int[] primes) {
        int k = primes.length;
        int[] p = new int[k];
        int[] res = new int[n];
        res[0] = 1;

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
        for (int i = 0; i < k; i++) {
            pq.offer(new int[]{res[p[i]] * primes[i], i}); // {val, idx}
        }

        for (int t = 1; t < n; t++) {
            int cur = pq.peek()[0];
            res[t] = cur;

            while (!pq.isEmpty() && pq.peek()[0] == cur) {
                int i = pq.poll()[1];
                p[i]++;
                pq.offer(new int[]{res[p[i]] * primes[i], i});
            }
        }
        return res[n - 1];
    }
}
// @lc code=end

