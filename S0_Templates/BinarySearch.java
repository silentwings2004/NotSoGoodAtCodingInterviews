package S0_Templates;

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
        if (nums.length == 0) return 0;

        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) left = mid + 1;
            else right = mid;
        }
        return left; // 在[left, right]里 <= target的第一个位置，最小不会越过left
    }

    public static int upperBound(int[] nums, int target) {
        if (nums.length == 0) return 0;
        
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] <= target) left = mid + 1;
            else right = mid;
        }
        return left; // 在[left, right]里 > target的第一个位置，最大不超过right
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
        int[] nums = new int[]{2,3,5,7,9,10,18,101};
        int first = lowerBound(nums, 2);
        int last = upperBound(nums, 9);
        System.out.println(first + " " + last);
    }
}
