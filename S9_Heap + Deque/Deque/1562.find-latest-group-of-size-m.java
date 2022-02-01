import java.util.Deque;

/*
 * @lc app=leetcode id=1562 lang=java
 *
 * [1562] Find Latest Group of Size M
 */

// @lc code=start
class Solution {
    // time = O(n), space = O(n)
    public int findLatestStep(int[] arr, int m) {
        int n = arr.length;
        if (m == n) return n;
        int[] day = new int[n];
        for (int i = 0; i < n; i++) day[arr[i] - 1] = i + 1;

        Deque<Integer> deque = new LinkedList<>();
        int res = -1;

        for (int i = 0; i < n; i++) {
            while (!deque.isEmpty() && day[deque.peekLast()] < day[i]) deque.pollLast();
            while (!deque.isEmpty() && i - deque.peekFirst() >= m) deque.pollFirst();
            deque.offer(i);
            //i - m, [i - m + 1, i], i + 1 -> i - x + 1 = m => x = i - m + 1
            // i - m + 1 >= 0 -> i >= m - 1
            if (i < m - 1) continue;
            int t = day[deque.peekFirst()];
            int left = Integer.MAX_VALUE, right = Integer.MAX_VALUE;
            if (i - m >= 0) left = day[i - m];
            if (i + 1 < n) right = day[i + 1];
            if (left > t && right > t) res = Math.max(res, Math.min(left, right) - 1);
        }
        return res;
    }
}
// @lc code=end

