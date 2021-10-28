import java.util.HashMap;
import java.util.HashSet;
import java.util.Queue;

/*
 * @lc app=leetcode id=269 lang=java
 *
 * [269] Alien Dictionary
 */

// @lc code=start
class Solution {
    // time = O(C), space = O(1)
    public String alienOrder(String[] words) {
        HashMap<Character, HashSet<Character>> map = new HashMap<>();
        HashMap<Character, Integer> indegree = new HashMap<>();
        
        for (String word : words) {
            for (char c : word.toCharArray()) {
                indegree.put(c, 0); // 对真实存在的char的入度初始化为0
            }
        }

        for (int i = 0; i < words.length - 1; i++) {
            String s = words[i];
            String t = words[i + 1];
            
            if (s.length() > t.length() && s.substring(0, t.length()).equals(t)) return "";

            for (int j = 0; j < Math.min(s.length(), t.length()); j++) {
                if (s.charAt(j) == t.charAt(j)) continue;
                // s[j] => t[j]
                map.putIfAbsent(s.charAt(j), new HashSet<>());
                if (!map.get(s.charAt(j)).contains(t.charAt(j))) {
                    map.get(s.charAt(j)).add(t.charAt(j));
                    indegree.put(t.charAt(j), indegree.getOrDefault(t.charAt(j), 0) + 1); // 只给入度非0的创建了
                }
                break;
            }
        }

        Queue<Character> queue = new LinkedList<>(); // change queue to pq if return the smallest in normal lexicographical order
        for (char x : indegree.keySet()) {
            if (indegree.get(x) == 0) queue.offer(x);
        }

        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            char cur = queue.poll();
            sb.append(cur);

            for (char next : map.getOrDefault(cur, new HashSet<>())) {
                indegree.put(next, indegree.get(next) - 1);
                if (indegree.get(next) == 0) queue.offer(next);
            }
        }
        if (sb.length() != indegree.size()) return "";
        return sb.toString(); 
    }
}
// @lc code=end

