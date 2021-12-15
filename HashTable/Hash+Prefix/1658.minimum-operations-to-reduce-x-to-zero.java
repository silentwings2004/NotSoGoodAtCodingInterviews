import java.util.HashMap;

/*
 * @lc app=leetcode id=1658 lang=java
 *
 * [1658] Minimum Operations to Reduce X to Zero
 */

// @lc code=start
class Solution {
    // time = O(n), space = O(n)
    public int minOperations(int[] nums, int x) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int n = nums.length, res = Integer.MAX_VALUE;
        int presum = 0, sufsum = 0;

        for (int i = 0; i < n; i++) {
            presum += nums[i];
            if (!map.containsKey(presum)) map.put(presum, i);
        }

        if (map.containsKey(x)) res = map.get(x) + 1;

        for (int b = n - 1; b >= 0; b--) {
            sufsum += nums[b];
            int pre = x - sufsum;
            if (map.containsKey(pre)) {
                int a = map.get(pre);
                if (a < b) res = Math.min(res, a + 1 + n - b);
            }
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }
}
// @lc code=end

