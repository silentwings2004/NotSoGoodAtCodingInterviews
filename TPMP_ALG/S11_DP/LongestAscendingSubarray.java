package S11_DP;
import java.util.*;

public class LongestAscendingSubarray {
    /**
     * Unsorted Array, the length of longest ascending subarray/substring / consecutive 1
     * @param array
     * @return
     */
    // S1
    public int longest(int[] array) {
        if (array == null || array.length == 0) return 0;
        int[] dp = new int[array.length];

        dp[0] = 1;
        int max = dp[0];

        for (int i = 1; i < array.length; i++) {
            if (array[i] > array[i - 1]) dp[i] = dp[i -1] + 1;
            else dp[i] = 1;
            max = Math.max(dp[i], max);
        }
        return max;
    }

    // S2
    public int longest2(int[] array) {
        if (array == null || array.length == 0) return 0;

        int cur = 1;
        int max = cur;

        for (int i = 1; i < array.length; i++) {
            if (array[i] > array[i - 1]) cur = cur + 1;
            else cur = 1;
            max = Math.max(cur, max);
        }
        return max;
    }
}
