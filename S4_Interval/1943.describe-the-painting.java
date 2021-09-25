import java.util.TreeMap;

/*
 * @lc app=leetcode id=1943 lang=java
 *
 * [1943] Describe the Painting
 */

// @lc code=start
class Solution {
    // time = O(nlogn), space = O(n)
    public List<List<Long>> splitPainting(int[][] segments) {
        List<List<Long>> res = new ArrayList<>();

        // 需要一个有序的map -> 按照位置排好序
        TreeMap<Long, Long> map = new TreeMap<>();
        for (int[] seg : segments) {
            map.put((long) seg[0], map.getOrDefault((long) seg[0], 0L) + seg[2]);
            map.put((long) seg[1], map.getOrDefault((long) seg[1], 0L) - seg[2]); // end is exclusive
        }

        long sum = 0, start = -1, end = -1;
        for (long x : map.keySet()) {
            long pos = x, diff = map.get(x);
            
            if (start == -1) start = pos; // 还没有起始区间
            else {
                end = pos;
                res.add(Arrays.asList(start, end, sum));
                start = end;
            }
            sum += diff; // 因为end是开区间，只能取在start开始的sum
            if (sum == 0) start = -1; // 空区间，重置为0
        }
        return res;
    }
}
// @lc code=end

