/*
 * @lc app=leetcode id=2014 lang=java
 *
 * [2014] Longest Subsequence Repeated k Times
 */

// @lc code=start
class Solution {
    // time = O(n), space = O(n)
    String res = "";
    public String longestSubsequenceRepeatedK(String s, int k) {
        int[] count = new int[26];
        for (char c : s.toCharArray()) count[c - 'a']++;
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            if (count[i] >= k) sb.append((char)('a' + i));
        }
        String t = sb.toString();
        dfs(s, k, t, "");
        return res;
    }

    private void dfs(String s, int k, String t, String temp) {
        // base case
        if (!checkOK(temp, s, k)) return; // pruning
        if (temp.length() > res.length() || temp.length() == res.length() && temp.compareTo(res) > 0) res = temp;
        if (temp.length() == 7) return;

        for (char c : t.toCharArray()) {
            temp += c;
            dfs(s, k, t, temp);
            temp = temp.substring(0, temp.length() - 1);
        }
    }

    private boolean checkOK(String temp, String s, int k) {
        if (temp.length() == 0) return true;
        int j = 0, round = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != temp.charAt(j)) continue;
            j++;
            if (j == temp.length()) {
                j = 0;
                round++;
                if (round == k) return true;
            }
        }
        return false;
    }
}
// @lc code=end

