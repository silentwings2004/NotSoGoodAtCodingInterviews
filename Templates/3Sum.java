package Templates;

public class 3Sum {
    public void main() {
        Arrays.sort(nums); // quicksort used O(logn) extra space for stack
        
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
    }
}
