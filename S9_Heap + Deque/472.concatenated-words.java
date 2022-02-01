/*
 * @lc app=leetcode id=472 lang=java
 *
 * [472] Concatenated Words
 */

// @lc code=start
class Solution {
    TrieNode root;
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> res = new ArrayList<>();
        root = new TrieNode();
        Arrays.sort(words, (o1, o2) -> o1.length() - o2.length());

        for (String word : words) {
            if (word.length() != 0 && check(word)) res.add(word);

            TrieNode node = root;
            for (char ch : word.toCharArray()) {
                if (node.next[ch - 'a'] == null) {
                    node.next[ch - 'a'] = new TrieNode();
                }
                node = node.next[ch - 'a'];
            }
            node.isEnd = true;
        }
        return res;
    }

    private boolean check(String word) {
        boolean[] memo = new boolean[word.length()];
        return dfs(word, 0, memo);
    }

    private boolean dfs(String word, int cur, boolean[] memo) {
        // base case
        if (cur == word.length()) return true;
        if (memo[cur]) return false;

        TrieNode node = root;
        for (int i = cur; i < word.length(); i++) {
            if (node.next[word.charAt(i) - 'a'] != null) {
                node = node.next[word.charAt(i) - 'a'];
                if (node.isEnd && dfs(word, i + 1, memo)) return true;
            } else break;
        }
        memo[cur] = true;
        return false;
    }

    private class TrieNode {
        private TrieNode[] next;
        private boolean isEnd;
        public TrieNode() {
            next = new TrieNode[26];
            isEnd = false;
        }
    }
}
// @lc code=end

