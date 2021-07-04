package S8_BFSDFS;
import java.util.*;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: GenerateParentheses
 * Creater: Silentwings
 * Date: Feb, 2020
 * Description: 22. Generate Parentheses
 */
public class LC22_GenerateParentheses {
    /**
     * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
     *
     * For example, given n = 3, a solution set is:
     *
     * [
     *   "((()))",
     *   "(()())",
     *   "(())()",
     *   "()(())",
     *   "()()()"
     * ]
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder("");
        if (n <= 0) return res;
        dfs(n, 0, 0, sb, res);
        return res;

    }
    // S1: call之前决定满足条件再往下call，不满足就直接return
    private void dfs(int n, int l, int r, StringBuilder sb, List<String> res) {
        // base case
        if (l + r == 2 * n) {
            res.add(sb.toString());
            return;
        }

        if (l < n) {
            sb.append('(');
            dfs(n, l + 1, r, sb, res);
            sb.deleteCharAt(sb.length() - 1);
        }
        if (r < l) {
            sb.append(')');
            dfs(n, l, r + 1, sb, res);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    // S2: 无脑call下去，在base case位置一上来不满足条件就return
    private void dfs2(int n, int l, int r, StringBuilder sb, List<String> res) {
        // failure --> 从语义上来说，failure应该在最上面直接check
        // 就看出幺蛾子的这一瞬间，这答案要不要加到结果里 ==> 多数情况下调换没关系，极少数情况下注意少了最后一个位置的答案
        if (r > l || l > n) return;
        // success base case
        if (l == n && r == n) {
            res.add(sb.toString());
            return;
        }


        int len = sb.length();
        sb.append('(');
        dfs(n, l + 1, r, sb, res);
        sb.setLength(len);
        sb.append(')');
        dfs(n, l, r + 1, sb, res);
        sb.setLength(len);
    }
}
