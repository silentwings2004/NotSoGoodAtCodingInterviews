/*
 * @lc app=leetcode id=301 lang=java
 *
 * [301] Remove Invalid Parentheses
 */

// @lc code=start
class Solution {
    // time = O(2^n), space = O(n)
    public List<String> removeInvalidParentheses(String s) {
        List<String> res = new ArrayList<>();
        int count = 0, remove = 0;
        for (char ch : s.toCharArray()) {
            if (ch == '(') count++;
            else if (ch == ')') {
                count--;
                if (count < 0) {
                    remove++;
                    count = 0;
                }
            }
        }
        if (count > 0) remove += count;
        int maxLen = s.length() - remove;
        dfs(s, 0, 0, new StringBuilder(), maxLen, res);
        return res;
    }

    private void dfs(String s, int idx, int count, StringBuilder path, int maxLen, List<String> res) {
        // base case - sucess
        if (idx == s.length()) {
            if (count == 0 && path.length() == maxLen) res.add(path.toString());
            return;
        }

        // base case - fail
        if (count < 0) return;
        if (path.length() > maxLen) return;

        // case 1: other chars
        if (s.charAt(idx) != '(' && s.charAt(idx) != ')') {
            path.append(s.charAt(idx));
            dfs(s, idx + 1, count, path, maxLen, res);
            path.setLength(path.length() - 1);
        } else {
            // case 2; choose
            path.append(s.charAt(idx));
            dfs(s, idx + 1, count + (s.charAt(idx) == '(' ? 1 : -1), path, maxLen, res);
            path.setLength(path.length() - 1);

            // case 3: not choose
            if (path.length() == 0 || s.charAt(idx) != path.charAt(path.length() - 1)) {
                dfs(s, idx + 1, count, path, maxLen, res);
            }
        }
    }
}
// @lc code=end

