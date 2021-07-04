package Templates;

public class BinarySearch {
    public static int binarySearch2(int[] nums, int target) {
        // corner case
        if (nums == null || nums.length == 0) return -1;

        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) return mid;
            if (nums[mid] < target) left = mid + 1;
            else right = mid;
        }
        return -1;
    }

    public static int lowerBound(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) left = mid + 1;
            else right = mid;
        }
        return nums[left] > target ? left - 1 : left;
    }

    public static int upperBound(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2 + 1;
            if (nums[mid] <= target) left = mid;
            else right = mid - 1;
        }
        return nums[left] < target ? left + 1 : left;
    }

    // B.S. II
    public int binarySearch3(int[] nums, int target) {
        // corner case
        if (nums == null || nums.length == 0) return -1;

        int left = 0, right = nums.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) return mid;
            if (nums[mid] < target) left = mid;
            else right = mid;
        }
        // post-processing
        if (nums[left] == target) return left;
        if (nums[right] == target) return right;
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 2, 2, 3, 3, 3, 3, 4, 5, 6};
        int first = lowerBound(nums, 0);
        int last = upperBound(nums, );
        System.out.println(first + " " + last);
    }
}
