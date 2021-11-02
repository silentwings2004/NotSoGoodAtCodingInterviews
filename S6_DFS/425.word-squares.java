import java.util.HashMap;
import java.util.List;

/*
 * @lc app=leetcode id=425 lang=java
 *
 * [425] Word Squares
 */

// @lc code=start
class Solution {
    // time = O(n * 26^k), space = O(n * k)  n: number of words, k: the length of a single word
    public List<List<String>> wordSquares(String[] words) {
        List<List<String>> res = new ArrayList<>();

        int n = words[0].length();
        HashMap<String, List<String>> map = new HashMap<>();
        for (String word : words) {
            for (int i = 0; i < n; i++) {
                String prefix = word.substring(0, i);
                map.putIfAbsent(prefix, new ArrayList<>());
                map.get(prefix).add(word);
            }
        }

        dfs(words, 0, map, new ArrayList<>(), res);
        return res;
    }

    private void dfs(String[] words, int idx, HashMap<String, List<String>> map, List<String> path, List<List<String>> res) {
        int n = words[0].length();
        // base case
        if (idx == n) {
            res.add(new ArrayList<>(path));
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < idx; i++) {
            sb.append(path.get(i).charAt(idx));
        }
        String prefix = sb.toString();
        for (String next : map.getOrDefault(prefix, new ArrayList<>())) {
            path.add(next);
            dfs(words, idx + 1, map, path, res);
            path.remove(path.size() - 1);
        }
    }
}
// @lc code=end

