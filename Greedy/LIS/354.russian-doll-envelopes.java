/*
 * @lc app=leetcode id=354 lang=java
 *
 * [354] Russian Doll Envelopes
 */

// @lc code=start
class Solution {
    // time = O(nlogn), space = O(n)
    public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, (o1, o2) -> o1[0] != o2[0] ? o1[0] - o2[0] : o2[1] - o1[1]);

        List<Integer> buffer = new ArrayList<>();
        for (int[] x : envelopes) {
            int idx = LIS(buffer, x[1]);
            if (idx == buffer.size()) buffer.add(x[1]);
            else buffer.set(idx, x[1]);
        }
        return buffer.size();
    }

    private int LIS(List<Integer> nums, int t) {
        if (nums.size() == 0) return 0;
        int left = 0, right = nums.size() - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums.get(mid) < t) left = mid + 1;
            else right = mid;
        }
        return nums.get(left) >= t ? left : left + 1;
    }
}
// @lc code=end

