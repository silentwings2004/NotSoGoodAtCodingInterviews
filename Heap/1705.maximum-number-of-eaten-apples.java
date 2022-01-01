import java.util.PriorityQueue;

/*
 * @lc app=leetcode id=1705 lang=java
 *
 * [1705] Maximum Number of Eaten Apples
 */

// @lc code=start
class Solution {
    // time = O(nlogn), space = O(n)
    public int eatenApples(int[] apples, int[] days) {
        int n = apples.length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
        int i = 0, count = 0;
        while (!pq.isEmpty() || i < n) {
            while (!pq.isEmpty() && pq.peek()[0] <= i) pq.poll();
            if (i < n && apples[i] > 0) pq.offer(new int[]{i + days[i], apples[i]});
            if (!pq.isEmpty()) {
                count++;
                pq.peek()[1]--;
                if (pq.peek()[1] == 0) pq.poll();
            }
            i++;
        }
        return count;
    }
}
// @lc code=end

