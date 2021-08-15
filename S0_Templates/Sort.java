package S0_Templates;
import java.util.*;

public class Sort {
    // S1: Selection Sort (TLE!!!)
    // time = O(n^2), space = O(1)
    public int[] sortArray(int[] nums) {
        // corner case
        if (nums == null || nums.length <= 1) return nums;

        int n = nums.length;
        for (int i = 0; i < n - 1; i++) {
            int idx = i;
            for (int j = i + 1; j < n; j++) {
                if (nums[j] < nums[idx]) idx = j;
            }
            if (idx != i) swap(nums, i, idx);
        }
        return nums;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    // S2: Merge Sort
    // time = O(nlogn), space = O(n)
    public int[] sortArray2(int[] nums) {
        // corner case
        if (nums == null || nums.length <= 1) return nums;

        return mergeSort(nums, 0, nums.length - 1);
    }

    private int[] mergeSort(int[] nums, int start, int end) {
        // base case
        if (start >= end) return nums;

        int mid = start + (end - start) / 2;
        mergeSort(nums, start, mid);
        mergeSort(nums, mid + 1, end);

        int i = start, j = mid + 1, p = 0;
        int[] temp = new int[end - start + 1];
        while (i <= mid && j <= end) {
            if (nums[i] <= nums[j]) temp[p++] = nums[i++];
            else temp[p++] = nums[j++];
        }
        while (i <= mid) temp[p++] = nums[i++];
        while (j <= end) temp[p++] = nums[j++];

        for (i = 0; i < end - start + 1; i++) {
            nums[i + start] = temp[i];
        }
        return nums;
    }

    // S3: Quick Sort
    // time = O(nlogn), space = O(n)
    public int[] sortArray3(int[] nums) {
        // corner case
        if (nums == null || nums.length <= 1) return nums;

        helper(nums, 0, nums.length - 1);
        return nums;
    }

    private void helper(int[] nums, int start, int end) {
        // base case
        if (start >= end) return;

        int left = start, right = end;
        // pivot must be saved and fixed as the following operations will do the swap and change the values of nums.
        // So if you only save pivot as index, then your nums[pivot] may have changed during the operations after swap!
        int pivot = nums[left + (right - left) / 2]; // VERY VERY IMPORTANT!!!

        while (left <= right) {
            while (left <= right && nums[left] < pivot) left++;
            while (left <= right && nums[right] > pivot) right--;
            if (left <= right) swap(nums, left++, right--);
        }
        helper(nums, start, right);
        helper(nums, left, end);
    }

    // S3.2: Quick Sort v2 (random)
    // time = O(nlogn), space = O(n)
    public int[] sortArray32(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }
    
    private void quickSort(int[] nums, int left, int right) {
        if (left >= right) return;
        
        int m = partition(nums, left, right);
        quickSort(nums, left, m - 1);
        quickSort(nums, m + 1, right);
    }
    
    private int partition(int[] nums, int left, int right) {
        Random random = new Random();
        int p = random.nextInt(right - left + 1) + left;
        swap(nums, left, p);
        int pivot = nums[left];
        p = left + 1;
        
        for (int i = left + 1; i <= right; i++) {
            if (nums[i] < pivot) swap(nums, i, p++);
        }
        p--;
        swap(nums, left, p);
        return p;
    }

    // S4: bucket (fake count) sort
    // time = O(n), space = O(n)
    public int[] sortArray4(int[] nums) {
        // corner case
        if (nums == null || nums.length <= 1) return nums;
        
        int n = nums.length;
        int min = nums[0], max = nums[0];
        
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        
        int[] bucket = new int[max - min + 1];
        for (int num : nums) {
            bucket[num - min]++;
        }
        
        int j = 0;
        for (int i = 0; i < bucket.length; i++) {
            while (bucket[i]-- > 0) nums[j++] = i + min;
        }
        return nums;
    }

    // real count (bucket) sort
    // time = O(n), space = O(n)
    public int[] sortArray42(int[] nums) {
        // corner case
        if (nums == null || nums.length <= 1) return nums;
        
        int n = nums.length;
        int min = nums[0], max = nums[0];
        
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        
        int[] bucket = new int[max - min + 1];
        for (int num : nums) {
            bucket[num - min]++;
        }
        
        for (int i = 1; i < bucket.length; i++) {
            bucket[i] += bucket[i - 1];
        }
        
        int[] res = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            res[--bucket[nums[i] - min]] = nums[i];
        }
        return res;
    }

    // S5: Sort with heap (can psss!)
    // time = O(nlogn), space = O(n)
    public int[] sortArray5(int[] nums) {
        // corner case
        if (nums == null || nums.length <= 1) return nums;

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int n : nums) minHeap.offer(n);
        for (int i = 0; i < nums.length; i++) {
            nums[i] = minHeap.poll();
        }
        return nums;
    }

    // S6: heap sort
    // time = O(nlogn), space = O(n)
    public int[] sortArray6(int[] nums) {
        // corner case
        if (nums == null || nums.length <= 1) return nums;

        int n = nums.length;
        // build the heap
        int lastNode = n - 1, parent = (lastNode - 1) / 2;
        for (int i = parent; i >= 0; i--) heapify(nums, n, i);

        // heap里最大的结点肯定是放在root的位置上，因为父节点都要大于它的子节点
        // 1. swap root(max node) with the last node
        // 2. chop down the last node
        // 3. heapify from the root => max node now comes to the root again

        for (int i = n - 1; i >= 0; i--) {
            swap(nums, i, 0);
            heapify(nums, i, 0); // i 代表当前结点个数，因为不断在砍断，所以i也是在同步减小的，代表剩下节点的数量
            // 比如现在i = n - 1, 因为当前root交换到了最后已经被砍掉，所以数量也从 n -> n - 1
        }
        return nums;
    }

    private void heapify(int[] nums, int n , int i) { // convert an unsorted array to complete binary tree
        int c1 = 2 * i + 1, c2 = 2 * i + 2;

        // find max
        int max = i;
        if (c1 < n && nums[c1] > nums[max]) max = c1;
        if (c2 < n && nums[c2] > nums[max]) max = c2;

        // swap and keep heapifying node i (i swapped to max)
        if (i != max) { // 注意：只有当i与max不等时，才有继续向下做heapify的必要！
            swap(nums, max, i);
            heapify(nums, n, max);
        }
    }

        // S7.1: 真计数排序
        public int[] sortArray7(int[] nums) {
            int M = (int)1e5 + 5;
            int[] cnt = new int[M];
            int n = nums.length;
            int[] ans = new int[n];
            
            for (int num : nums) {
                cnt[num + 50000]++;
            }
            
            for (int i = 1; i < M; i++) {
                cnt[i] += cnt[i - 1];
            }
            
            for (int i = n - 1; i >= 0; i--) {
                ans[--cnt[nums[i] + 50000]] = nums[i];
            }
            return ans;
        }
        
        // S7.2: 假计数排序
        public int[] sortArray72(int[] nums) {
            int M = (int)1e5 + 5;
            int[] cnt = new int[M];
            
            for (int num : nums) {
                cnt[num + 50000]++;
            }
            
            int j = 0;
            for (int i = -50000; i <= 50000; i++) {
                while (cnt[i + 50000]-- > 0) nums[j++] = i;
            }
            
            return nums;
        }
}
