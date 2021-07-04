package S1_BS;
import java.util.*;
/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: SuperEggDrop
 * Creater: Silentwings
 * Date: Feb, 2020
 * Description: 887. Super Egg Drop
 */
public class LC887_SuperEggDrop {
    /**
     * You are given K eggs, and you have access to a building with N floors from 1 to N.
     *
     * Each egg is identical in function, and if an egg breaks, you cannot drop it again.
     *
     * You know that there exists a floor F with 0 <= F <= N such that any egg dropped at a floor higher than F
     * will break, and any egg dropped at or below floor F will not break.
     *
     * Each move, you may take an egg (if you have an unbroken one) and drop it from any floor X (with 1 <= X <= N).
     *
     * Your goal is to know with certainty what the value of F is.
     *
     * What is the minimum number of moves that you need to know with certainty what F is, regardless of the initial
     * value of F?
     *
     *
     *
     * Example 1:
     *
     * Input: K = 1, N = 2
     * Output: 2
     * Explanation:
     * Drop the egg from floor 1.  If it breaks, we know with certainty that F = 0.
     * Otherwise, drop the egg from floor 2.  If it breaks, we know with certainty that F = 1.
     * If it didn't break, then we know with certainty F = 2.
     * Hence, we needed 2 moves in the worst case to know what F is with certainty.
     *
     * Example 2:
     *
     * Input: K = 2, N = 6
     * Output: 3
     *
     * Example 3:
     *
     * Input: K = 3, N = 14
     * Output: 4
     *
     *
     * Note:
     *
     * 1 <= K <= 100
     * 1 <= N <= 10000
     * @param K
     * @param N
     * @return
     */
    // time = O(K * N), space = O(K * N)
    public int superEggDrop(int K, int N) {
        // 定义长度为 K 的数组代表可用的 K 个蛋对应尝试次数后找到的最高楼层。
        int[] dp = new int[K];
        // 将数组每个位置用 1 填充，表示 1 层楼只需要尝试一次。
        Arrays.fill(dp, 1);
        while (dp[K - 1] < N){
            // 从可用 K 个蛋开始遍历。
            for (int i = K - 1; i >= 1; i--){
                // 每次尝试后的最高楼层为当前蛋碎和没碎找到的总楼层和 +1 。
                dp[i] = dp[i] + dp[i - 1] + 1;
            }
            // 每尝试一次，次数 +1 。
            dp[0]++;
        }
        return dp[0];
    }

    // S2
    // time = O(n * k), space = O(n * k)
    public int superEggDrop2(int k, int n) {
        int[][] dp = new int[k + 1][n + 1];

        int m = 0;
        while (dp[k][m] < n) {
            m++;
            for (int i = 1; i <= k; i++) {
                dp[i][m] = dp[i - 1][m - 1] + dp[i][m - 1] + 1;
            }
        }
        return m;
    }
}
/**
 * 令dp[k][m]表示用k个鸡蛋和m次尝试,最多可以测试的楼层的高度.那么转移方程是:
 * dp[k][m] = dp[k-1][m-1]+dp[k][m-1]+1
 * 这个思想是,我们设x=dp[k-1][m-1],那么我们在第x+1层扔一个鸡蛋:
 * 如果碎了,我们就用(k-1,m-1)的策略,能测量的楼层仍然是x.
 * 如果没碎,我们就能在x+1层之上,用(k,m-1)的策略,再检测x2 = dp[k][m-1]层楼.
 * 所以总的来说,高度在x1+1+x2之内的楼层,我们必然都可以通过(k,m)来实现检测.
 * +1是因为0 index
 */
