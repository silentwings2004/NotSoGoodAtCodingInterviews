package S11_DP;
import java.util.*;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: WordBreakII
 * Creater: Silentwings
 * Date: Mar, 2020
 * Description: 140. Word Break II
 */
public class LC150_WordBreakII {
    /**
     * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s to
     * construct a sentence where each word is a valid dictionary word. Return all such possible sentences.
     *
     * Note:
     *
     * The same word in the dictionary may be reused multiple times in the segmentation.
     * You may assume the dictionary does not contain duplicate words.
     * Example 1:
     *
     * Input:
     * s = "catsanddog"
     * wordDict = ["cat", "cats", "and", "sand", "dog"]
     * Output:
     * [
     *   "cats and dog",
     *   "cat sand dog"
     * ]
     * Example 2:
     *
     * Input:
     * s = "pineapplepenapple"
     * wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
     * Output:
     * [
     *   "pine apple pen apple",
     *   "pineapple pen apple",
     *   "pine applepen apple"
     * ]
     * Explanation: Note that you are allowed to reuse a dictionary word.
     * Example 3:
     *
     * Input:
     * s = "catsandog"
     * wordDict = ["cats", "dog", "sand", "and", "cat"]
     * Output:
     * []
     *
     * @param s
     * @param wordDict
     * @return
     */

    // S1
    public List<String> wordBreak(String s, List<String> wordDict) {
        // corner case
        if (s == null || s.length() == 0) return null;
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && isContains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }
}
