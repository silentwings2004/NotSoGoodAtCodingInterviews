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

    // S2: sweep line
    // time = O((m + n) * log(m + n)), space = O(m + n)
    public int[][] intervalIntersection2(int[][] firstList, int[][] secondList) {
        List<int[]> diff = new ArrayList<>();

        for (int[] x : firstList) {
            diff.add(new int[]{x[0], 1});
            diff.add(new int[]{x[1], -1});
        }

        for (int[] x : secondList) {
            diff.add(new int[]{x[0], 1});
            diff.add(new int[]{x[1], -1});
        }

        Collections.sort(diff, (o1, o2) -> o1[0] != o2[0] ? o1[0] - o2[0] : o2[1] - o1[1]);

        int count = 0, start = -1, end = -1;
        List<int[]> res = new ArrayList<>();

        for (int[] x : diff) {
            count += x[1];
            if (x[1] == 1 && count == 2) start = x[0];
            else if (x[1] == -1 && count == 1) {
                end = x[0];
                res.add(new int[]{start, end});
            }
        }
        int[][] ans = new int[res.size()][2];
        for (int i = 0; i < res.size(); i++) ans[i] = res.get(i);
        return ans;
    }
}
// @lc code=end

