package S9_BitNumberMath;
import java.util.*;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: SingleNumber
 * Creater: Silentwings
 * Date: Mar, 2020
 * Description: 136. Single Number
 */
public class LC136_SingleNumber {
    /**
     * Given a non-empty array of integers, every element appears twice except for one. Find that single one.
     *
     * Note:
     *
     * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
     *
     * Example 1:
     *
     * Input: [2,2,1]
     * Output: 1
     * Example 2:
     *
     * Input: [4,1,2,1,2]
     * Output: 4
     * @param nums
     * @return
     */
    // S1: Sorted --> 遍历偶数位置，check array[i] == array[i + 1]，每次i + 2
    // slow and fast, check array[slow] == array[fast], if not return array[slow]
    public int singleNumber(int[] nums) {
        if (nums.length == 1) return nums[0];
        Arrays.sort(nums);
        int slow = 0, fast = 1;
        while (fast < nums.length) {
            if (nums[slow] != nums[fast]) return nums[slow];
            slow += 2;
            fast += 2;
        }
        if (fast >= nums.length) return nums[slow]; // 出现奇数个元素的情况下，fast已经出界的情况
        return 0;
    }

    // S2: HashMap / Hashset / boolean[] / bit of Integer Bit Integer O(k) → O(m) → O(m/32)
    public int singleNumber2(int[] nums) {
        if (nums.length == 1) return nums[0];
        Set<Integer> set = new HashSet<>();
        int res = 0;
        for (int num : nums) {
            if (set.contains(num)) set.remove(num);
            else set.add(num);
        }
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) res = i;
        }
        return nums[res];
    }

    // S3: XOR ==> a ^ a = 0; 0 ^ b = b
    public int singleNumber3(int[] nums) {
        int res = 0;
        for (int num : nums) res ^= num;
        return res;
    }
}
