import java.util.HashMap;

/*
 * @lc app=leetcode id=838 lang=java
 *
 * [838] Push Dominoes
 */

// @lc code=start
class Solution {
    // time = O(n), space = O(n)
    public String pushDominoes(String dominoes) {
        // corner case
        if (dominoes == null || dominoes.length() == 0) return "";

        int n = dominoes.length();
        char[] chars = dominoes.toCharArray();
        Queue<int[]> queue = new LinkedList<>();
        int[] res = new int[n];
        Arrays.fill(res, -2);
        for (int i = 0; i < n; i++) {
            if (chars[i] == 'L') {
                queue.offer(new int[]{i, -1});
                res[i] = -1;
            } else if (chars[i] == 'R') {
                queue.offer(new int[]{i, 1});
                res[i] = 1;
            }
        }

        while (!queue.isEmpty()) {
            int size = queue.size();
            HashMap<Integer, Integer> map = new HashMap<>();
            while (size-- > 0) {
                int[] cur = queue.poll();
                int pos = cur[0], dir = cur[1];

                if (dir == 1 && pos + 1 < n && res[pos + 1] == -2) {
                    map.put(pos + 1, map.getOrDefault(pos + 1, 0) + 1);
                }
                if (dir == -1 && pos - 1 >= 0 && res[pos - 1] == -2) {
                    map.put(pos - 1, map.getOrDefault(pos - 1, 0) - 1);
                }
            }
            for (int key : map.keySet()) {
                queue.offer(new int[]{key, map.get(key)});
                res[key] = map.get(key);
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (res[i] == 1) sb.append('R');
            else if (res[i] == -1) sb.append('L');
            else sb.append('.');
        }
        return sb.toString();
    }
}
// @lc code=end

