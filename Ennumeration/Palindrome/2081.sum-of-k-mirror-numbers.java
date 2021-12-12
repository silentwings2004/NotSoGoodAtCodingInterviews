/*
 * @lc app=leetcode id=2081 lang=java
 *
 * [2081] Sum of k-Mirror Numbers
 */

// @lc code=start
class Solution {
    // time = O(10^5), space = O(1)
    public long kMirror(int k, int n) {
        int len = 1;
        List<Long> res = new ArrayList<>();
        while (true) {
            for (long i = (long)Math.pow(10, len - 1); i < (long)Math.pow(10, len); i++) {
                long a = createPalindrome(i, 0); // type = 0 -> odd; type = 1 -> even
                if (checkOK(a, k)) res.add(a);
                if (res.size() == n) return getSum(res);
            } 
            for (long i = (long)Math.pow(10, len - 1); i < (long)Math.pow(10, len); i++) {
                long a = createPalindrome(i, 1);
                if (checkOK(a, k)) res.add(a);
                if (res.size() == n) return getSum(res);
            } 
            len++;
        }
    }

    private long createPalindrome(long x, int type) {
        long y = x;
        if (type == 0) x /= 10;
        while (x > 0) {
            y = y * 10 + x % 10;
            x /= 10;
        }
        return y;
    }
    private boolean checkOK(long a, int k) {
        StringBuilder sb = new StringBuilder();
        while (a > 0) {
            sb.append(a % k);
            a /= k;
        }

        int i = 0, j = sb.length() - 1;
        while (i < j) {
            if (sb.charAt(i++) != sb.charAt(j--)) return false;
        }
        return true;
    }

    private long getSum(List<Long> res) {
        long sum = 0;
        for (long x : res) sum += x;
        return sum;
    }
}
// @lc code=end

