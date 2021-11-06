/*
 * @lc app=leetcode id=524 lang=java
 *
 * [524] Longest Word in Dictionary through Deleting
 */

// @lc code=start
class Solution {
    // time = O(26m + n * k), space = O(26m)
    public String findLongestWord(String s, List<String> dictionary) {
        int m = s.length();
        s = "#" + s;
        int[][] next = new int[m + 1][26];
        Arrays.fill(next[m], -1);

        for (int i = m; i >= 1; i--) {
            for (int j = 0; j < 26; j++) {
                next[i - 1][j] = next[i][j];
            }
            next[i - 1][s.charAt(i) - 'a'] = i;
        }

        String res = "";
        for (String word : dictionary) {
            int i = 0;
            boolean flag = true;
            for (char ch : word.toCharArray()) {
                i = next[i][ch - 'a'];
                if (i == -1) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                if (word.length() > res.length() || word.length() == res.length() && word.compareTo(res) < 0) {
                    res = word;
                }
            }
        }
        return res;
    }
}
// @lc code=end

