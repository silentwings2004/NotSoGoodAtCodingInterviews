/*
 * @lc app=leetcode id=630 lang=java
 *
 * [630] Course Schedule III
 */
import java.util.*;
// @lc code=start
class Solution {
    public int scheduleCourse(int[][] courses) {
        Arrays.sort(courses, (o1, o2) -> o1[1] - o2[1]);

        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);

        int days = 0;
        for (int i = 0; i < courses.length; i++) {
            pq.offer(courses[i][0]);
            days += courses[i][0];

            if (days > courses[i][1]) {
                days -= pq.poll();
            }
        }
        return pq.size();
    }
}
// @lc code=end

