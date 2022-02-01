/*
 * @lc app=leetcode id=1915 lang=java
 *
 * [1915] Number of Wonderful Substrings
 */

// @lc code=start
class Solution {
    // time = O(n), space = O(1)
    public long wonderfulSubstrings(String word) {
        int n = word.length(), state = 0;
        int[] count = new int[1 << 10];
        count[0] += 1; // 千万别忘了初始化count[0] += 1

        long res = 0;
        for (int i = 0; i < n; i++) {
            int j = word.charAt(i) - 'a';
            state = state ^ (1 << j);
            res += count[state];

            for (int k = 0; k < 10; k++) {
                int newState = state ^ (1 << k);
                res += count[newState];
            }
            count[state]++;
        }
        return res;
    }
}
// @lc code=end

