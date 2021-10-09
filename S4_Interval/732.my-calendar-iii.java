/*
 * @lc app=leetcode id=732 lang=java
 *
 * [732] My Calendar III
 */

// @lc code=start
class MyCalendarThree {
    // time = O(nlogn), space = O(n)
    private TreeSet<int[]> diff;
    private int idx;
    public MyCalendarThree() {
        diff = new TreeSet<>((o1, o2) -> o1[0] != o2[0] ? o1[0] - o2[0] : (o1[1] != o2[1] ? o1[1] - o2[1] : o1[2] - o2[2]));
        idx = 0;
    }
    
    public int book(int start, int end) {
        diff.add(new int[]{start, 1, idx++}); // 注意：这里的event是有先后次序的，所以也要按照idx排序。
        diff.add(new int[]{end, -1, idx++});

        int res = 0, count = 0;
        for (int[] x : diff) {
            count += x[1];
            res = Math.max(res, count);
        }
        return res;
    }
}

/**
 * Your MyCalendarThree object will be instantiated and called as such:
 * MyCalendarThree obj = new MyCalendarThree();
 * int param_1 = obj.book(start,end);
 */
// @lc code=end

