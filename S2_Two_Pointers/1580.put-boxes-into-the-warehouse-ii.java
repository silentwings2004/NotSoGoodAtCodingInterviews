/*
 * @lc app=leetcode id=1580 lang=java
 *
 * [1580] Put Boxes Into the Warehouse II
 */

// @lc code=start
class Solution {
    // time = O(n), space = O(1)
    public int maxBoxesInWarehouse(int[] boxes, int[] warehouse) {
        Arrays.sort(boxes);

        int n = warehouse.length;
        int i = 0, j = n - 1;
        int count = 0;

        for (int k = boxes.length - 1; k >= 0; k--) {
            int box = boxes[k];
            if (i > j) break;
            if (box > Math.max(warehouse[i], warehouse[j])) continue;
            // put at left side
            if (warehouse[j] < box || warehouse[i] >= box && warehouse[i] < warehouse[j]) i++;
            else j--;
            count++;
        }
        return count;
    }
}
// @lc code=end

