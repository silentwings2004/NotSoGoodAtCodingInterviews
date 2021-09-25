/*
 * @lc app=leetcode id=1109 lang=java
 *
 * [1109] Corporate Flight Bookings
 */

// @lc code=start
class Solution {
    // time = O(n), space = O(n)
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] diff = new int[20005];

        for (int[] book : bookings) {
            diff[book[0]] += book[2];
            diff[book[1] + 1] -= book[2];
        }

        int[] res = new int[n];
        int total = 0;
        for (int i = 1; i <= n; i++) {
            total += diff[i];
            res[i - 1] = total; 
        }
        return res;
    }
}
// @lc code=end

