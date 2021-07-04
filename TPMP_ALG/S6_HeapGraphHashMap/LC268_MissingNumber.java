package S6_HeapGraphHashMap;

import java.util.*;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: MissingNumber
 * Creater: Silentwings
 * Date: Feb, 2020
 * Description: 268. Missing Number
 */
public class LC268_MissingNumber {
    /**
     * Given an array containing n distinct numbers taken from 0, 1, 2, ..., n,
     * find the one that is missing from the array.
     *
     * Example 1:
     *
     * Input: [3,0,1]
     * Output: 2
     * Example 2:
     *
     * Input: [9,6,4,2,3,5,7,0,1]
     * Output: 8
     * Note:
     * Your algorithm should run in linear runtime complexity.
     * Could you implement it using only constant extra space complexity?
     *
     * @param nums
     * @return
     */
    // S1: Sort → one pass → check array[fast] - array[fast - 1] == 2, return fast / array[fast] - 1
    // Time = O(nlogn) sort + O(n)one pass = O(nlogn)
    public int missingNumber(int[] nums) {
        // corner case
        if (nums == null || nums.length == 0) return -1;
        if (nums.length == 1) return nums[0] == 0 ? 1 : 0; // 应对[0]或者[1]这种case
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] - nums[i - 1] == 2) return nums[i] - 1;
        }
        return nums[0] == 0 ? nums.length : 0; //上面for loop没找到的话，表明缺失的元素就在队头或者队尾，如果数列从0开始，
        // 则缺失的就是队尾元素nums.length；如果不从0开始，缺失的必然就是0
    }

    // S2: Sort → one pass → check value with index, index can be implemented with counter initialized with 0
    // Time = O(nlogn) sort + O(n)one pass = O(nlogn)
    public int missingNumber2(int[] nums) {
        // corner case
        if (nums == null || nums.length == 0) return -1;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (i - nums[i] <= -1) return i;
        }
        return nums.length;
    }

    // S3: Sort → binary search, check index and value  找1和0的分界线
    public int missingNumber3(int[] nums) {
        // corner case
        if (nums == null || nums.length == 0) return -1;
        Arrays.sort(nums);
        int left = 0, right = nums.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] != mid) right = mid;
            else left = mid;
        }
        if (nums[0] == 0) {
            if (nums[right] == right) return nums[right] + 1;
            return nums[right] - 1;
        }
        return 0;
    }

    // S4: HashMap <key = value of element, value = count>   统计次数
    public int missingNumber4(int[] nums) {
        // corner case
        if (nums == null || nums.length == 0) return -1;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(nums[i])) {
                int count = 0;
                map.put(nums[i], count++);
            } else {
                int val = map.get(nums[i]);
                map.put(nums[i], val + 1);
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if(!map.containsKey(i)) return i;
        }
        return nums.length;
    }

    // S5: HashSet <key = value of element>
    public int missingNumber5(int[] nums) {
        // corner case
        if (nums == null || nums.length == 0) return -1;
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }
        for (int i = 0; i< nums.length; i++) {
            if (!set.contains(i)) return i;
        }
        return nums.length;
    }

    // S6: Math 数学解
    public int missingNumber6(int[] nums) {
        // corner case
        if (nums == null || nums.length == 0) return -1;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        int total = (nums.length + 1) * nums.length / 2;
        if (total - sum == 0) {
            if (nums[0] == 0) return nums.length;
            return 0;
        }
        return total - sum;
    }

    // S7: bit操作
    // time = O(n), space = O(1)
    public int missingNumber7(int[] nums) {
        // corner case
        if (nums == null || nums.length == 0) return -1;

        int n = nums.length;
        int res = n;
        for (int i = 0; i < n; i++) {
            res ^= i ^ nums[i];
        }
        return res;
    }
}