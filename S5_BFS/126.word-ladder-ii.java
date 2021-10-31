import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;

/*
 * @lc app=leetcode id=126 lang=java
 *
 * [126] Word Ladder II
 */

// @lc code=start
class Solution {
    // time = O(n * k^2 + a), space = O(n * k) n: number of words in wordList, k: max length of a word, a: num of possible paths
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        HashSet<String> dict = new HashSet<>(wordList);
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        HashMap<String, List<String>> map = new HashMap<>();
        boolean isOver = false;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            HashSet<String> visited = new HashSet<>();
            while (size-- > 0) {
                String cur = queue.poll();
                List<String> nexts = convert(cur, dict);
                for (String next : nexts) {
                    if (next.equals(endWord)) isOver = true;
                    if (visited.add(next)) {
                        queue.offer(next);
                        map.putIfAbsent(next, new ArrayList<>());
                    }
                    map.get(next).add(cur);
                }
            }
            dict.removeAll(visited);
            if (isOver) break;
        }

        
        if (!isOver) return res;
        List<String> path = new LinkedList<>();
        path.add(endWord);
        dfs(map, endWord, beginWord, path, res);
        return res;
    }

    private void dfs(HashMap<String, List<String>> map, String cur, String end, List<String> path, List<List<String>> res) {
        // base case
        if (cur.equals(end)) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (String next : map.get(cur)) {
            path.add(0, next);
            dfs(map, next, end, path, res);
            path.remove(0);
        }
    }

    private List<String> convert(String s, HashSet<String> dict) {
        List<String> res = new ArrayList<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char temp = chars[i];
            for (char c = 'a'; c <= 'z'; c++) {
                chars[i] = c;
                String str = String.valueOf(chars);
                if (c != temp && dict.contains(str)) res.add(str);
            }
            chars[i] = temp;
        }
        return res;
    }
}
// @lc code=end

