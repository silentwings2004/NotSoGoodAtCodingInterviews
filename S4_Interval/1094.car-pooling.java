/*
 * @lc app=leetcode id=1094 lang=java
 *
 * [1094] Car Pooling
 */

// @lc code=start
class Solution {
    // time = O(nlogn), space = O(n)
    public boolean carPooling(int[][] trips, int capacity) {
        List<int[]> diff = new ArrayList<>(); // {stop, diff}
        for (int[] trip : trips) {
            diff.add(new int[]{trip[1], trip[0]});
            diff.add(new int[]{trip[2], -trip[0]});
        }

        Collections.sort(diff, (o1, o2) -> o1[0] != o2[0] ? o1[0] - o2[0] : o1[1] - o2[1]);
        
        int total = 0;
        for (int[] d : diff) {
            total += d[1];
            if (total > capacity) return false;
        }
        return true;
    }
}
// @lc code=end

