package Two_Pointers;

import java.util.Arrays;

class Solution {
    // time = O(n^2), space = O(logn)
    public int threeSumClosest(int[] nums, int target) {
        // corner case
        if (nums == null || nums.length < 3) {
            throw new IllegalArgumentException("Invalid input!");
        }
        
        // if asked for O(1) space complexity, use selection sort instead of quicksort, and time = O(n^2)!
        Arrays.sort(nums); 

        // int res = nums[0] + nums[1] + nums[2]; // 也可以把res先设置成一个有效解！
        int res = Integer.MAX_VALUE / 2; // 注意这里不能设置为最大值，因为如果target = -1的话，两者相减会越界！！！
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            int left = i + 1, right = n - 1;
            while (left < right) {
                int sum = nums[left] + nums[right] + nums[i];
                if (sum == target) return target;
                if (Math.abs(sum - target) < Math.abs(res - target)) res = sum;
                if (sum < target) left++;
                else right--;
            }
        }
        return res;
    }
}
/**
 * 一定记得要先排序！
 * 在确定了第一个元素之后，第2、3个元素的指针该如何设计变动呢？
 * => 小的让它变大，大的让它变小，相等则直接返回，0是完美最接近的解
 * if asked for O(1) space complexity, use selection sort instead of quicksort, and time = O(n^2)
 */