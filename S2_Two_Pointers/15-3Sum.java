package Two_Pointers;
import java.util.*;

class Solution {
    // time = O(n^2), space = O(logn)
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        
        // corner case
        if (nums == null || nums.length == 0) return res;

        Arrays.sort(nums); // quicksort used O(logn) extra space for stack

        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue; // 查重1
            int sum = -nums[i];
            int left = i + 1, right = n - 1;
            while (left < right) {
                if (nums[left] + nums[right] == sum) {
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while (left < right && nums[left] == nums[left + 1]) left++; // 查重2.1
                    while (left < right && nums[right] == nums[right - 1]) right--; // 查重2.2
                    left++;
                    right--;
                } else if (nums[left] + nums[right] < sum) left++;
                else right--;
            }
        }
        return res;
    }
}
/**
 * 一定要先排序。
 * 为了避免重复，仅在确认发现了一组解之后再移动left和right指针略过重复项。不要先略过重复项再判断是否解成立。
 * 对于最外层的循环，也是确定一个，先展开内层循环，再略过最外层的重复项。
 */