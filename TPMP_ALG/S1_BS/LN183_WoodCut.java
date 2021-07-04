package S1_BS;
import java.util.*;
/**
 * Project Name: Lintcode
 * Package Name: lintcode
 * File Name: WoodCut
 * Creater: Silentwings
 * Date: Aug, 2020
 * Description: 183. Wood Cut
 */
public class LN183_WoodCut {
    /**
     * Given n pieces of wood with length L[i] (integer array). Cut them into small pieces to guarantee you could have
     * equal or more than k pieces with the same length. What is the longest length you can get from the n pieces of
     * wood? Given L & k, return the maximum length of the small pieces.
     *
     * Example
     * Example 1
     *
     * Input:
     * L = [232, 124, 456]
     * k = 7
     * Output: 114
     * Explanation: We can cut it into 7 pieces if any piece is 114cm long, however we can't cut it into 7 pieces if
     * any piece is 115cm long.
     *
     * Example 2
     *
     * Input:
     * L = [1, 2, 3]
     * k = 7
     * Output: 0
     * Explanation: It is obvious we can't make it.
     * Challenge
     * O(n log Len), where Len is the longest length of the wood.
     *
     * Notice
     * You couldn't cut wood into float length.
     *
     * If you couldn't get >= k pieces, return 0.
     * @param L
     * @param k
     * @return
     */
    // time = O(nlogl), space = O(1)
    public int woodCut(int[] L, int k) {
        // corner case
        if (L == null || L.length == 0 || k <= 0) return 0;

        int maxLen = 0;
        long sum = 0;
        for (int l : L) {
            maxLen = Math.max(maxLen, l);
            sum += l;
        }

        int avgLen = (int)(sum / k);
        int left = 1, right = Math.min(avgLen, maxLen);
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (canCut(L, mid, k)) left = mid + 1;
            else right = mid - 1;
        }
        return right;
    }

    private boolean canCut(int[] L, int mid, int k) {
        int cut = 0;
        for (int l : L) {
            cut += l / mid;
        }
        return cut >= k;
    }

    // S2:
    // time = O(nlogk), space = O(1)  k: longest wood in the L
    public int woodCut2(int[] L, int k) {
        // corner case
        if (L == null || L.length == 0 || k <= 0) return 0;

        int longest = L[0];
        for (int l : L) {  // O(n)
            longest = Math.max(longest, l); // 上界right = longest， not the shortest，因为不是每根木头都需要被用到!!!
        }

        int left = 1, right = longest;
        while (left < right) {  // O(logk)
            int mid = left + (right - left) / 2 + 1;
            int count = helper(L, mid); // O(n)
            if (count >= k) left = mid;
            else right = mid - 1;
        }
        return helper(L, left) >= k ? left : 0;
    }

    private int helper(int[] L, int len) {
        int count = 0;
        for (int l : L) {
            count += l / len;
        }
        return count;
    }
}
/**
 * 上界right = longest， not the shortest，因为不是每根木头都需要被用到!!!
 */
