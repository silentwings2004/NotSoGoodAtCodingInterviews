package S11_DP;
import java.util.*;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: PascalTriangle
 * Creater: Silentwings
 * Date: Apr, 2020
 * Description: 118. Pascal's Triangle
 */
public class LC118_PascalTriangle {
    /**
     * Given a non-negative integer numRows, generate the first numRows of Pascal's triangle.
     *
     *
     * In Pascal's triangle, each number is the sum of the two numbers directly above it.
     *
     * Example:
     *
     * Input: 5
     * Output:
     * [
     *      [1],
     *     [1,1],
     *    [1,2,1],
     *   [1,3,3,1],
     *  [1,4,6,4,1]
     * ]
     * @param numRows
     * @return
     */
    // time = O(n^2), space = O(n)
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        // corner case
        if (numRows == 0) return res;

        //先添加第一行
        List<Integer> firstRow = new ArrayList<>();
        firstRow.add(1);
        res.add(firstRow);

        for (int i = 1; i < numRows; i++) { // --> level
            List<Integer> list = new ArrayList<>();
            //添加本层第一个1
            list.add(1);
            for (int j = 1; j < i; j++) {
                list.add(res.get(i - 1).get(j - 1) + res.get(i - 1).get(j));
            }
            //添加最后一个1
            list.add(1);
            res.add(list);
        }
        return res;
    }
}
