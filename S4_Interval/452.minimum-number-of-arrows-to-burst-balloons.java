/*
 * @lc app=leetcode id=452 lang=java
 *
 * [452] Minimum Number of Arrows to Burst Balloons
 */

// @lc code=start
class Solution {
    // time = O(nlogn), space = O(1)
    public int findMinArrowShots(int[][] points) {
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] == o2[1]) return 0;
                if (o1[1] < o2[1]) return -1;
                else return 1;
            }
        });

        int n = points.length;
        int i = 0, count = 0;
        while (i < n) {
            count++;
            int j = i + 1;
            while (j < n && points[j][0] <= points[i][1]) j++;
            i = j;
        }
        return count;
    }
}
/**
 * We can't simply use the o1[1] - o2[1] trick, as this will cause an integer overflow for very large or small values.
 */
// @lc code=en

