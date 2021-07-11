/*
 * @lc app=leetcode id=792 lang=java
 *
 * [792] Number of Matching Subsequences
 */

// @lc code=start
class Solution {
    public int numMatchingSubseq(String s, String[] words) {
        int m = s.length();
        s = "#" + s;
        int[][] next = new int[m + 1][26];
        // init
        for (int i = 0; i < 26; i++) next[m][i] = -1;
        for (int i = m - 1; i >= 0; i--) {
            for (int j = 0; j < 26; j++) {
                next[i][j] = next[i + 1][j];
            }
            next[i][s.charAt(i + 1) - 'a'] = i + 1;
        }

        int res = 0;
        for (String word : words) {
            int i = 0;
            boolean flag = true;
            for (char ch : word.toCharArray()) {
                i = next[i][ch - 'a'];
                if (i == -1) {
                    flag = false;
                    break;
                }
            }
            if (flag) res++;
        }
        return res;
    }
}
// @lc code=end

