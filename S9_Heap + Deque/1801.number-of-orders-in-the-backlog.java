import java.util.PriorityQueue;

/*
 * @lc app=leetcode id=1801 lang=java
 *
 * [1801] Number of Orders in the Backlog
 */

// @lc code=start
class Solution {
    // S1: PQ
    // time = O(nlogn), space = O(n)
    public int getNumberOfBacklogOrders(int[][] orders) {
        PriorityQueue<int[]> buy = new PriorityQueue<>((o1, o2) -> o2[0] - o1[0]);
        PriorityQueue<int[]> sell = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);

        long res = 0;
        long M = (long)(1e9 + 7);
        for (int[] order : orders) {
            res = (res + order[1]) % M;
            if (order[2] == 0) {
                while (!sell.isEmpty() && sell.peek()[0] <= order[0] && order[1] > 0) {
                    int[] cur = sell.poll(); // {price, amount}
                    int price = cur[0], amount = cur[1];
                    int num = Math.min(amount, order[1]);
                    amount -= num;
                    order[1] -= num;
                    res = (res - num * 2 + M) % M;
                    if (amount > 0) sell.offer(new int[]{price, amount});
                }
                if (order[1] > 0) buy.offer(new int[]{order[0], order[1]});
            } else {
                while (!buy.isEmpty() && buy.peek()[0] >= order[0] && order[1] > 0) {
                    int[] cur = buy.poll(); // {price, amount}
                    int price = cur[0], amount = cur[1];
                    int num = Math.min(amount, order[1]);
                    amount -= num;
                    order[1] -= num;
                    res = (res - num * 2 + M) % M;
                    if (amount > 0) buy.offer(new int[]{price, amount});
                }
                if (order[1] > 0) sell.offer(new int[]{order[0], order[1]});
            }
        }
        return (int) res;
    }

    // S2: PQ
    // time = O(nlogn), space = O(n)
    public int getNumberOfBacklogOrders2(int[][] orders) {
        PriorityQueue<int[]> buy = new PriorityQueue<>((o1, o2) -> o2[0] - o1[0]);
        PriorityQueue<int[]> sell = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);

        for (int[] order : orders) {
            if (order[2] == 0) buy.offer(order);
            else sell.offer(order);

            while (!buy.isEmpty() && !sell.isEmpty() && sell.peek()[0] <= buy.peek()[0]) {
                int num = Math.min(sell.peek()[1], buy.peek()[1]);
                sell.peek()[1] -= num;
                buy.peek()[1] -= num;
                if (sell.peek()[1] == 0) sell.poll();
                if (buy.peek()[1] == 0) buy.poll();
            }
        }

        long M = (long)(1e9 + 7), res = 0;
        while (!sell.isEmpty()) res = (res + sell.poll()[1]) % M;
        while (!buy.isEmpty()) res = (res + buy.poll()[1]) % M;
        return (int) res;
    }
}
// @lc code=end

