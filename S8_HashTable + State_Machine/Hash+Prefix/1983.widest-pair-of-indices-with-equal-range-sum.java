/*
 * @lc app=leetcode id=1983 lang=java
 *
 * [1983] Widest Pair of Indices With Equal Range Sum
 */

// @lc code=start
class Solution {
    // S1
    // time = O(n), space = O(n)
    public int widestPairOfIndices(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int[] pre1 = new int[n + 1], pre2 = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            pre1[i] = pre1[i - 1] + nums1[i - 1];
            pre2[i] = pre2[i - 1] + nums2[i - 1];
        }

        int[] diff = new int[n];
        for (int i = 0; i < n; i++) diff[i] = pre1[i + 1] - pre2[i + 1];
        
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (map.containsKey(diff[i])) {
                res = Math.max(res, i - map.get(diff[i]));
            }
            if (!map.containsKey(diff[i])) map.put(diff[i], i);
        }
        return res;
    }

    // S2
    // time = O(n), space = O(n)
    public int widestPairOfIndices2(int[] nums1, int[] nums2) {
        int n = nums1.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int res = 0, diff = 0;
        for (int i = 0; i < n; i++) {
            diff += nums1[i] - nums2[i];
            if (map.containsKey(diff)) {
                res = Math.max(res, i - map.get(diff));
            }
            if (!map.containsKey(diff)) map.put(diff, i);
        }
        return res;
    }
}
// @lc code=end

