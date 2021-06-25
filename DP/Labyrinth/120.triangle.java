/*
 * @lc app=leetcode id=120 lang=java
 *
 * [120] Triangle
 */

// @lc code=start
class Solution {
    // time = O(n^2), space = O(n)
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] dp = new int[n];
        dp[0] = triangle.get(0).get(0);

        for (int i = 1; i < n; i++) {
            int[] temp = dp.clone();
            for (int j = 0; j <= i; j++) {
                if (j == 0) {
                    dp[j] = temp[0] + triangle.get(i).get(j);
                } else if (j == i) {
                    dp[j] = temp[i - 1] + triangle.get(i).get(j);   
                } else {
                    dp[j] = Math.min(temp[j - 1], temp[j]) + triangle.get(i).get(j);
                }
            }
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            res = Math.min(dp[i], res);
        }
        return res;
    }
}
// @lc code=end

