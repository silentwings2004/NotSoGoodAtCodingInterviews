package S9_BitNumberMath;
import java.util.*;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: ContainsDuplicateIII
 * Creater: Silentwings
 * Date: Mar, 2020
 * Description: 220. Contains Duplicate III
 */
public class LC220_ContainsDuplicateIII {
    /**
     * Given an array of integers, find out whether there are two distinct indices i and j in the array such that
     * the absolute difference between nums[i] and nums[j] is at most t and the absolute difference between i and j
     * is at most k.
     *
     * Example 1:
     *
     * Input: nums = [1,2,3,1], k = 3, t = 0
     * Output: true
     * Example 2:
     *
     * Input: nums = [1,0,1,1], k = 1, t = 2
     * Output: true
     * Example 3:
     *
     * Input: nums = [1,5,9,1,5,9], k = 2, t = 3
     * Output: false
     * @param nums
     * @param k
     * @param t
     * @return
     */
    // S1: 双for loop 暴力遍历解法   time = O(n * k), space = O(1)
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        // corner case
        if (nums == null || nums.length < 2 || k < 1 || t < 0) return false;

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (j - i <= k) {
                    // 注意这里的类型要转化为long，应对可能的出界问题，比如Integer.MAX_VALUE - (-2) 就会产生溢出了，
                    // 一些溢出可能导致我们最终的结果出问题。
                    // 关于溢出的test case: [2147483647,-2147483647], k = 1, t = 2147483647
                    // 最直接的解决方案，也是最偷懒的方案，题目中给我们的数据是 int，我们强制转为 long 进行运算，就不会出错了。
                    // 否则如果只是把最后结果出来后转化为long，那么此时已经在int类型下出界而overflow，再转化为long无法得出正确的结果。
                    if (Math.abs((long)nums[i] - nums[j]) <= t) return true;
                }
            }
        }
        return false;
    }

    // S2: TreeSet: 红黑树  排序
    // ceiling: 返回大于或者等于element的最小值
    // floor: 返回小于或者等于element的最大值
    // time = O(nlogk), space = O(k)
    public boolean containsNearbyAlmostDuplicate2(int[] nums, int k, int t) {
        // corner case
        if (nums == null || nums.length < 2 || k < 1 || t < 0) return false;
        TreeSet<Long> set = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            Long floor = set.floor((long)nums[i]);
            Long ceiling = set.ceiling((long)nums[i]);

            if ((floor != null && nums[i] - floor <= t) || (ceiling != null && ceiling - nums[i] <= t)) return true;
            // 当前不符合上面条件的element，直接先加入到set中，有可能再下面与后来的element组合成符合条件的解
            set.add((long)nums[i]);
            if (i >= k) set.remove((long)nums[i - k]);
            //sliding window，当index超过[0,k - 1]的范围时，就淘汰掉最前面的老的element，使set里永远只有k个元素
        }
        return false;
    }

    // S3: Bucket --> bucket = (nums[i] - Integer.MIN_VALUE) / (t + 1)  time = O(n), space = O(k)
    public boolean containsNearbyAlmostDuplicate3(int[] nums, int k, int t) {
        // corner case
        if (nums == null || nums.length < 2 || k < 1 || t < 0) return false;

        HashMap<Long, Long> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            long remapNum = (long)nums[i] - Integer.MIN_VALUE;
            long bucket = remapNum / ((long)t + 1);   // 防止t = 0时没法被除

            if (map.containsKey(bucket) || (map.containsKey(bucket - 1) && remapNum - map.get(bucket - 1) <= t) ||
                    (map.containsKey(bucket + 1) && map.get(bucket + 1) - remapNum <= t)) return true;
            map.put(bucket, remapNum);
            if (map.entrySet().size() > k) {  //entrySet size的范围是【1，k]，所以在这里要 > k, 而不是 >= k，注意和S2区分，
                // 否则下面nums[i - k]会出现-1(index out of bound)的错误！！！
                long lastBucket = ((long)nums[i - k] - Integer.MIN_VALUE) / ((long)t + 1);
                map.remove(lastBucket); // sliding window移除掉出界的最前面那个bucket，先定位bucket的index，再进行移除
            }
        }
        return false;
    }
}
