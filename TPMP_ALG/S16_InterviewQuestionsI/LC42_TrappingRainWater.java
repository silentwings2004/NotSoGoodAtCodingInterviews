package S16_InterviewQuestionsI;
import java.util.*;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: TrappingRainWater
 * Creater: Silentwings
 * Date: Mar, 2020
 * Description: 42. Trapping Rain Water
 */
public class LC42_TrappingRainWater {
    /**
     * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much
     * water it is able to trap after raining.
     *
     *
     * The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water
     * (blue section) are being trapped. Thanks Marcos for contributing this image!
     *
     * Example:
     *
     * Input: [0,1,0,2,1,0,1,3,2,1,2,1]
     * Output: 6
     * @param height
     * @return
     */
    // S4: 2 pointers --> Time = O(n), space = O(1)
    public int trap(int[] height) {
        // corner case
        if (height == null || height.length == 0) return 0;

        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0;
        int water = 0;

        while (left <= right) {
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);

            if (leftMax < rightMax) {
                water += leftMax - height[left];
                left++;
            } else {
                water += rightMax - height[right];
                right--;
            }
        }
        return water;
    }
}
