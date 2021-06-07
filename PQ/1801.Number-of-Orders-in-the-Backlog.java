import java.util.PriorityQueue;

class Solution {
    // time = O(nlogn), space = O(1)
    public int getNumberOfBacklogOrders(int[][] orders) {
        // corner case
        if (orders == null || orders.length == 0 || orders[0] == null || orders[0].length == 0) return 0;

        PriorityQueue<int[]> sell = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
        PriorityQueue<int[]> buy = new PriorityQueue<>((o1, o2) -> o2[0] - o1[0]);

        for (int[] order : orders) {
            if (order[2] == 0) buy.offer(order);
            else sell.offer(order);

            while (!buy.isEmpty() && !sell.isEmpty() && sell.peek()[0] <= buy.peek()[0]) {
                int num = Math.min(sell.peek()[1], buy.peek()[1]);
                buy.peek()[1] -= num;
                sell.peek()[1] -= num;
                if (buy.peek()[1] == 0) buy.poll();
                if (sell.peek()[1] == 0) sell.poll();
            }
        }

        int res = 0, M = (int)(1e9 + 7);
        for (int[] cur : sell) res = (res + cur[1]) % M;
        for (int[] cur : buy) res = (res + cur[1]) % M;
        return res;
    }
}