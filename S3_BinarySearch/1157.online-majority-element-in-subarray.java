import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
 * @lc app=leetcode id=1157 lang=java
 *
 * [1157] Online Majority Element In Subarray
 */

// @lc code=start
class MajorityChecker {
    HashMap<Integer, List<Integer>> map; // val -> {pos}
    List<int[]> nums;
    public MajorityChecker(int[] arr) {
        map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            map.putIfAbsent(arr[i], new ArrayList<>());
            map.get(arr[i]).add(i);
        }

        nums = new ArrayList<>();
        for (int x : map.keySet()) {
            nums.add(new int[]{map.get(x).size(), x});
        }
        Collections.sort(nums, (o1, o2) -> o2[0] - o1[0]);
    }
    
    public int query(int left, int right, int threshold) {
        int total = right - left + 1;
        for (int i = 0; i < nums.size(); i++) {
            int[] x = nums.get(i);
            if (map.get(x[1]).size() < threshold) return -1; // 下面找的数频次会更小，直接剪枝即可，因为前面是从大到小sort的
            int a = lowerBound(map.get(x[1]), left); // >= left的位置
            int b = upperBound(map.get(x[1]), right); // <= right的位置
            total -= b - a + 1;
            if (b - a + 1 >= threshold) return x[1];
            if (total < threshold) return -1; // 剩下的总数都少于threshold，那也可以剪枝
        }
        return -1;
    }

    private int lowerBound(List<Integer> nums, int t) {
        int left = 0, right = nums.size() - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums.get(mid) < t) left = mid + 1;
            else right = mid;
        }
        return nums.get(left) >= t ? left : left + 1;
    }

    private int upperBound(List<Integer> nums, int t) {
        int left = 0, right = nums.size() - 1;
        while (left < right) {
            int mid = right - (right - left) / 2;
            if (nums.get(mid) <= t) left = mid;
            else right = mid - 1;
        }
        return nums.get(left) <= t ? left : left - 1;
    }
}

/**
 * Your MajorityChecker object will be instantiated and called as such:
 * MajorityChecker obj = new MajorityChecker(arr);
 * int param_1 = obj.query(left,right,threshold);
 */
// @lc code=end

