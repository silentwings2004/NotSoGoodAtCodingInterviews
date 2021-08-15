/*
 * @lc app=leetcode id=493 lang=java
 *
 * [493] Reverse Pairs
 */

// @lc code=start
class Solution {
    // S1: merge sort + bs
    // time = O(nlogn * logn), space = O(n)
    private int[] sorted;
    private int res;
    public int reversePairs(int[] nums) {
        // corner case
        if (nums == null || nums.length == 0) return 0;

        sorted = nums.clone();
        res = 0;

        helper(nums, 0, nums.length - 1);
        return res;
    }

    private void helper(int[] nums, int left, int right) {
        if (left >= right) return;

        int mid = left + (right - left) / 2;
        helper(nums, left, mid);
        helper(nums, mid + 1, right);

        for (int j = mid + 1; j <= right; j++) {
            int idx = upperBound(sorted, left, mid, 2 * (long)nums[j]);
            res += mid - idx + 1;
        }

//        Arrays.sort(sorted, left, right + 1);
        int i = left, j = mid + 1, p = 0;
        int[] temp = new int[right - left + 1];
        while (i <= mid && j <= right) {
            if (sorted[i] < sorted[j]) temp[p++] = sorted[i++];
            else temp[p++] = sorted[j++];
        }
        while (i <= mid) temp[p++] = sorted[i++];
        while (j <= right) temp[p++] = sorted[j++];

        for (i = 0; i < temp.length; i++) {
            sorted[i + left] = temp[i];
        }
    }

    private int upperBound(int[] sorted, int left, int right, long target) {
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (sorted[mid] <= target) left = mid + 1;
            else right = mid;
        }
        return sorted[left] > target ? left : left + 1;
    }

    // S2: divide conquer + merge sort
    // time = O(nlogn), space = O(n)
    private int ret;
    public int reversePairs2(int[] nums) {
        // corner case
        if (nums == null || nums.length == 0) return 0;

        sorted = nums.clone();
        ret = 0;

        partition(nums, 0, nums.length - 1);
        return ret;
    }

    private void partition(int[] nums, int left, int right) {
        if (left >= right) return;

        int mid = left + (right - left) / 2;
        partition(nums, left, mid);
        partition(nums, mid + 1, right);

        int i = left, j = mid + 1;
        while (i <= mid && j <= right) {
            if (2 * (long)nums[j] < nums[i]) {
                ret += mid - i + 1;
                j++;
            } else i++;
        }
        i = left;
        j = mid + 1;
        int p = 0;
        int[] temp = new int[right - left + 1];

        while (i <= mid && j <= right) {
            if (nums[i] < nums[j]) temp[p++] = nums[i++];
            else temp[p++] = nums[j++];
        }
        while (i <= mid) temp[p++] = nums[i++];
        while (j <= right) temp[p++] = nums[j++];

        for (i = 0; i < temp.length; i++) {
            nums[i + left] = temp[i];
        }
    }
}
/**
 * A：[y y y y y z z z z]
 * B: [y y y y y] C: [z z z z z] + ...
 * 分治法和归并排序在一起用 => 返回的时候排个序
 * 变有序有什么帮助：用二分法(logn)找出逆序对，方便我们在做additional work时就变得非常高效
 * 拆分最多是logn层
 */
// @lc code=end

