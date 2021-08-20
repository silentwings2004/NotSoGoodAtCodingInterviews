/*
 * @lc app=leetcode id=1564 lang=java
 *
 * [1564] Put Boxes Into the Warehouse I
 */

// @lc code=start
class Solution {
    // time = O(nlogn + m), space = O(1)
    public int maxBoxesInWarehouse(int[] boxes, int[] warehouse) {
        Arrays.sort(boxes); // O(nlogn)

        int n = boxes.length, i = n - 1, count = 0;
        for (int w : warehouse) { // O(m)
            while (i >= 0 && boxes[i] > w) i--;
            if (i == -1) return count;
            count++;
            i--;
        }
        return count;
    }
}
// @lc code=end

