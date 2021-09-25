/*
 * @lc app=leetcode id=56 lang=java
 *
 * [56] Merge Intervals
 */

// @lc code=start
class Solution {
    public int[][] merge(int[][] intervals) {
        List<int[]> diff = new ArrayList<>();
        for (int[] interval : intervals) {
            diff.add(new int[]{interval[0], 1});
            diff.add(new int[]{interval[1], -1});
        }

        Collections.sort(diff, (o1, o2) -> o1[0] != o2[0] ? o1[0] - o2[0] : o2[1] - o1[1]);

        int count = 0, start = -1, end = -1;
        List<int[]> res = new ArrayList<>();
        for (int[] x : diff) {
            if (x[1] == 1) {
                count++;
                if (count == 1) start = x[0];
            } else {
                count--;
                if (count == 0) {
                    end = x[0];
                    res.add(new int[]{start, end});
                }
            }
        }
        int[][] ans = new int[res.size()][2];
        for (int i = 0; i < res.size(); i++) {
            ans[i] = res.get(i);
        }
        return ans;
    }
}
// @lc code=end

