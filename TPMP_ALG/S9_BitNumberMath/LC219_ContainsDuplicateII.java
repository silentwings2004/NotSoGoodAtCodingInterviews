package S9_BitNumberMath;
import java.util.*;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: ContainsDuplicateII
 * Creater: Silentwings
 * Date: Mar, 2020
 * Description: 219. Contains Duplicate II
 */
public class LC219_ContainsDuplicateII {
    /**
     * Given an array of integers and an integer k, find out whether there are two distinct indices i and j in the
     * array such that nums[i] = nums[j] and the absolute difference between i and j is at most k.
     *
     * Example 1:
     *
     * Input: nums = [1,2,3,1], k = 3
     * Output: true
     * Example 2:
     *
     * Input: nums = [1,0,1,1], k = 1
     * Output: true
     * Example 3:
     *
     * Input: nums = [1,2,3,1,2,3], k = 2
     * Output: false
     * @param nums
     * @param k
     * @return
     */

    // S1: Use HashMap
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        // corner case
        if (nums == null || nums.length < 2 || k < 1) return false;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                if (i - map.get(nums[i]) <= k) return true; // 永远都是比较最新出现的2个，之前如果不满足条件后面出现的也不会满足
            }   // 比如[1, 0, 1, 1],如果第一个1和第二个1不符合条件，那么第三个1和第一个1也不会满足条件(index相差更大)
                // 因此前面1的index无需保留传递下去，只需要比较此时此刻得到的这个和上一个之间index只差是否<= k即可
            map.put(nums[i],i); // 不停地将后一个拥有同样key的value取代前一个
        }
        return false;
    }

    // S2: My own fussy solution (not recommend)
    public boolean containsNearbyDuplicate2(int[] nums, int k) {
        // corner case
        if (nums == null || nums.length < 2 || k < 1) return false;
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                map.get(nums[i]).add(i);
                continue;
            }
            List<Integer> list = new ArrayList<>();
            list.add(i);
            map.put(nums[i], list);
        }

        for (List<Integer> list : map.values()) {
            if (list.size() > 1) {
                Collections.sort(list);
                if (absDiff(list, k)) return true;
            }
        }
        return false;
    }

    private boolean absDiff(List<Integer> list, int k) {
        if (list == null || list.size() < 2 || k < 1) return false;
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(j) - list.get(i) <= k) return true;
            }
        }
        return false;
    }
}
