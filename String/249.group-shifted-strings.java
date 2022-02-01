import java.util.HashMap;
import java.util.List;

/*
 * @lc app=leetcode id=249 lang=java
 *
 * [249] Group Shifted Strings
 */

// @lc code=start
class Solution {
    // time = O(n * k), space = O(n)
    public List<List<String>> groupStrings(String[] strings) {
        List<List<String>> res = new ArrayList<>();
        HashMap<String, List<String>> map = new HashMap<>();
        for (String s : strings) {
            int offset = s.charAt(0) - 'a';
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                int diff = s.charAt(i) - offset;
                if (diff < 'a') diff += 26;
                sb.append((char) diff);
            }
            String key = sb.toString();
            map.putIfAbsent(key, new ArrayList<>());
            map.get(key).add(s);
        }
        res.addAll(map.values());
        return res;
    }
}
/**
 * abc -> abc
 * bcd -> abc
 * acef -> acef
 * xyz -> abc
 * az -> az
 * ba -> az
 * a -> a
 * z -> a
 */
// @lc code=end

