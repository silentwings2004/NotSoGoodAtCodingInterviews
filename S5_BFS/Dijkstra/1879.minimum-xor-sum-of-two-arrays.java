import java.util.PriorityQueue;

/*
 * @lc app=leetcode id=1879 lang=java
 *
 * [1879] Minimum XOR Sum of Two Arrays
 */

// @lc code=start
class Solution {
    // S1: DP
    // time = O(n^2 * 2^n), space = O(2^n)
    public int minimumXORSum(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int[] dp = new int[1 << n];
        dp[0] = 0;


        for (int j = 0; j < n; j++) {
            int[] dp2 = dp.clone();
            for (int state = 0; state < (1 << n); state++) {
                dp[state] = Integer.MAX_VALUE / 2;
                for (int i = 0; i < n; i++) { // 先把bit 1挑出来，遍历nums1里所有的元素
                    if (((state >> i) & 1) == 1) {  // 这种情况在state里出现过
                        dp[state] = Math.min(dp[state], dp2[state - (1 << i)] + (nums1[i] ^ nums2[j]));
                    }
                }
            }
        }
        return dp[(1 << n) - 1];
    }

    // S1.2: DP + Gospher's hack
    // time = O(n^2 * 2^n), space = O(2^n)
    public int minimumXORSum2(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int[] dp = new int[1 << n];
        dp[0] = 0;


        for (int j = 0; j < n; j++) {
            int[] dp2 = dp.clone();
            int k = j + 1;
            int state = (1 << k) - 1;
            while (state < (1 << n)) {
                // do something(state)
                dp[state] = Integer.MAX_VALUE / 2;
                for (int i = 0; i < n; i++) {
                    if (((state >> i) & 1) == 1) {
                        dp[state] = Math.min(dp[state], dp2[state - (1 << i)] + (nums1[i] ^ nums2[j]));
                    } 
                }
                
                int c = state & -state;
                int r = state + c;
                state = (((r ^ state) >> 2) / c) | r;
            }
        }
        return dp[(1 << n) - 1];
    }

    // S3: Dijkstra
    // time = O(n * 2^n), space = O(2^n)
    public int minimumXORSum3(int[] nums1, int[] nums2) {
        int m = nums1.length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]); // {cost, state}
        pq.offer(new int[]{0, 0});
        boolean[] visited = new boolean[1 << m];

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int cost = cur[0], state = cur[1];
            if (visited[state]) continue;
            visited[state] = true;

            int j = Integer.bitCount(state);
            if (j == m) return cost;

            for (int i = 0; i < m; i++) {
                if (((state >> i) & 1) == 1) continue;
                int newState = state | (1 << i);
                pq.offer(new int[]{cost + (nums1[i] ^ nums2[j]), newState});
            }
        }
        return -1;
    }
}
// @lc code=end

