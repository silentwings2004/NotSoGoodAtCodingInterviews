/*
 * @lc app=leetcode id=1648 lang=java
 *
 * [1648] Sell Diminishing-Valued Colored Balls
 */

// @lc code=start
class Solution {
    // time = O(nlogM), space = O(1)
    public int maxProfit(int[] inventory, int orders) {
        // corner case
        if (inventory == null || inventory.length == 0 || orders <= 0) return 0;

        long left = 1, right = (int) 1e9;
        while (left < right) {
            long mid = left + (right - left) / 2;
            if (helper(inventory, mid) > orders) left = mid + 1;
            else right = mid;
        }
        
        long res = 0;
        long M = (long)(1e9 + 7);
        for (int i : inventory) {
            if (i > left) {
                res += (left + 1 + i) * (i - left) / 2;
                orders -= i - left;
            }
        }
        res += left * orders;
        return (int)(res % M);
    }

    private long helper(int[] nums, long t) {
        long sum = 0;
        for (int num : nums) sum += Math.max(num - t, 0);
        return sum;
    }
}
// @lc code=end

