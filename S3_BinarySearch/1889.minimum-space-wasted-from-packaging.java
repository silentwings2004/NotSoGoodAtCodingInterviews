/*
 * @lc app=leetcode id=1889 lang=java
 *
 * [1889] Minimum Space Wasted From Packaging
 */

// @lc code=start
class Solution {
    // time = O(nlogn + m * (klogk + mlogn)), space = O(n)
    public int minWastedSpace(int[] packages, int[][] boxes) {
        int n = packages.length;
        Arrays.sort(packages); // O(nlogn)

        long M = (long)(1e9 + 7);
        long[] presum = new long[n + 1]; 
        for (int i = 1; i <= n; i++) presum[i] = presum[i - 1] + packages[i - 1]; // O(n)
        
        long res = Long.MAX_VALUE;
        for (int[] box : boxes) { // O(m)
            int m = box.length;
            Arrays.sort(box); // O(klogk)
            long waste = 0;
            int prev = -1;
            for (int i = 0; i < m; i++) { // O(m)
                int j = upperBound(packages, box[i]); // find 1st package > box[i] // O(logn)
                if (j == -1) continue; // fail, even smallest package cannot fit, has to increase box size
                waste += (long)(j - prev) * box[i] - (presum[j + 1] - presum[prev + 1]);
                prev = j;
                if (prev == n - 1) break;
            }
            if (prev != n - 1) continue; // // not all packages were fit, box are all occupied
            res = Math.min(res, waste);
        }
        return res == Long.MAX_VALUE ? -1 : (int)(res % M);
    }

    private int upperBound(int[] nums, int t) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = right - (right - left) / 2;
            if (nums[mid] <= t) left = mid;
            else right = mid - 1;
        }
        return nums[left] <= t ? left : left - 1;
    }
}
// @lc code=end

