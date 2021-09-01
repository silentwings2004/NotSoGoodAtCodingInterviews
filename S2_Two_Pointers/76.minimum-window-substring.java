/*
 * @lc app=leetcode id=76 lang=java
 *
 * [76] Minimum Window Substring
 */

// @lc code=start
class Solution {
    // time = O(n), space = O(1)
    public String minWindow(String s, String t) {
        int[] dict = new int[128];
        for (char c : t.toCharArray()) dict[c]++;

        int start = 0, min = Integer.MAX_VALUE;
        int i = 0, total = t.length();

        for (int j = 0; j < s.length(); j++) {
            char c = s.charAt(j);
            if (dict[c]-- > 0) total--;
            while (total == 0) {
                int len = j - i + 1;
                if (len < min) {
                    min = j - i + 1;
                    start = i;
                }
                if (++dict[s.charAt(i++)] > 0) total++;
            }
        }
        return min == Integer.MAX_VALUE ? "" : s.substring(start, start + min);
    }
}
// @lc code=end

