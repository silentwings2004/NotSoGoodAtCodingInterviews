import java.util.Deque;

/*
 * @lc app=leetcode id=1776 lang=java
 *
 * [1776] Car Fleet II
 */

// @lc code=start
class Solution {
    // time = O(n), space = O(n)
    public double[] getCollisionTimes(int[][] cars) {
        int n = cars.length;
        double[] res = new double[n];
        Deque<double[]> deque = new LinkedList<>(); // {t, v}
        deque.offer(new double[]{0, cars[n - 1][1]});
        res[n - 1] = -1;

        for (int i = n - 2; i >= 0; i--) {
            int ds = cars[i + 1][0] - cars[i][0];
            double vi = cars[i][1];
            double total = 0;

            // can't catch the fleet ahead
            if (!deque.isEmpty() && deque.peekLast()[1] >= vi) {
                deque.clear();
                deque.offer(new double[]{0, vi});
                res[i] = -1;
                continue;
            }

            // can catch up the fleet ahead
            double t = deque.peekFirst()[0];
            double v = deque.peekFirst()[1];
            deque.pollFirst(); // poll the head

            while (!deque.isEmpty()) {
                if (total + v * (deque.peekFirst()[0] - t) + ds >= vi * deque.peekFirst()[0]) {
                    total += v * (deque.peekFirst()[0] - t); // update total
                    t = deque.peekFirst()[0];
                    v = deque.peekFirst()[1];
                    deque.pollFirst();
                } else break; // find the range, break
            }

            // find the range when collision happens
            double dt = (ds + total - vi * t) / (vi - v);
            deque.offerFirst(new double[]{t + dt, v});
            deque.offerFirst(new double[]{0, vi}); 
            res[i] = t + dt;
        }
        return res;
    }
}
// @lc code=end

