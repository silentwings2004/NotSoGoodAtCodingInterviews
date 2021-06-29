package Templates;

public class Sort {
    // merge sort
    public static void mergeSort(int[] nums) {
        // corner case
        if (nums == null || nums.length == 0) return;

        mergeSort(nums, 0, nums.length - 1);
    }
    
    private static void mergeSort(int[] nums, int left, int right) {
        if (left >= right) return;
        
        int[] temp = new int[right - left + 1];
        int mid = left + (right - left) / 2;

        mergeSort(nums, left, mid);
        mergeSort(nums, mid + 1, right);

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

    // quick sort
    public void quickSrot(int[] nums, int start, int end) {
        if (start >= end) return;

        int left = start, right = end;
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

        quickSrot(nums, start, right);
        quickSrot(nums, left, end);
    }
    
    public static void main(String[] args) {
        int[] arr = new int[]{3,6,2,4,8,5,1,0,9,7};
        mergeSort(arr);
        for (int n : arr) System.out.print(n + " ");
    }
}
