import java.util.PriorityQueue;

/*
 * @lc app=leetcode id=871 lang=java
 *
 * [871] Minimum Number of Refueling Stops
 */

// @lc code=start
class Solution {
    // time = O(nlogn), space = O(n)
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
        int curFuel = startFuel, curStation = 0, count = 0;
        
        while (curStation < stations.length) {
            if (curFuel >= stations[curStation][0]) {
                pq.offer(stations[curStation][1]);
                curStation++;
            } else {
                while (!pq.isEmpty() && curFuel < stations[curStation][0]) {
                    curFuel += pq.poll();
                    count++;
                }
                if (curFuel < stations[curStation][0]) return -1;
            }
        }

        // check target as the last gas station
        while (!pq.isEmpty() && curFuel < target) {
            curFuel += pq.poll();
            count++;
        }
        if (curFuel < target) return -1;
        return count;
    }
}
// @lc code=end

