/*
 * @lc app=leetcode id=906 lang=java
 *
 * [906] Super Palindromes
 */

// @lc code=start
class Solution {
    // time = O(w^(1/4) * logw), space = O(logw)   w: 10^18
    public int superpalindromesInRange(String left, String right) {
        long a = Long.valueOf(left), b = Long.valueOf(right);

        int start = (int)Math.pow(10, left.length() / 4 - 1);
        int end = (int)Math.pow(10, right.length() / 4 + 1);

        int count = 0;
        for (int i = start; i <= end; i++) {
            for (int j = 0; j <= 1; j++) {
                long palin = constructPalin(i, j); // j: type
                long superPalin = palin * palin;
                if (superPalin >= a && superPalin <= b && isPalin(superPalin)) {
                    count++;
                }
            }
        }
        return count;
    }

    private long constructPalin(int i, int j) {
        long y = i;
        // 构造奇数位的回文数
        if (j == 1) i /= 10;
        while (i > 0) {
            y = y * 10 + i % 10;
            i /= 10;
        }
        return y;
    }

    private boolean isPalin(long x) {
        String s = String.valueOf(x);
        int i = 0, j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i++) != s.charAt(j--)) return false;
        }
        return true;
    }
}
// @lc code=end

