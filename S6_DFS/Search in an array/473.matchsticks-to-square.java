/*
 * @lc app=leetcode id=473 lang=java
 *
 * [473] Matchsticks to Square
 */

// @lc code=start
class Solution {
    // time = O(4^n), space = O(n)
    public boolean makesquare(int[] matchsticks) {
        int n = matchsticks.length;

        Arrays.sort(matchsticks);

        int total = 0;
        for (int x : matchsticks) total += x;
        if (total % 4 != 0) return false;
        boolean[] visited = new boolean[n];
        return dfs(matchsticks, 0, 0, 0, total / 4, visited);
    }

    private boolean dfs(int[] nums, int idx, int group, int sum, int side, boolean[] visited) {
        // base case
        if (group == 4) return true;
        if (sum > side) return false;
        if (sum == side) return dfs(nums, 0, group + 1, 0, side, visited);

        int last = -1;
        for (int i = idx; i < nums.length; i++) {
            if (visited[i]) continue;
            if (nums[i] == last) continue;
            visited[i] = true;
            last = nums[i];
            if (dfs(nums, i + 1, group, sum + nums[i], side, visited)) return true;
            visited[i] = false;
        }
        return false;
    }
}
// @lc code=end

