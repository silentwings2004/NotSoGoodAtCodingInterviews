import java.util.HashMap;
import java.util.List;

/*
 * @lc app=leetcode id=446 lang=java
 *
 * [446] Arithmetic Slices II - Subsequence
 */

// @lc code=start
class Solution {
    // S1: DP
    // time = O(n^2), space = O(n^2)
    public int numberOfArithmeticSlices(int[] nums) {
        int n = nums.length;
        HashMap<Long, Integer>[] dp = new HashMap[n];
        for (int i = 0; i < n; i++) dp[i] = new HashMap<>();

        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                long diff = (long) nums[i] - (long) nums[j];
                dp[i].put(diff, dp[i].getOrDefault(diff, 0) + dp[j].getOrDefault(diff, 0) + 1);
                res += dp[j].getOrDefault(diff, 0);
            }
        }
        return res;
    }

    // S2: DP
    // time = O(n^2), space = O(n^2)
    public int numberOfArithmeticSlices2(int[] nums) {
        int n = nums.length;
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.putIfAbsent(nums[i], new ArrayList<>());
            map.get(nums[i]).add(i);
        }

        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                long diff = (long) nums[i] - (long) nums[j];
                long x = (long) nums[j] - diff;
                if (x > Integer.MAX_VALUE || x < Integer.MIN_VALUE) continue;
                if (map.containsKey((int) x)) {
                    for (int k : map.get((int) x)) {
                        if (k < j) dp[j][i] += dp[k][j] + 1;
                    }
                }
            }
        }

        int res = 0;
        for (int j = 0; j < n; j++) {
            for (int i = j + 1; i < n; i++) {
                res += dp[j][i];
            }
        }
        return res;
    }
}
// @lc code=end

