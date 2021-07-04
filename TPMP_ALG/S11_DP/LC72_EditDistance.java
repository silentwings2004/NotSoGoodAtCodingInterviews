package S11_DP;
import java.util.*;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: EditDistance
 * Creater: Silentwings
 * Date: Mar, 2020
 * Description: 72. Edit Distance
 */
public class LC72_EditDistance {
    /**
     * Given two words word1 and word2, find the minimum number of operations required to convert word1 to word2.
     *
     * You have the following 3 operations permitted on a word:
     *
     * Insert a character
     * Delete a character
     * Replace a character
     * Example 1:
     *
     * Input: word1 = "horse", word2 = "ros"
     * Output: 3
     * Explanation:
     * horse -> rorse (replace 'h' with 'r')
     * rorse -> rose (remove 'r')
     * rose -> ros (remove 'e')
     * Example 2:
     *
     * Input: word1 = "intention", word2 = "execution"
     * Output: 5
     * Explanation:
     * intention -> inention (remove 't')
     * inention -> enention (replace 'i' with 'e')
     * enention -> exention (replace 'n' with 'x')
     * exention -> exection (replace 'n' with 'c')
     * exection -> execution (insert 'u')
     *
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {
        // corner case： word1 or word2 is null or empty
        if (word1 == null || word1.length() == 0) {
            if (word2 == null) return 0;
            return word2.length();
        }
        if (word2 == null || word2.length() == 0) {
            if (word1 == null) return 0;
            return word1.length();
        }
        // new 2D dp[][]
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        dp[0][0] = 0;

        // Initialize 2D dp array
        for (int i = 1; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int j = 1; j <= n; j++) {
            dp[0][j] = j;
        }

        // Fill in the 2D dp table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) dp[i][j] = dp[i - 1][j - 1];
                else dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i - 1][j - 1]), dp[i][j - 1]) + 1;
            }
        }
        return dp[m][n];
    }
}
