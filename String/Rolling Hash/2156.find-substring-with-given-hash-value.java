/*
 * @lc app=leetcode id=2156 lang=java
 *
 * [2156] Find Substring With Given Hash Value
 */

// @lc code=start
class Solution {
    // time = O(n), space = O(1)
    public String subStrHash(String s, int power, int modulo, int k, int hashValue) {
        int n = s.length();
        long hash = 0, M = modulo;
        long pk = 1;
        for (int i = 0; i < k - 1; i++) pk = pk * power % M;

        int pos = -1;
        for (int i = n - 1; i >= 0; i--) {
            if (i + k < n) {
                hash = hash - (s.charAt(i + k) - 'a' + 1) * pk % M;
                hash = (hash + M) % M;
            }
            hash = (hash * power + s.charAt(i) - 'a' + 1) % M;
            if (i + k <= n && hash == hashValue) pos = i;
        }
        return s.substring(pos, pos + k);
    }
}
// @lc code=end

