/*
 * @lc app=leetcode id=1552 lang=java
 *
 * [1552] Magnetic Force Between Two Balls
 */

// @lc code=start
class Solution {
    // time = O(nlogn), space = O(1)
    public int maxDistance(int[] position, int m) {
        // corner case
        if (position == null || position.length == 0) return 0;

        Arrays.sort(position);

        int n = position.length;
        int left = 1, right = position[n - 1] - position[0];
        while (left < right) {
            int mid = right - (right - left) / 2;
            if (helper(position, mid, m)) left = mid;
            else right = mid - 1;
        }
        return left;
    }

    private boolean helper(int[] nums, int t, int m) {
        int n = nums.length, count = 1;
        for (int i = 0; i < n; i++) {
            int j = i;
            while (j < n && nums[j] - nums[i] < t) {
                j++;
            }
            if (j == n) break;
            count++;
            if (count == m) return true;
            i = j - 1;
        }
        return false;

    }
}
// @lc code=end

