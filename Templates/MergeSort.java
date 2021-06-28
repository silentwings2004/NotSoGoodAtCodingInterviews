package Templates;

public class MergeSort {
    public static int firstPos(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = right - (right - left) / 2;
            if (nums[mid] <= target) left = mid;
            else right = mid - 1;
        }
        if (nums[left] < target) return left + 1;
        return left;
    }
    
    public static void mergeSort(int[] nums) {
        helper(nums, 0, nums.length - 1);
    }
    
    private static void helper(int[] nums, int left, int right) {
        if (left == right) return;
        
        int[] temp = new int[right - left + 1];
        int mid = left + (right - left) / 2;
        helper(nums, left, mid);
        helper(nums, mid + 1, right);
        int i = left, j = mid + 1, p = 0;
        while (i <= mid && j <= right) {
            if (nums[i] <= nums[j]) {
                temp[p] = nums[i++];
            } else temp[p] = nums[j++];
            p++;
        }
        while (i <= mid) temp[p++] = nums[i++];
        while (j <= right) temp[p++] = nums[j++];
        for (i = 0; i < right - left + 1; i++) {
            nums[left + i] = temp[i];
        }
    }
    
    public static void main(String[] args) {
        int[] arr = new int[]{3,6,2,4,8,5,1,0,9,7};
        mergeSort(arr);
        for (int n : arr) System.out.print(n + " ");
    }
}
