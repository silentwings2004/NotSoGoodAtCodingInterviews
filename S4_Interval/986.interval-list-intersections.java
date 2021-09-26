import java.util.List;

/*
 * @lc app=leetcode id=986 lang=java
 *
 * [986] Interval List Intersections
 */

// @lc code=start
class Solution {
    // S1: Two Pointers
    // time = O(m + n), space = O(m + n)
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        int m = firstList.length, n = secondList.length;
        int i = 0, j = 0;
        List<int[]> res = new ArrayList<>();

        while (i < m && j < n) {
            if (firstList[i][1] < secondList[j][0]) i++;
            else if (firstList[i][0] > secondList[j][1]) j++;
            else {
                int start = Math.max(firstList[i][0], secondList[j][0]);
                int end = Math.min(firstList[i][1], secondList[j][1]);
                res.add(new int[]{start, end});
                if (firstList[i][1] < secondList[j][1]) i++;
                else j++;
            }
        }
        return res.toArray(new int[res.size()][]);
    }
}
// @lc code=end

