/*
 * @lc app=leetcode id=140 lang=java
 *
 * [140] Word Break II
 */

// @lc code=start
class Solution {
    // time = O(2^n), space = O(n)
    TrieNode root;
    boolean[] memo;
    List<String> res;
    public List<String> wordBreak(String s, List<String> wordDict) {
        res = new ArrayList<>();
        root = new TrieNode();
        int n = s.length();
        memo = new boolean[n];

        // build trie
        for (String word : wordDict) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                if (node.next[c - 'a'] == null) {
                    node.next[c - 'a'] = new TrieNode();
                }
                node = node.next[c - 'a'];
            }
            node.isEnd = true;
        }

        List<String> path = new ArrayList<>();
        dfs(s, 0, path);
        return res;
    }

    private boolean dfs(String s, int cur, List<String> path) {
        // base case
        if (cur == s.length()) {
            StringBuilder sb = new StringBuilder();
            for (String word : path) sb.append(word).append(' ');
            sb.setLength(sb.length() - 1);
            res.add(sb.toString());
            return true;
        }

        if (memo[cur]) return false;

        boolean flag = false;
        TrieNode node = root;
        for (int i = cur; i < s.length(); i++) {
            char c = s.charAt(i);
            if (node.next[c - 'a'] != null) {
                node = node.next[c - 'a'];
                if (node.isEnd) {
                    path.add(s.substring(cur, i + 1));
                    if (dfs(s, i + 1, path)) flag = true;
                    path.remove(path.size() - 1);
                }
            } else break;
        }
        if (!flag) memo[cur] = true;
        return flag;
    }

    private class TrieNode {
        private TrieNode[] next;
        private boolean isEnd;
        public TrieNode() {
            this.next = new TrieNode[26];
            this.isEnd = false;
        }
    }
}
// @lc code=end

