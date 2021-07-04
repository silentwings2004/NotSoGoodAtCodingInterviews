package S11_DP;
import java.util.*;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: PascalTriangleII
 * Creater: Silentwings
 * Date: Apr, 2020
 * Description: 119. Pascal's Triangle II
 */
public class LC119_PascalTriangleII {
    /**
     * Given a non-negative index k where k ≤ 33, return the kth index row of the Pascal's triangle.
     *
     * Note that the row index starts from 0.
     *
     *
     * In Pascal's triangle, each number is the sum of the two numbers directly above it.
     *
     * Example:
     *
     * Input: 3
     * Output: [1,3,3,1]
     * @param rowIndex
     * @return
     */
    // S1: Two Arrays
    // time = O(n^2), space = O(n)
    public List<Integer> getRow(int rowIndex) {
        List<Integer> pre = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        for (int i = 0; i <= rowIndex; i++) {
            cur = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) cur.add(1);
                else cur.add(pre.get(j - 1) + pre.get(j));
            }
            pre = cur;
        }
        return cur;
    }

    // S2: One Array
    // time = O(n^2), space = O(n)
    public List<Integer> getRow2(int rowIndex) {
        List<Integer> cur = new ArrayList<>();
        cur.add(1);

        for (int i = 1; i <= rowIndex; i++) {
            for (int j = i - 1; j > 0; j--) {
                cur.set(j, cur.get(j - 1) + cur.get(j));
            }
            cur.add(1); //补上每层的最后一个 1
        }
        return cur;
    }
}
