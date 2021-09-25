/*
 * @lc app=leetcode id=759 lang=java
 *
 * [759] Employee Free Time
 */

// @lc code=start
/*
// Definition for an Interval.
class Interval {
    public int start;
    public int end;

    public Interval() {}

    public Interval(int _start, int _end) {
        start = _start;
        end = _end;
    }
};
*/

class Solution {
    // time = O(nlogn), space = O(n)
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        List<int[]> diff = new ArrayList<>();
        for (List<Interval> list : schedule) {
            for (Interval x : list) {
                diff.add(new int[]{x.start, 1});
                diff.add(new int[]{x.end, -1});
            }
        }

        Collections.sort(diff, (o1, o2) -> o1[0] != o2[0] ? o1[0] - o2[0] : o2[1] - o1[1]);

        List<Interval> res = new ArrayList<>();
        int count = 0, start = -1, end = -1;
        for (int[] x : diff) {
            count += x[1];
            if (count == 0 && x[1] == -1) start = x[0];
            else if (count == 1 && x[1] == 1 && start != -1) {
                end = x[0];
                res.add(new Interval(start, end));
            }
        }
        return res;
    }
}
// @lc code=end

