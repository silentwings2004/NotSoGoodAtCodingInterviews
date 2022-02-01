/*
 * @lc app=leetcode id=2025 lang=java
 *
 * [2025] Maximum Number of Ways to Partition an Array
 */

// @lc code=start
class Solution {
    public int waysToPartition(int[] nums, int k) {
        int n = nums.length;
        long sum = 0;
        for (int x : nums) sum += x;

        long[] pre = new long[n];
        pre[0] = nums[0];
        for (int i = 1; i < n; i++) {
            pre[i] = pre[i - 1] + nums[i];
        }

        long[] suf = new long[n];
        suf[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suf[i] = suf[i + 1] + nums[i];
        }

        int[] res = new int[n];
        HashMap<Long, Integer> map = new HashMap<>();
        // from front to rear
        for (int i = 0; i < n; i++) {
            long newSum = sum - nums[i] + k;
            if (newSum % 2 == 0) {
                res[i] += map.getOrDefault(newSum / 2, 0);
            }
            map.put(pre[i], map.getOrDefault(pre[i], 0) + 1);
        }

        // from rear to front
        map.clear();
        for (int i = n - 1; i >= 0; i--) {
            long newSum = sum - nums[i] + k;
            if (newSum % 2 == 0) {
                res[i] += map.getOrDefault(newSum / 2, 0);
            }
            map.put(suf[i], map.getOrDefault(suf[i], 0) + 1);
        }

        int ans = 0;
        for (int i = 0; i < n - 1; i++) {
            if (sum % 2 == 0 && pre[i] == sum / 2) ans++;
        }

        for (int x : res) ans = Math.max(ans, x);
        return ans;
    }
}
// @lc code=end

