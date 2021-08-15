/*
 * @lc app=leetcode id=315 lang=java
 *
 * [315] Count of Smaller Numbers After Self
 */

// @lc code=start
class Solution {
    // S1: merge sort + bs
    // time = O(nlogn * logn), space = O(n)
    private int[] sorted;
    private int[] ans;
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> res = new ArrayList<>();
        // corner case
        if (nums == null || nums.length == 0) return res;

        int n = nums.length;
        sorted = nums.clone();
        ans = new int[n];

        helper(nums, 0, n - 1);

        for (int num : ans) res.add(num);
        return res;
    }

    private void helper(int[] nums, int left, int right) {
        if (left >= right) return;

        int mid = left + (right - left) / 2;
        helper(nums, left, mid);
        helper(nums, mid + 1, right);

        for (int i = left; i <= mid; i++) {
            int idx = lowerBound(sorted, mid + 1, right, nums[i]);
            ans[i] += idx - (mid + 1) + 1;
        }

        int[] temp = new int[right - left + 1];
        int i = left, j = mid + 1, p = 0;
        while (i <= mid && j <= right) {
            if (sorted[i] <= sorted[j]) temp[p++] = sorted[i++];
            else temp[p++] = sorted[j++];
        }

        while (i <= mid) temp[p++] = sorted[i++];
        while (j <= right) temp[p++] = sorted[j++];

        for (i = 0; i < right - left + 1; i++) {
            sorted[i + left] = temp[i];
        }
    }

    private int lowerBound(int[] sorted, int left, int right, int target) {
        while (left < right) {
            int mid = right - (right - left) / 2;
            if (sorted[mid] < target) left = mid;
            else right = mid - 1;
        }
        return sorted[left] < target ? left : left - 1;
    }

    // S2: divide & conquer + merge sort
    // time = O(nlogn), space = O(n)
    private int[] index;
    private int[] answer;
    public List<Integer> countSmaller2(int[] nums) {
        List<Integer> res = new ArrayList<>();
        // corner case
        if (nums == null || nums.length == 0) return res;

        int n = nums.length;
        answer = new int[n];
        index = new int[n];
        for (int i = 0; i < n; i++) index[i] = i;

        partition(nums, 0, n - 1);

        for (int num : answer) res.add(num);
        return res;
    }

    private void partition(int[] nums, int a, int b) {
        if (a >= b) return;

        int m = a + (b - a) / 2;
        partition(nums, a, m);
        partition(nums, m + 1, b);

        int i = a, j = m + 1;
        while (i <= m && j <= b) {
            if (nums[i] <= nums[j]) answer[index[i++]] += j - (m + 1);
            else j++;
        }
        while (i <= m) answer[index[i++]] += j - (m + 1);

        i = a;
        j = m + 1;
        int p = 0;
        int[] temp = new int[b - a + 1];
        int[] tempIdx = new int[b - a + 1];

        while (i <= m && j <= b) {
            if (nums[i] <= nums[j]) {
                temp[p] = nums[i];
                tempIdx[p++] = index[i++];
            } else {
                temp[p] = nums[j];
                tempIdx[p++] = index[j++];
            }
        }
        while (i <= m) {
            temp[p] = nums[i];
            tempIdx[p++] = index[i++];
        }
        while (j <= b) {
            temp[p] = nums[j];
            tempIdx[p++] = index[j++];
        }

        for (i = 0; i < temp.length; i++) {
            nums[i + a] = temp[i];
            index[i + a] = tempIdx[i];
        }
    }
}
/**
 * ref: LC1649: 翻译过来就是count smaller number before self 与该题几乎一致
 * 都可以用分治来做，用处并不是特别大，思想很经典
 * 分治法一般会和归并排序放在一起用
 * A: [y y y y y z z z z z]
 * B: [y y y y y] C:[z z z z z] + ...
 *     a      mid   mid+1    b
 * 固定一个y，z扫一遍 => C是有序的话，可以用二分法提高效率
 * 分治 + 归并排序 -> 小问题 解决 大问题，返回到上一级，也把它搞成有序
 * 先拆分分解递归，返回之后要搞成有序，这样再往上传递方便解决更大的问题 -> 归并排序搞成有序的
 * 有点像递归，最大特色是返回的时候需要额外做一个排序
 */

// @lc code=end

