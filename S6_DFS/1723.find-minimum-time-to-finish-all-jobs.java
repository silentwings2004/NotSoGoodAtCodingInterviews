/*
 * @lc app=leetcode id=1723 lang=java
 *
 * [1723] Find Minimum Time to Finish All Jobs
 */

// @lc code=start
class Solution {
    // S1: dp + state compression
    // time = O(k * 3^n), space = O(k * 2^n)
    public int minimumTimeRequired(int[] jobs, int k) {
        int n = jobs.length;
        int[][] dp = new int[k + 1][1 << n];
        int[] time = new int[1 << n];
        for (int state = 0; state < (1 << n); state++) {
            int sum = 0;
            for (int i = 0; i < n; i++) {
                if (((state >> i) & 1) == 1) sum += jobs[i];
            }
            time[state] = sum;
        }

        // init dp
        for (int state = 0; state < (1 << n); state++) {
            dp[0][state] = Integer.MAX_VALUE;
        }
        dp[0][0] = 0;

        for (int i = 1; i <= k; i++) {
            for (int state = 0; state < (1 << n); state++) {
                dp[i][state] = Integer.MAX_VALUE;
                for (int subset = state; subset > 0; subset = (subset - 1) & state) {
                    dp[i][state] = Math.min(dp[i][state], Math.max(dp[i - 1][state - subset], time[subset]));
                }
            }
        }
        return dp[k][(1 << n) - 1];
    }

    // S2: dfs + bs
    // time = O(2^n), space = O(2^n)
    public int minimumTimeRequired2(int[] jobs, int k) {
        int n = jobs.length;
        int[] time = new int[1 << n];
        for (int state = 0; state < (1 << n); state++) {
            int sum = 0;
            for (int i = 0; i < n; i++) {
                if (((state >> i) & 1) == 1) {
                    sum += jobs[i];
                }
            }
            time[state] = sum;
        }

        boolean[][] memo = new boolean[k][1 << n];
        
        int left = 1, right = 0;
        for (int job : jobs) right += job;
        while (left < right) {
            for (int i = 0; i < k; i++) Arrays.fill(memo[i], true);
            int mid = left + (right - left) / 2;
            if (dfs((1 << n) - 1, mid, 0, k, time, memo)) right = mid;
            else left = mid + 1;
        }
        return left;
    }

    private boolean dfs(int state, int th, int idx, int k, int[] time, boolean[][] memo) {
        // base case
        if (state == 0) return true;
        if (idx == k) return false;

        if (!memo[idx][state]) return false;

        for (int subset = state; subset > 0; subset = (subset - 1) & state) {
            if (time[subset] > th) continue;
            if (dfs(state - subset, th, idx + 1, k, time, memo)) return true; 
        }
        memo[idx][state] = false;
        return false;
    }

    // S3: dfs + bs
    // time = O(nlogn + logS * k^n), space = O(k + n)
    public int minimumTimeRequired3(int[] jobs, int k) {
        int n = jobs.length;
        Arrays.sort(jobs);
        int i = 0, j = n - 1;
        while (i < j) {
            int temp = jobs[i];
            jobs[i++] = jobs[j];
            jobs[j--] = temp;
        }

        int left = 1, right = 0;
        for (int job : jobs) right += job;
        while (left < right) {
            int[] workers = new int[k];
            int mid = left + (right - left) / 2;
            if (helper(workers, mid, 0, jobs)) right = mid;
            else left = mid + 1;
        }
        return left;
    }

    private boolean helper(int[] workers, int th, int idx, int[] jobs) {
        int k = workers.length, n = jobs.length;
        // base case
        if (idx == n) return true;

        boolean flag = false;
        for (int i = 0; i < k; i++) {
            if (workers[i] + jobs[idx] > th) continue;

            // pruning 
            if (workers[i] == 0) { // free worker
                if (flag) continue; // jobs[i] has been assigned to one free worker once in dfs, no need to do again
                else flag = true; // assign it to the current worker and set the flag as true to avoid doing it repeatedly
            }
            workers[i] += jobs[idx];
            if (helper(workers, th, idx + 1, jobs)) return true;
            workers[i] -= jobs[idx];
        }
        return false;
    }
}
// @lc code=end

