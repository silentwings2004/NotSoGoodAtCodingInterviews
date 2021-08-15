/*
 * @lc app=leetcode id=49 lang=java
 *
 * [49] Group Anagrams
 */

// @lc code=start
class Solution {
    // time = O(n * k), space = O(n * k)   k: the maximum length of a string in strs
    public List<List<String>> groupAnagrams(String[] strs) {
        // corner case
        if (strs == null || strs.length == 0) return new ArrayList<>();
    
        HashMap<String, List<String>> map = new HashMap<>();
    
        for (String str : strs) {
            int[] count = new int[26];
            for (char c : str.toCharArray()) {
                count[c - 'a']++;
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < count.length; i++) {
                if (count[i] != 0) {
                    sb.append(count[i]).append((char)(i + 'a'));
                }
            }
            String s = sb.toString();
            if (map.containsKey(s)) {
                List<String> list = map.get(s);
                list.add(str);
            } else {
                map.put(s, new ArrayList<>());
                map.get(s).add(str);
            }
        }
        return new ArrayList<>(map.values());
    }
}
// @lc code=end

