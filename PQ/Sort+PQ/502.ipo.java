/*
 * @lc app=leetcode id=502 lang=java
 *
 * [502] IPO
 */
import java.util.*;
// @lc code=start
class Solution {
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        // int[capital, profits]
        int n = profits.length;
        List<int[]> tasks = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            tasks.add(new int[]{capital[i], profits[i]});
        }

        Collections.sort(tasks, (o1, o2) -> o1[0] - o2[0]);
        
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);

        int count = 0, i = 0;
        while (count < k) {
            while (i < n && tasks.get(i)[0] <= w) {
                pq.offer(tasks.get(i)[1]);
                i++;
            }
            if (!pq.isEmpty()) {
                w +=pq.poll();
                count++;
            } else break;
        }
        return w;
    }
}
// @lc code=end

