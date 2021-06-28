package Templates;

public class FirstOrLastPos {
    public static int firstPos(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) left = mid + 1;
            else right = mid;
        }
        if (nums[left] < target) return left + 1;
        return left;
    }

    public static int lastPos(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = right - (right - left) / 2;
            if (nums[mid] <= target) left = mid;
            else right = mid - 1;
        }
        if (nums[left] < target) return left + 1;
        return left;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 2, 2, 3, 3, 3, 3, 4, 5, 6};
        int first = firstPos(nums, 2);
        int last = lastPos(nums, 3);
        System.out.println(first + " " + last);
    }
}
