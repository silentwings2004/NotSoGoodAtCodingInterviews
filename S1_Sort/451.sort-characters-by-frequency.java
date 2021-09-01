/*
 * @lc app=leetcode id=451 lang=java
 *
 * [451] Sort Characters By Frequency
 */

// @lc code=start
class Solution {
    // time = O(n), space = O(n)
    public String frequencySort(String s) {
        // corner case
        if (s == null || s.length() == 0) return "";

        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        int n = s.length();
        TreeSet<Character>[] bucket = new TreeSet[n + 1];

        for (char key : map.keySet()) {
            int freq = map.get(key);
            if (bucket[freq] == null) bucket[freq] = new TreeSet<>();
            bucket[freq].add(key);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = n; i >= 1; i--) {
            if (bucket[i] != null) {
                for (char c : bucket[i]) {
                    for (int j = 0; j < i; j++) {
                        sb.append(c);
                    }
                }
            }
        }
        return sb.toString();
    }
}
// @lc code=end

