package Templates;
import java.util.*;

public class TwoPointers {
    // 相向双指针(partition in quicksort)
    public void partition(int[] nums, int left, int right) {
        if (left >= right) return;

        // key point 1: pivot is the value, not the index!
        int pivot = left + (right - left) / 2;

        // key point 2: each time you compare left & right, it must be left <= right instead of left < right
        while (left <= right) {
            while (left <= right && nums[left] < pivot) left++;
            while (left <= right && nums[right] > pivot) right--;
            if (left <= right) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left++;
                right--;
            }
        }
    }

    // 背向双指针
    int slow = pos;
    int fast = pos + 1;
    while (slow >= 0 && fast < length) {
        if (canStop) break;
        left--;
        right++;
    }

    // 同向双指针
    int j = 0;
    for (int i = 0; i < n; i++) {
        // 不满足则循环到满足为止
        while (j < n && i到j之间不满足条件) {
            j++;
        }
        if (i到j之间满足条件) {
            处理i，j之间的这次搭配
        }
    }

    
    // 合并双指针
    public List<Integer> merge(List<Integer> list1, List<Integer> list2) {
        List<Integer> res = new ArrayList<>();

        int i = 0, j = 0;
        while (i < list1.size() && j < list2.size()) {
            if (list1.get(i) < list2.get(j)) {
                res.add(list1.get(i));
                i++;
            } else {
                res.add(list2.get(j));
                j++;
            }
        }

        while (i < list1.size()) {
            res.add(list1.get(i));
            i++;
        }

        while (j < list2.size()) {
            res.add(list2.get(j));
            j++;
        }

        return res;
    }

}
