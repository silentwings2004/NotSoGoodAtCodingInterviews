package S11_DP;

public class CutStone {
    /**
     * Given a rope of length n meters, cut the rope in different parts of integer lengths in a way that maximizes
     * product of lengths of all parts. You must make at least one cut. Assume that the length of rope is more than
     * 2 meters
     *
     * @param n
     * @return
     */
    // DP --> time = O(n^2)
    public int cutStone(int n) {
        // corner case
        if (n <= 1) return n;

        int[] dp = new int[n + 1];
        dp[1] = 1;

        for (int i = 1; i <= n; i++) {
            int curMax = 0;
            for (int j = 1; j <= i / 2; j++) {
                int curValue = Math.max(dp[j], j) * Math.max(dp[i - j], i - j);
                curMax = Math.max(curMax, curValue);
            }
            dp[i] = curMax;
        }
        return dp[n];
    }
}
