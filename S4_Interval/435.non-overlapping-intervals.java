/*
 * @lc app=leetcode id=435 lang=java
 *
 * [435] Non-overlapping Intervals
 */

// @lc code=start
class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        // corner case
        if (intervals == null || intervals.length == 0 || intervals[0] == null || intervals[0].length == 0) {
            return 0;
        }

        Arrays.sort(intervals, (o1, o2) -> o1[1] - o2[1]);

        int n = intervals.length;
        int i = 0, count = 0;

        while (i < n) {
            count++;
            int j = i + 1;
            while (j < n && intervals[j][0] < intervals[i][1]) j++;
            i = j;
        }
        return n - count;
    }
}
// @lc code=end

