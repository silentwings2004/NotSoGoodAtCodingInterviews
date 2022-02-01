import java.util.PriorityQueue;

/*
 * @lc app=leetcode id=1942 lang=java
 *
 * [1942] The Number of the Smallest Unoccupied Chair
 */

// @lc code=start
class Solution {
    // time = O(nlogn), space = O(n)
    public int smallestChair(int[][] times, int targetFriend) {
        PriorityQueue<Integer> free = new PriorityQueue<>();
        PriorityQueue<int[]> used = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);

        for (int i = 0; i < 10000; i++) free.offer(i);
        
        int n = times.length;
        int[][] time = new int[n][3];
        for (int i = 0; i < n; i++) {
            time[i][0] = times[i][0];
            time[i][1] = times[i][1];
            time[i][2] = i;
        }

        Arrays.sort(time, (o1, o2) -> o1[0] - o2[0]);

        for (int[] t : time) {
            int start = t[0], end = t[1], person = t[2];
            // check used chairs -> free
            while (!used.isEmpty() && used.peek()[0] <= start) {
                free.offer(used.poll()[1]);
            }

            int chair = free.poll();
            if (person == targetFriend) return chair;
            used.offer(new int[]{end, chair});
        }
        return 0;
    }
}
// @lc code=end

