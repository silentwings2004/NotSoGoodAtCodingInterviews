package S9_BitNumberMath;
import java.util.*;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: SingleNumberII
 * Creater: Silentwings
 * Date: Mar, 2020
 * Description: 137. Single Number II
 */
public class LC137_SingleNumberII {
    /**
     * Given a non-empty array of integers, every element appears three times except for one, which appears exactly
     * once. Find that single one.
     *
     * Note:
     *
     * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
     *
     * Example 1:
     *
     * Input: [2,2,3,2]
     * Output: 3
     * Example 2:
     *
     * Input: [0,1,0,1,0,1,99]
     * Output: 99
     *
     * 0 -> 1 -> 2 -> 0
     * 00 -> 01 -> 10 -> 00
     * 00 -> 10 -> 01 -> 00
     *
     * @param nums
     * @return
     */
    // S1: Sorted --> check array[i] == array[i + 2]，每次i + 3
    // slow and fast, check array[slow] == array[fast], if not return array[slow]
    public int singleNumber(int[] nums) {
        if (nums.length == 1) return nums[0];
        Arrays.sort(nums);
        int slow = 0, fast = 1;
        while (fast < nums.length) {
            if (nums[slow] != nums[fast]) return nums[slow];
            slow += 3;
            fast += 3;
        }
        if (fast >= nums.length) return nums[slow];
        return 0;
    }

    // *S2: HashSet 将输入数组存储到 HashSet，然后使用 HashSet 中数字和的三倍与数组之和比较。【Lintcode上跑不过】
    // 3 X (a + b + c) - (a + a + a + b + b + b + c) = 2c
    // time = O(n), space = O(n)
    public int singleNumber2(int[] nums) {
        if (nums.length == 1) return nums[0];
        Set<Long> set = new HashSet<>();  // 注意这里因为下面要涉及到加和，所以必须考虑出界的问题，在这里就要先设为long
        long sumSet = 0, sumArray = 0;
        for (int num : nums) {
            sumArray += num;
            set.add((long)num);
        }
        for (long s : set) sumSet += s;
        return (int)((sumSet * 3 - sumArray) / 2);
    }

    // S3: HashMap 遍历输入数组，统计每个数字出现的次数，最后返回出现次数为 1 的数字。
    // time = O(n), space = O(n)
    public int singleNumber3(int[] nums) {
        if (nums.length == 1) return nums[0];
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for (int k : map.keySet()) {
            if (map.get(k) == 1) return k;
        }
        return -1;
    }

    // S4: Bit Operations    time = O(n), space = O(1)
    // 0 与任何数 XOR 结果为该数。两个相同的数 XOR 结果为 0。以此类推，只有某个位置的数字出现奇数次时，该位的掩码才不为 0。
    // 为了区分出现一次的数字和出现三次的数字，使用两个位掩码：once 和 twice。
    // 仅当 twice 未变时，改变 once。仅当 once 未变时，改变twice。
    public int singleNumber4(int[] nums) {
        if (nums.length == 1) return nums[0];
        int once = 0, twice = 0;
        for (int num : nums) {
            once = ~twice & (once ^ num);
            twice = ~once & (twice ^ num);
        }
        return once;
    }
}
