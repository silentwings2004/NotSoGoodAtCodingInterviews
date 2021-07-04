package S12_Recursion;
import java.util.*;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: NQueens
 * Creater: Silentwings
 * Date: Mar, 2020
 * Description: 51. N-Queens
 */
public class LC51_NQueens {
    /**
     * The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each
     * other.
     *
     *
     * Given an integer n, return all distinct solutions to the n-queens puzzle.
     *
     * Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.'
     * both indicate a queen and an empty space respectively.
     *
     * Example:
     *
     * Input: 4
     * Output: [
     *  [".Q..",  // Solution 1
     *   "...Q",
     *   "Q...",
     *   "..Q."],
     *
     *  ["..Q.",  // Solution 2
     *   "Q...",
     *   "...Q",
     *   ".Q.."]
     * ]
     * Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above.
     *
     * queens[] : [0, 0, 0, 0] --> [1, 3, 0, 2] 当前的queen放在哪一列上
     *
     * @param n
     * @return
     */
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        if (n <= 0) return res;
        helper(res, new int[n], 0);
        return res;
    }

    private void helper(List<List<String>> res, int[] queens, int pos) { // pos --> level 层数
        if (pos == queens.length) {   // 都遍历完了，开始加答案
            addSoltuion(res, queens);
            return;
        }
        for (int i = 0; i < queens.length; i++) {
            queens[pos] = i; // 遍历该层各个位置
            if (isValid(queens, pos)) {
                helper(res, queens, pos + 1); // 满足条件就继续往下一层call，否则就没必要继续dfs向下，不会成为最终答案
            }
        }
    }

    private boolean isValid(int[] queens, int pos) {
        for (int i = 0; i < pos; i++) {
            if (queens[i] == queens[pos]) { // 是否在同一列
                return false;
            } else if (Math.abs(queens[pos] - queens[i]) == Math.abs(pos - i)) { //是否在对角线上
                return false;
            }
        }
        return true;
    }

    private void addSoltuion(List<List<String>> res, int[] queens) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < queens.length; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < queens.length; j++) {
                if (queens[i] == j) sb.append('Q');
                else sb.append('.');
            }
            list.add(sb.toString());
        }
        res.add(list);
    }
}