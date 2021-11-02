/*
 * @lc app=leetcode id=282 lang=java
 *
 * [282] Expression Add Operators
 */

// @lc code=start
class Solution {
    // time = O(4^n), space = O(n)
    public List<String> addOperators(String num, int target) {
        List<String> res = new ArrayList<>();
        dfs(num, target, 0, "", 0, 0, res);
        return res;
    }

    private void dfs(String num, int target, int curPos, String preStr, long preVal, long lastVal, List<String> res) {
        // base case
        if (curPos == num.length()) {
            if (preVal == target) {
                res.add(preStr);
                return;
            }
        }

        for (int i = curPos; i < num.length(); i++) {
            String curStr = num.substring(curPos, i + 1);
            if (curStr.length() > 1 && curStr.charAt(0) == '0') continue;
            long curVal = Long.parseLong(curStr);

            if (curPos == 0) {
                dfs(num, target, i + 1, curStr, curVal, curVal, res);
            } else {
                // '+'
                dfs(num, target, i + 1, preStr + "+" + curStr, preVal + curVal, curVal, res);

                // '-'
                dfs(num, target, i + 1, preStr + '-' + curStr, preVal - curVal, -curVal, res);

                // '*'
                dfs(num, target, i + 1, preStr + '*' + curStr, preVal - lastVal + lastVal * curVal, lastVal * curVal, res);
            }
        }
    }
}
// @lc code=end

