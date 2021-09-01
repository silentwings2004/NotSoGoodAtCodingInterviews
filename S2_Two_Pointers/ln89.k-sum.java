/*
 * @ln app=lintcode id=89 lang=java
 *
 * [89] K Sum
 */

// @ln code=start
class Solution {
    // time = O(nkt), space = O(nkt)
    public int kSum(int[] A, int k, int target) {
        // corner case
        if (A == null || A.length == 0) return 0;

        int n = A.length;
        int[][][] f = new int[n + 1][k + 1][target + 1]; // 前i个数里选出k个,它们的和为s
        f[0][0][0] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= k; j++) {
                for (int s = 0; s <= target; s++) {
                    f[i][j][s] = f[i - 1][j][s];
                    if (j >= 1 && s >= A[i - 1]) {
                        f[i][j][s] += f[i - 1][j - 1][s - A[i - 1]];
                    }
                }
            }
        }
        return f[n][k][target];
    }    
}
// @ln code=end