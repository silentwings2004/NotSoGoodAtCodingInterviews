import java.util.Arrays;

/*
 * @lc app=leetcode id=373 lang=java
 *
 * [373] Find K Pairs with Smallest Sums
 */

// @lc code=start
class Solution {
    // time = O(nlogn), space = O(n)
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        int m = nums1.length, n = nums2.length;
        long left = nums1[0] + nums2[0], right = nums1[m - 1] + nums2[n - 1];

        while (left < right) {
            long mid = left + (right - left) / 2;
            long count = countSmallerOrEqual(nums1, nums2, mid); // # of pairs whose sum <= mid
            if (count < k) left = mid + 1;
            else right = mid;
        }

        long sum = left;
        List<List<Integer>> res1 = new ArrayList<>();
        List<List<Integer>> res2 = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int j = 0;
            while (j < n && nums1[i] + nums2[j] <= sum) {
                if (nums1[i] + nums2[j] < sum) res1.add(Arrays.asList(nums1[i], nums2[j]));
                else res2.add(Arrays.asList(nums1[i], nums2[j]));
                j++;
            }
        }

        int t = Math.min(res2.size(), k - res1.size());
        for (int i = 0; i < t; i++) {
            res1.add(res2.get(i));
        }
        return res1;
    }

    private long countSmallerOrEqual(int[] nums1, int[] nums2, long t) {
        // nums1[i] + nums2[j] <= t
        int m = nums1.length, n = nums2.length;
        int j = n - 1;
        long count = 0;
            
        for (int i = 0; i < m; i++) {
            while (j >= 0 && nums1[i] + nums2[j] > t) j--;
            // nums2[0:j] are ok
            count += j + 1;
        }
        return count;
    }
}
// @lc code=end

