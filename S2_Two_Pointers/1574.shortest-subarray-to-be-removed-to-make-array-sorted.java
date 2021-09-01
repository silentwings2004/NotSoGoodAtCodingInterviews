/*
 * @lc app=leetcode id=1574 lang=java
 *
 * [1574] Shortest Subarray to be Removed to Make Array Sorted
 */

// @lc code=start
class Solution {
    // time = O(n), space = O(1)
    public int findLengthOfShortestSubarray(int[] arr) {
        // corner case
        if (arr == null || arr.length == 0) return 0;

        int n = arr.length, res = n - 1;
        // 从后往前找一个最长的递减区间
        int j = n - 1;
        while (j - 1 >= 0 && arr[j] >= arr[j - 1]) j--;
        res = Math.min(res, j);
        if (res == 0) return 0;
        
        // 下面一定是三段论的模型，i != j，否则 j - i - 1 = -1 < 0，前提是i与j必须错开，不能相等
        for (int i = 0; i < n; i++) {
            if (i - 1 >= 0 && arr[i] < arr[i - 1]) break; // i 不存在
            while (j < n && arr[j] < arr[i]) j++;
            res = Math.min(res, j - i - 1);
        }
        return res;
    }
}
// @lc code=end

