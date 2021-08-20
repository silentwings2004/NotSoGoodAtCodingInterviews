import java.util.HashMap;

/*
 * @lc app=leetcode id=30 lang=java
 *
 * [30] Substring with Concatenation of All Words
 */

// @lc code=start
class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();

        HashMap<String, Integer> map = new HashMap<>();
        for (String word : words) map.put(word, map.getOrDefault(word, 0) + 1);

        int n = words.length, w = words[0].length();
        for (int i = 0; i <= s.length() - n * w; i++) {
            HashMap<String, Integer> copy = new HashMap<>(map);
            int k = n, j = i;
            while (k > 0) {
                String str = s.substring(j, j + w);
                if (!copy.containsKey(str)) break;
                copy.put(str, copy.get(str) - 1);
                if (copy.get(str) == 0) copy.remove(str);
                k--;
                j += w;
            }
            if (k == 0) res.add(i);
        }
        return res;
    }
}
// @lc code=end

