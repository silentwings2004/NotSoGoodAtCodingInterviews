package S11_DP;
import java.util.*;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: Triangle
 * Creater: Silentwings
 * Date: Apr, 2020
 * Description: 120. Triangle
 */
public class LC120_Triangle {
    /**
     * Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on
     * the row below.
     *
     * For example, given the following triangle
     *
     * [
     *      [2],
     *     [3,4],
     *    [6,5,7],
     *   [4,1,8,3]
     * ]
     * The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
     *
     * Note:
     *
     * Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the
     * triangle.
     * @param triangle
     * @return
     */
    // S1: DFS with carry 2^n  → carry on path & pathsum 上 → 下 分叉 ⇒ DFS (Time Limit Exceeded!!!)
    int min = Integer.MAX_VALUE;
    public int minimumTotal(List<List<Integer>> triangle) {
        // corner case
        if (triangle == null || triangle.size() == 0) return 0;

        return dfs(0, 0, 0, triangle);
    }

    private int dfs(int i, int j, int sum, List<List<Integer>> triangle) {
        // base case
        if (i == triangle.size()) {
            if (sum < min) {
                min = sum;
            }
            return min;
        }
        int val = triangle.get(i).get(j);
        // case 1:
        dfs(i + 1, j, sum + val, triangle);
        // case 2:
        dfs(i + 1, j + 1, sum + val, triangle);
        return min; // 先办事后recursion，答案出在leafnode下挂着的null
    }

    // S2: Recursion 2^n → 自下向上 先call后返值，不需要carry on一个变量，有冗余计算 (Time Limit Exceeded!!!)
    public int minimumTotal2(List<List<Integer>> triangle) {
        // corner case
        if (triangle == null || triangle.size() == 0) return 0;

        return divideConquer(0, 0, triangle);
    }

    private int divideConquer(int i, int j, List<List<Integer>> triangle) {
        // base case
        if (i == triangle.size()) return 0;
        int left = divideConquer(i + 1, j, triangle);
        int right = divideConquer(i + 1, j + 1, triangle);
        return triangle.get(i).get(j) + Math.min(left, right);
    }

    // S3: Recursion with 记忆化存储 to prune n^2
    // 每个位置最多只会被call 2次 → 一共n2个node,每个node最多被call两次 ⇒ O(2n2) = O(n2)
    // 剪枝极大的降低了时间复杂度，n是层数
    public int minimumTotal3(List<List<Integer>> triangle) {
        // corner case
        if (triangle == null || triangle.size() == 0) return 0;

        int[][] dp = new int[triangle.size()][triangle.size()]; // i, j的取值范围长度是一样的
        return helper(0, 0, dp, triangle);
    }

    private int helper(int i, int j, int[][] dp, List<List<Integer>> triangle) {
        // base case
        if (i == triangle.size()) return 0;
        if (dp[i][j] != 0) return dp[i][j];

        int left = helper(i + 1, j, dp, triangle);
        int right = helper(i + 1, j + 1, dp, triangle);
        dp[i][j] = Math.min(left, right) + triangle.get(i).get(j);
        return dp[i][j];
    }

    // S4: DP  O(n^2)
    // dp[i][j] the minimum path sum from (i,j) to bottom
    public int minimumTotal4(List<List<Integer>> triangle) {
        // corner case
        if (triangle == null || triangle.size() == 0) return 0;

        int n = triangle.size();
        int[][] dp = new int[n][n];

        for (int j = 0; j < n; j++) {
            dp[n - 1][j] = triangle.get(n - 1).get(j);
        }

        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0][0];
    }

    // S5: 仅跟下一行有关系 ⇒ 用一个array迭代赋值的方式来解
    // Space → O(n^2) → O(n) → O(1) vs inplace O(0)
    // 把matrix[i][j] reuse为dp[i][j]，直接inplace操作，这样任何extra space都不会有 ⇒ O(0)
    public int minimumTotal5(List<List<Integer>> triangle) {
        // corner case
        if (triangle == null || triangle.size() == 0) return 0;

        int n = triangle.size();

        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                int temp = Math.min(triangle.get(i + 1).get(j), triangle.get(i + 1).get(j + 1)) +
                        triangle.get(i).get(j);
                triangle.get(i).set(j, temp);
            }
        }
        return triangle.get(0).get(0);
    }
    // 总结：想不到DP的话，一上来先写DFS，然后看有没有冗余计算，有冗余计算的话，把计划存储的东西填进去，然后看空间复杂度能不能优化。
}
