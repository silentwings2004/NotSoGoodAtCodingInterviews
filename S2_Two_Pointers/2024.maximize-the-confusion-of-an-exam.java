/*
 * @lc app=leetcode id=2024 lang=java
 *
 * [2024] Maximize the Confusion of an Exam
 */

// @lc code=start
class Solution {
    // time = O(n), space = O(1)
    public int maxConsecutiveAnswers(String answerKey, int k) {
        // corner case
        if (answerKey == null || answerKey.length() == 0 || k < 0) return 0;
        
        // F -> T
        int n = answerKey.length(), j = 0, count = 0, res = 0;
        for (int i = 0; i < n; i++) {
            while (j < n && (answerKey.charAt(j) == 'T' || count < k)) {
                if (answerKey.charAt(j) == 'F') count++;
                j++;
            }
            res = Math.max(res, j - i);
            while (count == k) {
                if (answerKey.charAt(i) == 'F') count--;
                else i++;
            }
        }

        // T -> F
        j = 0;
        count = 0;
        for (int i = 0; i < n; i++) {
            while (j < n && (answerKey.charAt(j) == 'F' || count < k)) {
                if (answerKey.charAt(j) == 'T') count++;
                j++;
            }
            res = Math.max(res, j - i);
            while (count == k) {
                if (answerKey.charAt(i) == 'T') count--;
                else i++;
            }
        }
        return res;
    }
}
// @lc code=end

