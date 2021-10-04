/*
 * @lc app=leetcode id=1024 lang=java
 *
 * [1024] Video Stitching
 */

// @lc code=start
class Solution {
    // time = O(nlogn), space = O(1)
    public int videoStitching(int[][] clips, int time) {
        Arrays.sort(clips, (o1, o2) -> o1[0] - o2[0]); // 左端点相等时，长区间优先

        if (clips[0][0] != 0) return -1;
        
        int idx = 0, right = 0, count = 0, n = clips.length;

        while (idx < n) {
            int farReach = right;
            while (idx < n && clips[idx][0] <= right) {
                farReach = Math.max(farReach, clips[idx][1]);
                idx++;
            }
            if (farReach == right) return -1;
            
            count++;
            if (farReach >= time) return count;
            right = farReach;
        }
        return -1;
    }
}
// @lc code=end

