
package Two_Pointers;
class Solution {
    // time = O(n), space = O(1)
    public int maxArea(int[] height) {
        // corner case
        if (height == null || height.length == 0) return 0;

        int left = 0, right = height.length - 1;
        int max = 0, area = 0;
        while (left < right) {
            if (height[left] < height[right]) {
                area = height[left] * (right - left);
                left++;
            } else {
                area = height[right] * (right - left);
                right--;
            }
            max = Math.max(max, area);
        }
        return max;
    }
}
/**
 * 只需要移动height[left]和height[right]中较矮的一个板子即可，这样才有使总面积增大的可能.
 * 否则移动较高的板子，不会使结果变得更好，因为面积受限于较矮的那块板子。
 */