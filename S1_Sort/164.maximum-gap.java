/*
 * @lc app=leetcode id=164 lang=java
 *
 * [164] Maximum Gap
 */

// @lc code=start
class Solution {
    // time = O(n), space = O(n)
    public int maximumGap(int[] nums) {
        // corner case
        if (nums == null || nums.length == 0) return 0;

        int n = nums.length;
        int min = nums[0], max = nums[0];
        for (int num : nums) {
            max = Math.max(max, num);
            min = Math.min(min, num);
        }

        int gap = (int)Math.ceil((double)(max - min) / (n - 1));
        if (gap == 0) return 0;

        int[][] bucket = new int[n][2]; // {bmin, bmax}
        for (int i = 0; i < n; i++) {
            bucket[i][0] = Integer.MAX_VALUE;
            bucket[i][1] = Integer.MIN_VALUE;
        }

        for (int num : nums) {
            int idx = (num - min) / gap;
            bucket[idx][0] = Math.min(num, bucket[idx][0]);
            bucket[idx][1] = Math.max(num, bucket[idx][1]);
        }

        int maxGap = 0, pre = bucket[0][1];
        for (int i = 1; i < n; i++) {
            if (bucket[i][0] > bucket[i][1]) continue; // bucket is untouched!
            maxGap = Math.max(bucket[i][0] - pre, maxGap);
            pre = bucket[i][1];
        }
        return maxGap;
    }
}
// @lc code=end

