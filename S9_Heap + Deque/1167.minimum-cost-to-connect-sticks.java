import java.util.PriorityQueue;

/*
 * @lc app=leetcode id=1167 lang=java
 *
 * [1167] Minimum Cost to Connect Sticks
 */

// @lc code=start
class Solution {
    // time = O(nlogn), space = O(n)
    public int connectSticks(int[] sticks) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int x : sticks) pq.offer(x);

        int res = 0;
        while (pq.size() >= 2) {
            int a = pq.poll(), b = pq.poll();
            res += a + b;
            pq.offer(a + b);
        }
        return res;
    }
}
// @lc code=end

