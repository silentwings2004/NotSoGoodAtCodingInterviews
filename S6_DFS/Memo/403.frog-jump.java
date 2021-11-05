/*
 * @lc app=leetcode id=403 lang=java
 *
 * [403] Frog Jump
 */

// @lc code=start
class Solution {
    // time = O(n^2), space = O(n^2)
    HashSet<Integer> set;
    HashSet<String> failed;
    public boolean canCross(int[] stones) {
        set = new HashSet<>();
        failed = new HashSet<>();
        for (int x : stones) set.add(x);
        return dfs(stones, 0, 0);
    }

    private boolean dfs(int[] stones, int pos, int jump) {
        int n = stones.length;
        if (pos == stones[n - 1]) return true;
        if (!set.contains(pos)) return false;
        if (failed.contains(pos + "#" + jump)) return false;

        if (jump > 1 && dfs(stones, pos + jump - 1, jump - 1)) return true;
        if (jump > 0 && dfs(stones, pos + jump, jump)) return true;
        if (dfs(stones, pos + jump + 1, jump + 1)) return true;
        failed.add(pos + "#" + jump);
        return false;
    }
}
// @lc code=end

