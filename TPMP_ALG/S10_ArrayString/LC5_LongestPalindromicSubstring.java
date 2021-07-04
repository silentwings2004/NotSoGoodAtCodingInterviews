package S10_ArrayString;
import java.util.*;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: LongestPalindromicSubstring
 * Creater: Silentwings
 * Date: Mar, 2020
 * Description: 5. Longest Palindromic Substring
 */
public class LC5_LongestPalindromicSubstring {
    /**
     * Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s
     * is 1000.
     *
     * Example 1:
     *
     * Input: "babad"
     * Output: "bab"
     * Note: "aba" is also a valid answer.
     * Example 2:
     *
     * Input: "cbbd"
     * Output: "bb"
     * @param s
     * @return
     */
    // S1: DP --> time = O(n^2), space = O(n^2)
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) return s;
        String res = "";
        boolean[][] dp = new boolean[s.length()][s.length()];
        int max = 0;
        for (int j = 0; j < s.length(); j++) {
            for (int i = 0; i <= j; i++) {
                dp[i][j] = s.charAt(i) == s.charAt(j) && ((j - i <= 2) || dp[i + 1][j - 1]);
                if (dp[i][j]) {
                    if (j - i + 1 > max) {
                        max = j - i + 1;
                        res = s.substring(i, j + 1);
                    }
                }
            }
        }
        return res;
    }

    // S2: 中心扩散法：从中间向两边
    // time = O(n^2), space = o(1）
    public String longestPalindrome2(String s) {
        if (s == null || s.length() == 0) return s;

        String res = "";
        for (int i = 0; i < s.length(); i++) {
            res = helper(s, res, i, i); // odd
            res = helper(s, res, i, i + 1); // even
        }
        return res;
    }

    private String helper(String s, String res, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        String cur = s.substring(left + 1, right);
        if (cur.length() > res.length()) {
            res = cur;
        }
        return res;
    }
}
