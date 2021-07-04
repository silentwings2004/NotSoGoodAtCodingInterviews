package S8_BFSDFS;
import java.util.*;

public class CoinSumUpToTarget {
    /**
     * coins[]: 25 10 5 1
     * target: 100
     * return sol[4] = {num of 25, num of 10, num of 5, num of 1}
     * sol[4] → List<Integer> sol; → 做成定长4size
     *
     * stack		heap				res @21875 @21895 @21375
     * sol		0 0 0 100
     * sol'		0 0 1 95
     * sol''	0 0 1 95
     * sol'''
     *
     * sol		1 4 8 10
     * 100
     * level0 num of 25	0 * 25	1*25	2*25 … left_balance / 25   分5个岔，注意一个不取的情况
     * level1 num of 10      0	left_balance / 10 + 1  动态分叉，只能使用for loop
     * level2 num of 5       0 1 2
     * level3 num of 1      0 1 2 3 4 5 … 100  ← 答案出在leaf node上，提前一层出答案，无需走到null
     * null                        |   |  |
     * 余下的left_balance是几，最后一层就取几，根本不用分叉，所以可以直接出答案
     * @param coins
     */
    public void dfs(int[] coins, int level, int left_balance, int[] sol, List<int[]> res) {
        // base case
        if (level == coins.length - 1) {
            sol[level] = left_balance;
            res.add(sol.clone()); // --> 一定要deep copy
//            res.add(Arrays.copyOf(sol, sol.length));  // copyOf可以拿来做expand
            return;
        }

        int num = left_balance / coins[level] + 1;
        for (int i = 0; i < num; i++) {
            sol[level] = i;
            dfs(coins, level + 1, left_balance - i * coins[level], sol, res);
//            sol[level] = 0;
        }
    }
}
