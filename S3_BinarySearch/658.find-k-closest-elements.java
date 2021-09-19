/*
 * @lc app=leetcode id=658 lang=java
 *
 * [658] Find K Closest Elements
 */

// @lc code=start
class Solution {
    // time = O(logn), space = O(1)
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> res = new ArrayList<>();
        // corner case
        if (arr == null || arr.length == 0 || k < 0) return res;

        int n = arr.length;
        int left = 0, right = n - k;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (x - arr[mid] > arr[mid + k] - x) left = mid + 1;
            else right = mid;
        } 
        for (int i = left; i < left + k; i++) {
            res.add(arr[i]);
        }
        return res;
        
    }
}
// @lc code=end

