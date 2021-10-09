/*
 * @lc app=leetcode id=253 lang=java
 *
 * [253] Meeting Rooms II
 */

// @lc code=start
class Solution {
    // time = O(nlogn), space = O(n)
    public int minMeetingRooms(int[][] intervals) {
        List<int[]> diff = new ArrayList<>();
        for (int[] interval : intervals) {
            diff.add(new int[]{interval[0], 1});
            diff.add(new int[]{interval[1], -1});
        }

        Collections.sort(diff, (o1, o2) -> o1[0] != o2[0] ? o1[0] - o2[0] : o1[1] - o2[1]);

        int count = 0, max = 0;
        for (int[] x : diff) {
            count += x[1];
            max = Math.max(count, max);
        }
        return max;
    }
}
// @lc code=end

