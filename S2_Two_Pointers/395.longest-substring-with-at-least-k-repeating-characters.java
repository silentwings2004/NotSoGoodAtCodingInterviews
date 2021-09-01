/*
 * @lc app=leetcode id=395 lang=java
 *
 * [395] Longest Substring with At Least K Repeating Characters
 */

// @lc code=start
import java.util.*;
class Solution {
    // S1: Two Pointers
    // time = O(n), space = O(1)
    public int longestSubstring(String s, int k) {
        // corner case
        if (s == null || s.length() == 0 || k < 0) return 0;

        int res = 0;
        for (int m = 1; m <= 26; m++) {
            res = Math.max(res, helper(s, m, k));
        }
        return res;
    }

    private int helper(String s, int m, int k) {
        HashMap<Character, Integer> map = new HashMap<>();
        int n = s.length(), j = 0, res = 0;
        int count = 0;
        
        for (int i = 0; i < n; i++) {
            while (j < n && map.size() <= m) {
                map.put(s.charAt(j), map.getOrDefault(s.charAt(j), 0) + 1);
                if (map.get(s.charAt(j)) == k) count++;
                if (map.size() == m && count == m) {
                    res = Math.max(res, j - i + 1);
                }
                j++;
            }
            map.put(s.charAt(i), map.get(s.charAt(i)) - 1);
            if (map.get(s.charAt(i)) == k - 1) count--;
            if (map.get(s.charAt(i)) == 0) map.remove(s.charAt(i));
        }
        return res;
    }

    // S2: DFS
    // time = O(n^2), space = O(n)
    public int longestSubstring2(String s, int k) {
        // corner case
        if (s == null || s.length() == 0 || k < 0) return 0;
    
        HashMap<Character, Integer> map = new HashMap<>();
        for (char ch :  s.toCharArray()) map.put(ch, map.getOrDefault(ch, 0) + 1);
    
        int n = s.length(), res = 0;
        boolean flag = true;
        for (char key : map.keySet()) {
            if (map.get(key) < k) {
                flag = false;
                break;
            }
        }
        if (flag) return n;
    
        for (int i = 0; i < n; i++) {
            if (map.get(s.charAt(i)) < k) continue;
            int j = i;  // [o {x x x} o]
            while (j < n && map.get(s.charAt(j)) >= k) j++;
            res = Math.max(res, longestSubstring(s.substring(i, j), k));
            i = j;
        }
        return res;
    }
}
// @lc code=end

