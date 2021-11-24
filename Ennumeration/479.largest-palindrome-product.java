/*
 * @lc app=leetcode id=479 lang=java
 *
 * [479] Largest Palindrome Product
 */

// @lc code=start
class Solution {
    // time = O(1), space = O(1)
    public int largestPalindrome(int n) {
        if (n == 1) return 9;

        long low = (long) Math.pow(10, n - 1);
        long high = (long) Math.pow(10, n) - 1;

        for (long i = high; i > low; i--) {
            long p = createPalin(i);
            for (long d = high; d * d >= p; d--) {
                if (p % d == 0 && p / d >= low) {
                    return (int)(p % 1337);
                }
            }
        }
        return -1;
    }

    private long createPalin(long i) {
        String s = String.valueOf(i);
        StringBuilder sb = new StringBuilder(s);
        return Long.valueOf(s + sb.reverse().toString());
    }
}
// @lc code=end

