/*
 * @lc app=leetcode id=692 lang=java
 *
 * [692] Top K Frequent Words
 */

// @lc code=start
class Solution {
    // time = O(n), space = O(n)
    public List<String> topKFrequent(String[] words, int k) {
        List<String> res = new ArrayList<>();
        // corner case
        if (words == null || words.length == 0 || k <= 0) return res;

        int n = words.length;
        HashMap<String, Integer> map = new HashMap<>();
        for (String word : words) {
        	 map.put(word, map.getOrDefault(word, 0) + 1);
        }

        List<String>[] bucket = new List[n + 1];
        for (String key : map.keySet()) {
        	int freq = map.get(key);
        	if (bucket[freq] == null) bucket[freq] = new ArrayList<>();
        	bucket[freq].add(key);
        }
        
        for (int i = n - 1; i >= 0; i--) {
        	if (bucket[i] != null) {
                Collections.sort(bucket[i]);
        		for (String next : bucket[i]) {
        			if (res.size() == k) return res;
        			res.add(next);
        		}
        	}
        }
        return res;
    }
}
// @lc code=end

