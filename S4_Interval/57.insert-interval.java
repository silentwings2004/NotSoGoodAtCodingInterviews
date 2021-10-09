/*
 * @lc app=leetcode id=57 lang=java
 *
 * [57] Insert Interval
 */

// @lc code=start
class Solution {
    // S1: diff array
    // time = O(nlogn), space = O(n)
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> diff = new ArrayList<>();
        for (int[] interval : intervals) {
            diff.add(new int[]{interval[0], 1});
            diff.add(new int[]{interval[1], -1});
        }
        diff.add(new int[]{newInterval[0], 1});
        diff.add(new int[]{newInterval[1], -1});

        Collections.sort(diff, (o1, o2) -> o1[0] != o2[0] ? o1[0] - o2[0] : o2[1] - o1[1]);

        int start = -1, end = -1, count = 0;
        List<int[]> res = new ArrayList<>();
        for (int[] x : diff) {
            count += x[1];
            if (x[1] == 1) {
                if (count == 1) start = x[0];
            } else {
                if (count == 0) {
                    end = x[0];
                    res.add(new int[]{start, end});
                }
            }
        }
        int[][] ans = new int[res.size()][2];
        for (int i = 0; i < res.size(); i++) ans[i] = res.get(i);
        return ans;
    }

    // S2: insert
    // time = O(n), space = O(n)
    public int[][] insert2(int[][] intervals, int[] newInterval) {
        List<int[]> res = new ArrayList<>();

        for (int[] interval : intervals) {
            if (interval[1] < newInterval[0]) {
                res.add(interval);
            } else if (newInterval[1] < interval[0]) {
                res.add(newInterval);
                newInterval = interval;
            } else if (newInterval[0] < interval[1] || newInterval[1] > interval[0]){
                int start = Math.min(interval[0], newInterval[0]);
                int end = Math.max(interval[1], newInterval[1]);
                newInterval = new int[]{start, end};
            }
        }
        res.add(newInterval); // newInterval不断向后推演，最后出loop时要加入res
        int[][] ans = new int[res.size()][2];
        for (int i = 0; i < res.size(); i++) ans[i] = res.get(i);
        return ans;
    }
}
// @lc code=end

