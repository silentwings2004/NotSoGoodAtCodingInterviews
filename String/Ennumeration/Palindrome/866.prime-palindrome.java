/*
 * @lc app=leetcode id=866 lang=java
 *
 * [866] Prime Palindrome
 */

// @lc code=start
class Solution {
    // time = O(n), space = O(1)
    public int primePalindrome(int n) {
        if (n > 7 && n <= 11) return 11;
        String num = String.valueOf(n);
        int len = num.length() / 2;
        int a = (int)Math.pow(10, len);

        for (int i = a; i <= 20000; i++) {
            String s = String.valueOf(i);
            String s1 = s.substring(0, s.length() - 1);
            StringBuilder sb = new StringBuilder(s1);
            s1 = sb.reverse().toString();
            s += s1;

            int k = Integer.parseInt(s);
            if (k >= n && isPrime(k)) return k;
        }
        return -1;
    }

    private boolean isPrime(int k) {
        if (k < 2) return false;
        if (k % 2 == 0) return k == 2;
        for (int i = 3; i * i <= k; i++) {
            if (k % i == 0) return false;
        }
        return true;
    }
}
// @lc code=end

