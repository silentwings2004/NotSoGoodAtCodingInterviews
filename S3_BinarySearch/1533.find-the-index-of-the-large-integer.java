/*
 * @lc app=leetcode id=1533 lang=java
 *
 * [1533] Find the Index of the Large Integer
 */

// @lc code=start
/**
 * // This is ArrayReader's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface ArrayReader {
 *     // Compares the sum of arr[l..r] with the sum of arr[x..y] 
 *     // return 1 if sum(arr[l..r]) > sum(arr[x..y])
 *     // return 0 if sum(arr[l..r]) == sum(arr[x..y])
 *     // return -1 if sum(arr[l..r]) < sum(arr[x..y])
 *     public int compareSub(int l, int r, int x, int y) {}
 *
 *     // Returns the length of the array
 *     public int length() {}
 * }
 */

class Solution {
    // time = O(logn), space = O(1)
    public int getIndex(ArrayReader reader) {
        int n = reader.length();
        int left = 0, right = n - 1;

        while (right - left + 1 >= 3) {
            int mid = (right - left + 1) / 3;
            int res = reader.compareSub(left, left + mid - 1, left + mid, left + 2 * mid - 1);
            if (res == 0) left = left + 2 * mid;
            else if (res == 1) right = left + mid - 1;
            else {
                left = left + mid;
                right = left + 2 * mid - 1;
            }
        }
        // 出loop时，2种情况：1个元素 or 2个元素
        if (left == right) return left;
        else {
            if (reader.compareSub(left, left, right, right) == 1) return left;
            return right;
        }
    }
}
// @lc code=end

