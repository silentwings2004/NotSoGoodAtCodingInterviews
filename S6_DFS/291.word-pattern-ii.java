/*
 * @lc app=leetcode id=291 lang=java
 *
 * [291] Word Pattern II
 */

// @lc code=start
class Solution {
    // time = O(n^k), space = O(k)  n: length of s, k: length of pattern
    HashMap<Character, String> map;
    HashSet<String> set;
    public boolean wordPatternMatch(String pattern, String s) {
        map = new HashMap<>();
        set = new HashSet<>();

        return dfs(0, 0, pattern, s); 
    }

    private boolean dfs(int x, int y, String pattern, String s) {
        int m = pattern.length(), n = s.length();
        // base case - success
        if (x == m && y == n) return true;

        // base case - fail
        if (x == m  || y == n) return false;

        char c = pattern.charAt(x);
        if (map.containsKey(c)) {
            String t = map.get(c);
            if (y + t.length() > n) return false;
            if (!s.substring(y, y + t.length()).equals(t)) return false;
            return dfs(x + 1, y + t.length(), pattern, s);
        } else {
            for (int i = y; i < n; i++) {
                String t = s.substring(y, i + 1);
                if (set.contains(t)) continue;
                map.put(c, t);
                set.add(t);
                if (dfs(x + 1, y + t.length(), pattern, s)) return true;
                map.remove(c);
                set.remove(t);
            }
        }
        return false;
    }
}
// @lc code=end

