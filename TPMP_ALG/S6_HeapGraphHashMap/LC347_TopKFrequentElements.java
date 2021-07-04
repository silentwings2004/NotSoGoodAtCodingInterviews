package S6_HeapGraphHashMap;
import java.util.*;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: TopKFrequentElements
 * Creater: Silentwings
 * Date: Feb, 2020
 * Description: 347. Top K Frequent Elements
 */
public class LC347_TopKFrequentElements {
    /**
     * Given a non-empty array of integers, return the k most frequent elements.
     *
     * Example 1:
     *
     * Input: nums = [1,1,1,2,2,3], k = 2
     * Output: [1,2]
     * Example 2:
     *
     * Input: nums = [1], k = 1
     * Output: [1]
     * Note:
     *
     * You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
     * Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
     * @param nums
     * @param k
     * @return
     */
    // S1: PriorityQueue ==> time : O(nlogn),  space : O(n)
    public List<Integer> topKFrequent(int[] nums, int k) {
        // corner case
        if (nums == null || nums.length == 0) return null;

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<Map.Entry<Integer, Integer>> maxHeap = new PriorityQueue<>((a, b) ->
                (b.getValue() - a.getValue()));
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            maxHeap.add(entry);
        }

        List<Integer> res = new ArrayList<>();
        while (res.size() < k) {
            Map.Entry<Integer, Integer> entry = maxHeap.poll();
            res.add(entry.getKey());
        }
        return res;
    }

    // S2: minHeap
    // time = O(n + k + nlogk), space = O(n + k)
    public int[] topKFrequent2(int[] nums, int k) {
        int[] res = new int[k];
        // corner case
        if (nums == null || nums.length == 0) return res;

        int len = nums.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        PriorityQueue<Element> minHeap = new PriorityQueue<>((o1, o2) -> o1.freq - o2.freq);

        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> e : map.entrySet()) {  // O(n)
            int key = e.getKey();
            int val = e.getValue();
            Element elm = new Element(key, val);
            if (minHeap.size() < k) {
                minHeap.offer(elm);   // O(logk)
            } else {
                if (minHeap.peek().freq < elm.freq) {
                    minHeap.poll();
                    minHeap.offer(elm); // O(logk)
                }
            }
        }

        for (int i = k - 1; i >= 0; i--) {  // O(k)
            res[i] = minHeap.poll().val;
        }

        return res;
    }

    class Element {
        private int val;
        private int freq;
        public Element(int val, int freq) {
            this.val = val;
            this.freq = freq;
        }
    }
}
