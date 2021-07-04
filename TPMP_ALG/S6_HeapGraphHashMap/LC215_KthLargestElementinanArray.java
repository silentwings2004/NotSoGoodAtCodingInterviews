package S6_HeapGraphHashMap;

import java.util.*;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: KthLargestElementinanArray
 * Creater: Silentwings
 * Date: Feb, 2020
 * Description: 215. Kth Largest Element in an Array
 */
public class LC215_KthLargestElementinanArray {
    /**
     * Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order,
     * not the kth distinct element.
     *
     * Example 1:
     *
     * Input: [3,2,1,5,6,4] and k = 2
     * Output: 5
     * Example 2:
     *
     * Input: [3,2,3,1,2,4,5,5,6] and k = 4
     * Output: 4
     * Note:
     * You may assume k is always valid, 1 ≤ k ≤ array's length.
     *
     * @param nums
     * @param k
     * @return
     */
    // S1: Sort and return Kth element, Time O(nlogn) space O(1) 采用改进型quick sort
    public int findKthLargest(int[] nums, int k) {
        // corner case
        if (nums == null || nums.length == 0) return -1; // k is always valid, so k < 1 || k > nums.length is ignored.
        int left = 0, right = nums.length - 1;
//        Arrays.sort(nums);
        quickSort(nums, left, right);
        return nums[nums.length - k];
    }

    private void quickSort(int[] nums, int left, int right) {
        // recursion base case
        if (left >= right) return;

        int pivotIndex = findPosAndPartition(nums, left, right);
        quickSort(nums, left, pivotIndex - 1);
        quickSort(nums, pivotIndex + 1, right);
    }

    private int findPosAndPartition(int[] nums, int left, int right) {
        Random rn = new Random();
        int pivotRandIndex = left + rn.nextInt(right - left + 1); // 注意这里的选取空间是[0, right - left + 1)
        int pivotValue = nums[pivotRandIndex];
        swap(nums, pivotRandIndex, right);

        int leftI = left;
        int rightI = right - 1; // 注意: array[right] is now the pivot

        while (leftI <= rightI) {
            if (nums[leftI] <= pivotValue) {
                leftI++;
            } else if (nums[rightI] > pivotValue) {
                rightI--;
            } else {
                swap(nums, leftI, rightI);
            }
        }
        swap(nums, leftI, right);
        return leftI;
    }

    private void swap(int[] nums, int i, int j) {
        int count = nums[i];
        nums[i] = nums[j];
        nums[j] = count;
    }

    // S2: Quick Selection / QuickSort Partition + Binary Search
    public int findKthLargest2(int[] nums, int k) {
        // corner case
        if (nums == null || nums.length == 0) return -1;
        int left = 0, right = nums.length - 1;
        quickSelection(nums, left, right, k);
        return nums[nums.length - k];
    }

    private void quickSelection(int[] nums, int left, int right, int k) {
        if (left >= right) return;
        int pivotIndex = findPosAndPartition(nums, left, right);
        // once pivotIndex is obtained, proceed with binary search at one side that has the target
        if (pivotIndex == nums.length - k) return;
        if (pivotIndex < nums.length - k) { // search in the right side of pivotIndex [pivotIndex + 1, right]
            quickSelection(nums, pivotIndex + 1, right, k);
        } else {  // search in the left side of pivotIndex [left, pivotIndex - 1]
            quickSelection(nums, left, pivotIndex - 1, k);
        }
    }
    // reuse the method findPosAndPartition and swap from S1

    // S3: MinHeap → size n    sort with heap   selection sort的变形   每次取剩下当中最小值
    //step1: nlogn one by one → heapify the whole input array O(n)
    //step2: keep pop out k times O(klogn)
    //Time O(n + klogn)
    //Space O(n) // streaming flow or very large
    public int findKthLargest3(int[] nums, int k) {
        // corner case
        if (nums == null || nums.length == 0) return -1;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int n : nums) {
            minHeap.add(n);
            if (minHeap.size() > k) minHeap.poll();
        }
        return minHeap.poll();
    }

    // S4: MaxHeap → size k (prefer)
    public int findKthLargest4(int[] nums, int k) {
        // corner case
        if (nums == null || nums.length == 0) return -1;
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int i = 0; i < k; i++) {
            heap.add(nums[i]);
        }

        for (int i = k; i < nums.length; i++) {
            if (nums[i] > heap.peek()) {
                heap.poll();
                heap.add(nums[i]);
            }
        }
        return heap.peek();
    }
}
