/*
 * @lc app=leetcode id=1326 lang=java
 *
 * [1326] Minimum Number of Taps to Open to Water a Garden
 */

// @lc code=start
class Solution {
    // time = O(nlogn), space = O(n)
    public int minTaps(int n, int[] ranges) {
        int[][] intervals = new int[n + 1][2];
        for (int i = 0; i <= n; i++) {
            intervals[i] = new int[]{i - ranges[i], i + ranges[i]};
        }

        Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);

        int count = 0, idx = 0, right = 0;
        while (idx < n + 1) {
            int far = right;
            while (idx < n + 1 && intervals[idx][0] <= right) {
                far = Math.max(far, intervals[idx][1]);
                idx++;
            }
            if (far == right) return -1;

            count++;
            if (far >= n) return count;
            right = far;
        }
        return -1;
    }
}
// @lc code=end

