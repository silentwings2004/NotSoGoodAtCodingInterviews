/*
 * @lc app=leetcode id=546 lang=java
 *
 * [546] Remove Boxes
 */

// @lc code=start
class Solution {
    // time = O(n^4), space = O(n^3)
    int[] boxes;
    int[][][] dp;
    public int removeBoxes(int[] boxes) {
        int n = boxes.length;
        this.boxes = boxes;
        dp = new int[n][n][n];
        return dfs(0, n - 1, 0);
    }

    private int dfs(int l, int r, int k) {
        // base case
        if (l > r) return 0;

        if (dp[l][r][k] != 0) return dp[l][r][k];

        // case 1: self-destruction of the last continuous O chunk
        int i = r, count = k;
        while (i >= l && boxes[i] == boxes[r]) { // i -> O
            i--;
            count++;
        }
        dp[l][r][k] = dfs(l, i, 0) + count * count; // i -> x, 末尾是O，基本格局是 ......OOO xxxxx OOOO

        // case 2: the last continuous O chuck merge with the former chunks after the middle x chunk is destroyed
        for (int j = i; j >= l; j--) { // j -> x
            if (boxes[j] != boxes[r]) continue;
            if (boxes[j] == boxes[r] && boxes[j] == boxes[j + 1]) continue; // only go dfs with the end O of the continuous x chunk
            dp[l][r][k] = Math.max(dp[l][r][k], dfs(l, j, count) + dfs(j + 1, i, 0));
        }
        return dp[l][r][k];
    }
}
// @lc code=end

